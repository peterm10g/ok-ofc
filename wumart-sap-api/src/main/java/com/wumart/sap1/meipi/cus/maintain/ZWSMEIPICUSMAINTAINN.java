
package com.wumart.sap1.meipi.cus.maintain;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ZWS_MEIPI_CUS_MAINTAIN_N", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZWSMEIPICUSMAINTAINN {


    /**
     * 
     * @param tXknbk
     * @param iKnb1
     * @param tKnvv
     * @param iKna1
     * @param tXknvk
     * @param errorMsg
     * @param iBank
     * @param iKnvv
     * @param mode
     * @param flag
     * @param oKna1
     * @param tkunnr
     * @param tXknvp
     */
    @WebMethod(operationName = "ZmdMeipiCustomerMaintain")
    @RequestWrapper(localName = "ZmdMeipiCustomerMaintain", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.wumart.sap1.meipi.cus.maintain.ZmdMeipiCustomerMaintain")
    @ResponseWrapper(localName = "ZmdMeipiCustomerMaintainResponse", targetNamespace = "urn:sap-com:document:sap:soap:functions:mc-style", className = "com.wumart.sap1.meipi.cus.maintain.ZmdMeipiCustomerMaintainResponse")
    public void zmdMeipiCustomerMaintain(
        @WebParam(name = "IBank", targetNamespace = "")
        Zbankinfo iBank,
        @WebParam(name = "IKna1", targetNamespace = "")
        Kna1 iKna1,
        @WebParam(name = "IKnb1", targetNamespace = "")
        Knb1 iKnb1,
        @WebParam(name = "IKnvv", targetNamespace = "")
        Knvv iKnvv,
        @WebParam(name = "Mode", targetNamespace = "")
        String mode,
        @WebParam(name = "TKnvv", targetNamespace = "", mode = WebParam.Mode.INOUT)
        Holder<TableOfKnvv> tKnvv,
        @WebParam(name = "TXknbk", targetNamespace = "", mode = WebParam.Mode.INOUT)
        Holder<TableOfFknbk> tXknbk,
        @WebParam(name = "TXknvk", targetNamespace = "", mode = WebParam.Mode.INOUT)
        Holder<TableOfFknvk> tXknvk,
        @WebParam(name = "TXknvp", targetNamespace = "", mode = WebParam.Mode.INOUT)
        Holder<TableOfFknvp> tXknvp,
        @WebParam(name = "ErrorMsg", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> errorMsg,
        @WebParam(name = "Flag", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> flag,
        @WebParam(name = "OKna1", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Kna1> oKna1,
        @WebParam(name = "Tkunnr", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> tkunnr);

}
