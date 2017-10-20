#!/bin/sh
echo $(cd "$(dirname $0)"; pwd)
APP_BASE=$(cd "$(dirname $0)/.."; pwd)
APP_NAME=ofc-provider
FILE_NAME=$APP_NAME-`date "+%Y%m%d%H%M%S"`.tar.gz
BACKUP_PATH=$APP_BASE/backup
if [ ! -d $BACKUP_PATH ]; then
    echo "新建备份目录：$BACKUP_PATH"
    mkdir $BACKUP_PATH
fi

echo "备份开始..."
echo "备份文件:$BACKUP_PATH/$FILE_NAME"
cd $APP_BASE
tar -zcvf $FILE_NAME conf/ lib/
mv $APP_BASE/$FILE_NAME $BACKUP_PATH
echo "备份完成..."
