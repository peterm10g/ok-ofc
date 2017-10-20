WORKSPACE=$1
if [ ! -d "$WORKSPACE" ]; then
    echo "工作目录不存在，中止！"
    exit 1
fi

if [ ! -f "$WORKSPACE/wsdl/1/ZWS_MEIPI_CUS_MAINTAIN_N.wsdl" ] || [ ! -f "$WORKSPACE/wsdl/2/ZWS_MEIPI_CUS_MAINTAIN_N.wsdl" ]; then
    echo "ZWS_MEIPI_CUS_MAINTAIN_N.wsdl不存在，中止！"
    exit 1
fi

if [ ! -f "$WORKSPACE/wsdl/1/ZWS_MEIPI_CREATESO.wsdl" ] || [ ! -f "$WORKSPACE/wsdl/2/ZWS_MEIPI_CREATESO.wsdl" ]; then
    echo "ZWS_MEIPI_CREATESO.wsdl不存在，中止！"
    exit 1
fi

if [ ! -f "$WORKSPACE/wsdl/1/ZMP_OBDSTATUS_SELECT.wsdl" ] || [ ! -f "$WORKSPACE/wsdl/2/ZMP_OBDSTATUS_SELECT.wsdl" ]; then
    echo "ZMP_OBDSTATUS_SELECT.wsdl不存在，中止！"
    exit 1
fi

if [ -d "$WORKSPACE/src" ]; then
    echo "清空源码文件：$WORKSPACE/src/*"
    rm -rf "$WORKSPACE/src/*"
else
    echo "创建源码目录：$WORKSPACE/src"
    mkdir "$WORKSPACE/src"
fi

if [ -d "$WORKSPACE/bin" ]; then
    echo "清空class文件：$WORKSPACE/bin/*"
    rm -rf "$WORKSPACE/bin/*"
else
    echo "创建class目录：$WORKSPACE/bin"
    mkdir "$WORKSPACE/bin"
fi

wsimport -extension -p com.wumart.sap1.meipi.createso     -s $WORKSPACE/src -d $WORKSPACE/bin $WORKSPACE/wsdl/1/ZWS_MEIPI_CREATESO.wsdl
wsimport -extension -p com.wumart.sap1.meipi.cus.maintain -s $WORKSPACE/src -d $WORKSPACE/bin $WORKSPACE/wsdl/1/ZWS_MEIPI_CUS_MAINTAIN_N.wsdl
wsimport -extension -p com.wumart.sap1.obdstatus.select   -s $WORKSPACE/src -d $WORKSPACE/bin $WORKSPACE/wsdl/1/ZMP_OBDSTATUS_SELECT.wsdl

wsimport -extension -p com.wumart.sap2.meipi.createso     -s $WORKSPACE/src -d $WORKSPACE/bin $WORKSPACE/wsdl/2/ZWS_MEIPI_CREATESO.wsdl
wsimport -extension -p com.wumart.sap2.meipi.cus.maintain -s $WORKSPACE/src -d $WORKSPACE/bin $WORKSPACE/wsdl/2/ZWS_MEIPI_CUS_MAINTAIN_N.wsdl
wsimport -extension -p com.wumart.sap2.obdstatus.select   -s $WORKSPACE/src -d $WORKSPACE/bin $WORKSPACE/wsdl/2/ZMP_OBDSTATUS_SELECT.wsdl
