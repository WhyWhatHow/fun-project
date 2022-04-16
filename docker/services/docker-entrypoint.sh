#!/bin/bash
# docker-entrypoint.sh
# link: https://stackoverflow.com/questions/69718900/how-can-i-change-heap-size-when-i-use-docker-compose-and-dockerfile-to-create-a
# print debug information
set -ex

#########################################################
# Set JVM memory options if set as environment variables.
#hint:if 语句格式需要正确,不然 命令会出现残缺
#########################################################
if [ -n "${JVM_XMS}" ]; then
  JAVA_OPTS="$JAVA_OPTS -Xms$JVM_XMS "
fi
if [ -n "$JVM_XMX" ]; then
  JAVA_OPTS="$JAVA_OPTS -Xmx$JVM_XMX "
fi
# shellcheck disable=SC1072
if [ -n "$JVM_GC" ]; then
  JAVA_OPTS="$JAVA_OPTS -XX:$JVM_GC"
fi
if [ -n "$JAVA_RANDOM" ]; then
  JAVA_OPTS="$JAVA_OPTS $JAVA_RANDOM"
fi
#########################################################
# set params
#########################################################

##shellcheck disable=SC1073 spring-cloud
if [ -n  "$SERVER_PORT" ]; then
    PARAMS="$PARAMS --server.port=$SERVER_PORT "
fi
if [ -n  "$SPRING_PROFILES_ACTIVE" ]; then
    PARAMS="$PARAMS --spring.profiles.active=$SPRING_PROFILES_ACTIVE "
fi
#if [ -n  "$NACOS_ADDR" ]; then
#    PARAMS="$PARAMS --spring.cloud.nacos.server-addr=$NACOS_ADDR"
#fi
if [ -n "$OTHER_PARAMS" ];  then
    PARAMS="$PARAMS $OTHER_PARAMS"
fi
# Then run the main container command.
exec  java $JAVA_OPTS -jar  app.jar $PARAMS