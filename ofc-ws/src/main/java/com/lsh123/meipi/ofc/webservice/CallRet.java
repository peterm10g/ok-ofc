package com.lsh123.meipi.ofc.webservice;

import javax.xml.bind.annotation.*;

/**
 * Created by zengwenjun on 16/4/9.
 */

@XmlRootElement(name="CallRet" )
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="CallRet", propOrder = {
        "code",
        "err_msg",
        "detail",
        "temp1",
        "temp2",
        "temp3",
        "temp4",
})


public class CallRet {
    @XmlElement(name = "code")
    public String code;
    @XmlElement(name = "err_msg")
    public String err_msg;
    @XmlElement(name = "detail")
    public String detail;
    @XmlElement(name = "temp1")
    public String temp1;
    @XmlElement(name = "temp2")
    public String temp2;
    @XmlElement(name = "temp3")
    public String temp3;
    @XmlElement(name = "temp4")
    public String temp4;


    public CallRet(){
        code = "";
        err_msg = "";
        detail = "";
        temp1 = "";
        temp2 = "";
        temp3 = "";
        temp4 = "";
    }
}
