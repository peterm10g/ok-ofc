#!/bin/bash

APP="ofc-worker"
PHOME=$(dirname `readlink -f "$0"`)
PHOME=$(dirname $PHOME)
CLASS_PATH=${PHOME}/conf:${PHOME}/lib/*:${wumart.api.classpath}

#JMX_PORT=`expr 8500`
JAVA_OPTS="-server -Xms1024m -Xmx1024m -Xmn384m -XX:MaxPermSize=128m \
-Xss256k -XX:+UseConcMarkSweepGC \
-XX:+UseParNewGC -XX:CMSFullGCsBeforeCompaction=5 \
-XX:+UseCMSCompactAtFullCollection \
-XX:+PrintGC -Xloggc:${PHOME}/logs/gc.log \
-Dlogs.dir=$PHOME/logs \
-Dsnowflake.node=1"
#-Djava.rmi.server.hostname=114.215.143.54 \
#-Dcom.sun.management.jmxremote \
#-Dcom.sun.management.jmxremote.port=$JMX_PORT \
#-Dcom.sun.management.jmxremote.authenticate=false \
#-Dcom.sun.management.jmxremote.ssl=false"

pid=`ps -eo pid,args | grep ${APP} | grep java | grep -v grep | awk '{print $1}'`
echo "-----------------${APP} pid is ${pid}-----------------"

if [ -n "$pid" ]
then
    kill -3 ${pid}
    kill ${pid} && sleep 3
    if [  -n "`ps -eo pid | grep $pid`" ]
    then
        kill -9 ${pid}
    fi
    echo "-----------------kill pid: ${pid}-----------------"
fi

if [  -n "`ps -eo pid | grep $pid`" ]
then
    echo "-----------------kill failure!-----------------"
fi

echo "start ...."
java $JAVA_OPTS -cp $CLASS_PATH com.lsh.ofc.worker.bootstrap.WorkerMain ${APP} >> ${PHOME}/logs/out.log 2>&1 &
