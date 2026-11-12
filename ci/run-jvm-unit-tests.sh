#!/usr/bin/env bash
# Runs standalone JVM unit tests shipped with the source (not Gradle androidTest).
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"

WORKDIR="$(mktemp -d)"
trap 'rm -rf "$WORKDIR"' EXIT

FOUND=0
for d in "$ROOT/tests_group2" "$ROOT/tests" "$ROOT/../tests"; do
  if [ -d "$d" ]; then
    find "$d" -name '*.java' -type f -exec cp {} "$WORKDIR/" \;
    FOUND=1
  fi
done

if [ "$FOUND" -eq 0 ]; then
  echo "No JVM test directories found — skip."
  exit 0
fi

cd "$WORKDIR"
# shellcheck disable=SC2046
set -- $(find . -maxdepth 1 -name '*.java' -type f)
if [ "$#" -eq 0 ]; then
  echo "No .java tests found — skip."
  exit 0
fi

echo "Compiling $# test source file(s)..."
javac -encoding UTF-8 "$@"

FAIL=0
PASS=0
for classfile in *.class; do
  [ -f "$classfile" ] || continue
  base="${classfile%.class}"
  case "$base" in
    *\$*) continue ;;
  esac
  if javap -public "$base" 2>/dev/null | grep -q 'public static void main'; then
    echo ">>> RUN $base"
    if java "$base"; then
      PASS=$((PASS + 1))
    else
      echo "FAILED: $base"
      FAIL=$((FAIL + 1))
    fi
  fi
done

echo "JVM tests: pass=$PASS fail=$FAIL"
if [ "$FAIL" -gt 0 ]; then
  exit 1
fi
