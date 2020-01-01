#!/usr/bin/env sh

mvn package -Dmaven.test.skip=true

while inotifywait --exclude '^\.\/target' --exclude '^\.\/\.git' -q -r -e close_write .; do
    mvn package -Dmaven.test.skip=true
done