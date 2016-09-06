#!/usr/bin/env bash

# swagger-maven-plugin generates a nice markdown file from our swagger spec for us
#
# This script processes this file before it can be used by hugo to be turned into html. It does the following:
#   1) Copy hugo files to target so that we can add some "build time" content
#   2) Add swagger.yaml to hugo static content
#   3) Chop the markdown file into separate files for each of the summary stuff at the top, the operations, and the definitions
#   4) Generate a markdown file that contains the swagger spec "source"
#   5) Add hugo front matter
#
# ----------------------------------------------------------------------------------------------------------------------
# usage:
#
#   hugoPreprocessing.sh BASE_DIR
#
#     BASE_DIR: maven ${basedir} property, the base dir of this project
#


BASE_DIR=$1


if [ ! -f "${BASE_DIR}/target/devguide/specification.md" ]; then
  echo "Specification markdown file not found"
  exit 1
fi

if [ ! -f "${BASE_DIR}/target/swagger/swagger.yaml" ]; then
  echo "Swagger file not found"
  exit 1
fi


echo '1) Copy hugo files to target so that we can add some "build time" content'
echo ''

cp -r "${BASE_DIR}/src/docs/devguide/hugo/site" "${BASE_DIR}/target/devguide/hugo"


echo '2) Add swagger.yaml to hugo static content'
echo ''

mkdir -p "${BASE_DIR}/target/devguide/hugo/static/specification/swagger/"
cp "${BASE_DIR}/target/swagger/swagger.yaml" "${BASE_DIR}/target/devguide/hugo/static/specification/swagger/swagger.yaml"


echo '3) Chop the markdown file into separate files for each of the summary stuff at the top, the operations, and the definitions'
echo ''

mkdir "${BASE_DIR}/target/devguide/hugo/content/specification"
sed -e '/# Operations/,$d' "${BASE_DIR}/target/devguide/specification.md" > "${BASE_DIR}/target/devguide/hugo/content/specification/introduction.md"
sed -n -e '/^# Operations$/,/^# Definitions$/{ /^# Operations$/d; /^# Definitions$/d; p; }' "${BASE_DIR}/target/devguide/specification.md" > "${BASE_DIR}/target/devguide/hugo/content/specification/operations.md"
sed -e '1,/# Definitions/d' "${BASE_DIR}/target/devguide/specification.md" > "${BASE_DIR}/target/devguide/hugo/content/specification/definitions.md"


echo '4) Generate a markdown file that contains the swagger spec "source"'
echo ''

awk 'BEGIN {
        while ((getline line < ARGV[1]) > 0) {file1 = file1 nl line; nl = "\n"};
        close (ARGV[1]); nl = "";
        ARGV[1] = "" }
      { gsub("{{placeholder}}", file1);
        print }' "${BASE_DIR}/target/swagger/swagger.yaml" "${BASE_DIR}/src/docs/devguide/hugo/swagger-template.md" > "${BASE_DIR}/target/devguide/hugo/content/specification/swagger.md"


echo '5) Add hugo front matter'
echo ''

addFrontMatter () {
  TITLE=$1
  WEIGHT=$2
  PARENT=$3
  FILE=$4

  echo "---\ntitle: ${TITLE}\nmenu:\n  main:\n    weight: ${WEIGHT}\n    parent: ${PARENT}\n---\n\n$(cat ${FILE})" > "${FILE}"
}

#               Title        Weight Parent          File
#-----------------------------------------------------------------------------------------------------------------------
addFrontMatter "Introduction" "81" "Specification" "${BASE_DIR}/target/devguide/hugo/content/specification/introduction.md"
addFrontMatter "Operations"   "82" "Specification" "${BASE_DIR}/target/devguide/hugo/content/specification/operations.md"
addFrontMatter "Definitions"  "83" "Specification" "${BASE_DIR}/target/devguide/hugo/content/specification/definitions.md"
addFrontMatter "Swagger"      "84" "Specification" "${BASE_DIR}/target/devguide/hugo/content/specification/swagger.md"
