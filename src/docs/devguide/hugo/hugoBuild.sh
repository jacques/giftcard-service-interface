#!/usr/bin/env bash

# Run a hugo build from the comfort of a docker container
#
# ----------------------------------------------------------------------------------------------------------------------
# usage:
#
#   hugoBuild.sh BASE_DIR
#
#     BASE_DIR: maven ${basedir} property, the base dir of this project
#


BASE_DIR=$1

if [ -z $CI ]; then
  echo ''
  echo '  + Ensure docker-machine is started [only required for mac]'
  echo ''

  docker-machine start default
  if [ $(docker-machine status default) != Running ]; then
    echo 'docker-machine not running - failing build'
    exit 1
  fi

  eval $(docker-machine env default)
  if [[ $? -ne 0 ]]; then
    echo 'command finished with non zero exit code - failing build'
    exit 1
  fi
fi

echo ''
echo ''
echo '  + Running hugo build'
echo ''
docker create -v /src -v /output --name configs alpine:3.4 /bin/true
docker cp ${BASE_DIR}/target/devguide/hugo/. configs:/src
docker run \
#  --volumes-from configs \
#  --name hugo \
#  -e "HUGO_THEME=hugo-material-docs" \
#  -e "HUGO_BASEURL=https://electrumpayments.github.io/giftcard-service-interface-docs/" \
#  jojomi/hugo:0.29
docker run \
  --volumes-from configs \
  --name hugo \
  -e "HUGO_THEME=hugo-material-docs" \
  -e "HUGO_BASEURL=/" \
  jojomi/hugo:0.29
docker cp hugo:/output/. ${BASE_DIR}/target/devguide/site


docker stop hugo &> /dev/null
docker rm hugo &> /dev/null
docker rm configs &> /dev/null


if [ -z $CI ]; then
  echo ''
  echo '  + Remove box and stop docker-machine [only required for mac]'
  echo ''

  docker-machine stop default
else
  sudo chown -R $(whoami):$(whoami) ${BASE_DIR}/target/devguide/site
fi
