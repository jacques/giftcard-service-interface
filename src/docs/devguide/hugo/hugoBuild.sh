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
#docker run --name "hugo" -v ${BASE_DIR}/target/devguide/hugo:/src -v ${BASE_DIR}/target/devguide/site:/output -e "HUGO_THEME=hugo-material-docs" -e "HUGO_BASEURL=https://electrumpayments.github.io/giftcard-service-interface-docs/" jojomi/hugo
docker run --name "hugo" -v ${BASE_DIR}/target/devguide/hugo:/src -v ${BASE_DIR}/target/devguide/site:/output -e "HUGO_THEME=hugo-material-docs" -e "HUGO_BASEURL=/" jojomi/hugo

docker stop hugo &> /dev/null
docker rm hugo &> /dev/null

if [ -z $CI ]; then
  echo ''
  echo '  + Remove box and stop docker-machine [only required for mac]'
  echo ''

  docker-machine stop default
else
  sudo chown -R $(whoami):$(whoami) ${BASE_DIR}/target/devguide/site
fi
