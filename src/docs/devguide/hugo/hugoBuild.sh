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

echo ''
echo ''
echo '  + Running hugo build'
echo ''

docker create -v /src --name src-vol alpine:3.4 /bin/true

# Copy the content of the hugo directory into the /src directory of the volume container
# The /. is required otherwise it creates a /src/hugo directory
docker cp "${BASE_DIR}/target/devguide/hugo/." src-vol:/src

if [ -z $CI ]; then
    echo "local"
    docker run \
      --volumes-from src-vol \
      --name hugo \
      -e "HUGO_THEME=hugo-material-docs" \
      -e "HUGO_BASEURL=/" \
      jojomi/hugo:0.29
else
    docker run \
      --volumes-from src-vol \
      --name hugo \
      -e "HUGO_THEME=hugo-material-docs" \
      -e "HUGO_BASEURL=https://electrumpayments.github.io/giftcard-service-interface-docs/" \
      jojomi/hugo:0.29
fi

docker cp hugo:/output/. "${BASE_DIR}/target/devguide/site"

docker stop src-vol &> /dev/null
docker rm src-vol &> /dev/null

docker stop hugo &> /dev/null
docker rm hugo &> /dev/null

if [ ! -z $CI ]; then
  echo ''
  echo "Setting ownership of Hugo output"
  echo "BASE_DIR: ${BASE_DIR}"
  sudo chown -R $(whoami):$(whoami) ${BASE_DIR}/target/devguide/site
fi