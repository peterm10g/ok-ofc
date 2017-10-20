#!/bin/sh

#部署完成后是否启动
isRun=$2

echo $(cd "$(dirname $0)"; pwd)
BIN_PATH=$(cd "$(dirname $0)"; pwd)
APP_BASE=$(cd "$(dirname $0)/.."; pwd)

echo "部署包文件：$1"
PACKAGE_FILE=$(cd "$(dirname $1)"; pwd)/$(basename $1)
if [ ! -f "$PACKAGE_FILE" ]; then
    echo "部署包文件[$PACKAGE_FILE]不存在！"
    echo "中止部署！"
    exit 1
fi

TEMP_PATH=$APP_BASE/temp
if [ -d "$TEMP_PATH" ]; then
    echo "目录[$TEMP_PATH]已存在！"
    echo "中止部署！"
    exit 1
else
    mkdir $TEMP_PATH
fi

echo "部署开始..."
cd $APP_BASE
tar -zxvf $PACKAGE_FILE -C $TEMP_PATH

rm -rf $APP_BASE/lib $APP_BASE/conf

mv $TEMP_PATH/*/lib $TEMP_PATH/*/conf -t $APP_BASE

rm -rf $APP_BASE/lib/wumart-sap-api-*.jar

if [ $isRun = true ]
then
    echo "-----------开始启动-----------"
    sh $BIN_PATH/run.sh
    echo "-----------启动完成-----------"
else
    echo "-----------未启动-----------"
fi

rm -rf $TEMP_PATH
echo "部署完成..."