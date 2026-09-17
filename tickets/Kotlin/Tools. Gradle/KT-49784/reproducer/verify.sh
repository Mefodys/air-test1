#!/bin/sh
set -eu

gradle --no-daemon clean publishMavenJavaPublicationToMavenRepository
module_file=build/repo/org/example/kt-49784-reproducer/1.0/kt-49784-reproducer-1.0.module

python3 - "$module_file" <<'PY'
import json
import sys

with open(sys.argv[1]) as f:
    variants = json.load(f)["variants"]

main = next(v for v in variants if v["name"] == "apiElements")
common = next(v for v in variants if v["name"] == "commonApiElements")
keys = ("org.gradle.jvm.environment", "org.jetbrains.kotlin.platform.type")
missing = {key: key for key in keys if key in main["attributes"] and key not in common["attributes"]}

print("main attributes:", main["attributes"])
print("common attributes:", common["attributes"])
if missing:
    print("REPRODUCED: commonApiElements lacks attributes present on apiElements:", ", ".join(missing))
    sys.exit(1)
print("NOT REPRODUCED: commonApiElements includes every compared main attribute.")
PY
