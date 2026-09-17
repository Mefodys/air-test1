#!/usr/bin/env sh
set -eu

# First build with kotlin.stdlib declared, then remove it and compile again.
gradle --no-daemon clean compileKotlin
cat > src/main/java/module-info.java <<'EOF'
module my.module {
}
EOF

if gradle --no-daemon compileKotlin; then
  echo "ERROR: compilation unexpectedly succeeded without requires kotlin.stdlib" >&2
  exit 1
fi

echo "Expected compiler failure observed after removing requires kotlin.stdlib."
