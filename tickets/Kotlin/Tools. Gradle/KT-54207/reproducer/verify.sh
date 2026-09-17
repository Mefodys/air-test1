#!/bin/sh
set -eu

archive=build/libs/kt-54207-reproducer-1.0-sources.jar

gradle --no-daemon clean sourcesJar
unzip -Z1 "$archive" | sort > sourcesJar.entries

gradle --no-daemon clean kotlinSourcesJar
unzip -Z1 "$archive" | sort > kotlinSourcesJar.entries

printf '%s\n' 'sourcesJar:'
cat sourcesJar.entries
printf '%s\n' 'kotlinSourcesJar:'
cat kotlinSourcesJar.entries

if grep -qx 'main/Kotlin.kt' kotlinSourcesJar.entries && ! grep -qx 'main/Kotlin.kt' sourcesJar.entries; then
    printf '%s\n' 'Reproduced: kotlinSourcesJar archives sources below main/, unlike sourcesJar.'
    exit 0
fi

printf '%s\n' 'Not reproduced: the archives no longer differ by the main/ directory.' >&2
exit 1
