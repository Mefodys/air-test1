#!/usr/bin/env bash
set -euo pipefail

if [ "${AIR_STARTUP_MODE:-}" = "warmup" ]; then
  WARMUP=1
else
  WARMUP=
fi

image='gradle:9.7.1-jdk25'
reproducer='tickets/Kotlin/Tools. Gradle/KT-41077/reproducer'
build_log=/tmp/air-gradle-smoke.log

proxy_options() {
  local proxy host port
  proxy=${HTTPS_PROXY:-${HTTP_PROXY:-}}
  if [ -z "$proxy" ]; then
    return 0
  fi
  proxy=${proxy#*://}
  proxy=${proxy%%/*}
  host=${proxy%:*}
  port=${proxy##*:}
  printf '%s' "-Dhttp.proxyHost=$host -Dhttp.proxyPort=$port -Dhttps.proxyHost=$host -Dhttps.proxyPort=$port -Dhttp.nonProxyHosts=localhost\\|127.0.0.1"
}

healthcheck() {
  echo 'Waiting for Kotlin/Gradle smoke build...'
  while kill -0 "$build_pid" 2>/dev/null; do
    echo 'Gradle smoke build is still running.'
    sleep 5
  done

  if ! wait "$build_pid"; then
    echo 'Gradle smoke build failed; recent output follows:' >&2
    tail -100 "$build_log" >&2 || true
    return 1
  fi

  docker image inspect "$image" >/dev/null
  grep -q 'BUILD SUCCESSFUL' "$build_log"
  echo 'Kotlin/Gradle smoke build is ready.'
}

if [ -n "$WARMUP" ]; then
  echo "Pulling $image..."
  docker pull "$image"

  echo 'Starting Kotlin/Gradle smoke build and priming Gradle cache...'
  mkdir -p "$HOME/.gradle"
  java_options=$(proxy_options)
  (
    cd "$reproducer"
    docker run --rm --network host \
      -e "JAVA_TOOL_OPTIONS=$java_options" \
      -v "$PWD:/workspace" \
      -v "$HOME/.gradle:/home/gradle/.gradle" \
      -w /workspace "$image" gradle --no-daemon build
  ) >"$build_log" 2>&1 &
  build_pid=$!
  healthcheck
else
  echo "Gradle reproducer environment ready; cached image: $image"
fi
