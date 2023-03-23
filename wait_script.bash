#!/usr/bin/env bash

: ${HOST=10.0.0.3}
: ${PORT=8080}

function testUrl() {
  url=$@
  if $url -ks -f -o /dev/null
  then
    return 0
  else
    return 1
  fi;
}

function waitForService() {
  url=$@
  echo -n "Wait for: $url... "
  n=0
  until testUrl $url
  do
    n=$((n + 1))
    if [[ $n == 100 ]]
    then
      echo " Give up"
      exit 1
    else
      echo -n ", retry #$n "
      sleep 3
    fi
  done
  echo "DONE, continues..."
}


echo "Start wait:" `date`

echo "HOST=${HOST}"
echo "PORT=${PORT}"

waitForService curl "http://$HOST:$PORT/calculate-sum?a=3\&b=2"

echo "End wait:" `date`


