#!/bin/bash
PROJECT_HOME=spring-boot-quartz
TARGET_DIR=/ocean/docker/$PROJECT_HOME/
if [ ! -d $TARGET_DIR  ];then
  mkdir $TARGET_DIR
else
  echo $TARGET_DIR exist
fi
BIN_DIR=`pwd`
echo $BIN_DIR
cp $BIN_DIR/quartz/target/*.jar $TARGET_DIR
cp $BIN_DIR/quartz/src/main/docker/Dockerfile $TARGET_DIR
cd $TARGET_DIR
docker build -t ocean/$PROJECT_HOME:1.0-SNAPSHOT .
docker run -d -p 8081:8081 ocean/$PROJECT_HOME:1.0-SNAPSHOT


