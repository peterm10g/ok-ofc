MVN_PROFILE=$1
if [ ! -n "$MVN_PROFILE" ]; then
    echo "MVN PROFILE不能为空"
    echo "中止部署！"
    exit 1
fi
echo "MVN PROFILE: $MVN_PROFILE"
GIT_BRANCH=$2
if [ ! -n "$GIT_BRANCH" ]; then
    GIT_BRANCH=master
fi

echo "GIT BRANCH: $GIT_BRANCH"

cd $(cd "$(dirname $0)"; pwd)

echo `pwd`

mvn clean

git co $GIT_BRANCH
git pull

mvn package -Dmaven.test.skip=true -P $MVN_PROFILE
