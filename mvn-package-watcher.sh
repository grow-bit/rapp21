#!/usr/bin/env sh

while inotifywait --exclude '^\.\/target' -q -r -e close_write .; do
    mvn package -Dmaven.test.skip=true
done