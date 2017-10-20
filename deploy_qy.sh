#!/bin/bash

#是否为首次部署:如为首次部署全部上传文件;否则只上传conf,lib两个文件夹
isFirstDeploy=$1
#是否备份
isBackup=$2
#是否重新启动
isRunAgain=$3
#部署完成后是否启动
isDeployRun=$4

#发布的APP
APPS=("ofc-provider" "ofc-worker")
#版本号
VERSION="1.0-SNAPSHOT"
#远程路径
REMOTES=("work@192.168.70.5" "work@192.168.70.2")

#节点数
declare -A NODES=()
NODES["ofc-provider"]=1
NODES["ofc-worker"]=1

#后缀名
SUFFIX=("01" "02")

#远程部署目录
REMOTE_PATH="/home/work/lsh-ofc"

#本地路径
ROOT=$(cd `dirname $0`; pwd)
echo "------------ 本地路径为 $ROOT ------------"

#发布打包环境
env="qypro"
echo "------------ 准备发布 $env ------------"

#循环部署代码
for REMOTE in ${REMOTES[@]}
do
    for APP in ${APPS[@]}
    do
        nodes=${NODES["$APP"]}
        for((i=0;i<$nodes;i++))
        do
            suffix=${SUFFIX[$i]}
            APP_NAME=$APP"-"$suffix

            APPROOT="${ROOT}/${APP}/target"

            if [ isRunAgain = true ]
            then
                echo "------------ 重新启动 ${REMOTE} ${APP_NAME} 开始  ------------"
                ssh $REMOTE "$REMOTE_PATH/$APP_NAME/bin/run.sh"
                continue
                echo "------------ 重新启动 ${REMOTE} ${APP_NAME} 结束  ------------"
            fi

            if [ $isBackup = true ]
            then
                echo "------------ 备份 ${REMOTE} ${APP_NAME} 开始  ------------"

                timeStr=$(date +%Y%m%d%H%M%S)
                #ssh $REMOTE "rsync -avzP --exclude=log --exclude=logs --exclude=out.log $REMOTE_PATH/${APP}/* $REMOTE_PATH/${APP}.bak.${timeStr}"
                ssh $REMOTE "$REMOTE_PATH/$APP_NAME/bin/backup.sh"

                echo "------------ 备份 ${REMOTE} ${APP_NAME} 完成 ------------"
            fi

            if [ $isFirstDeploy = true ]
            then
                echo "------------ 部署 ${REMOTE} ${APP_NAME} 开始  ------------"
                #解压到APPROOT
                tar -zxvf $APPROOT/$APP-$VERSION-$env.tar.gz -C $APPROOT/

                echo "------------ 上传 ${REMOTE} ${APP_NAME} 开始 ------------"
                rsync -avzP $APPROOT/$APP-$VERSION-$env/ $REMOTE:$REMOTE_PATH/$APP_NAME/
                echo "------------ 上传 ${REMOTE} ${APP_NAME} 成功 ------------"

                rm -rf $APPROOT/$APP-$VERSION-$env
                echo "------------ 部署 ${REMOTE} ${APP_NAME} 结束  ------------"
            else
                echo "------------ 部署 ${REMOTE} ${APP_NAME} 开始  ------------"

                rsync -avzP $APPROOT/$APP-$VERSION-$env.tar.gz $REMOTE:$REMOTE_PATH/
                ssh $REMOTE "$REMOTE_PATH/$APP_NAME/bin/deploy.sh $REMOTE_PATH/$APP-$VERSION-$env.tar.gz $isDeployRun"
                ssh $REMOTE "rm -rf $REMOTE_PATH/$APP-$VERSION-$env.tar.gz"

                echo "------------ 部署 ${REMOTE} ${APP_NAME} 结束  ------------"
            fi
        done
    done
done

echo "------------ 执行完毕 ------------"