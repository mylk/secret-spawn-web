#!/bin/sh

LATEST_ARTIFACT=`ls -rt build/libs/ | tail -n 1`
echo "Starting \"build/libs/$LATEST_ARTIFACT\""
java -jar build/libs/$LATEST_ARTIFACT
