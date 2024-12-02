#!/usr/bin/env sh

swiftc -parse-as-library "$1" -o /tmp/aoc && /tmp/aoc
