
package com.wumart.sap2.obdstatus.select;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
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
@WebService(name = "ZMP_OBDSTATUS_SELECT", targetNamespace = "urn:sap-com:document:sap:rfc:functions")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ZMPOBDSTATUSSELECT {


    /**
     * 
     * @param _return
     * @param itITEM
     * @param itHEAD
     * @return
     *     returns com.wumart.sap2.obdstatus.select.TABLEOFBAPIRET2
     */
    @WebMethod(operationName = "ZMP_OBDSTATUS_SELECT", action = "urn:sap-com:document:sap:rfc:functions:ZMP_OBDSTATUS_SELECT:ZMP_OBDSTATUS_SELECTRequest")
    @WebResult(name = "RETURN", targetNamespace = "")
    @RequestWrapper(localName = "ZMP_OBDSTATUS_SELECT", targetNamespace = "urn:sap-com:document:sap:rfc:functions", className = "com.wumart.sap2.obdstatus.select.ZMPOBDSTATUSSELECT_Type")
    @ResponseWrapper(localName = "ZMP_OBDSTATUS_SELECTResponse", targetNamespace = "urn:sap-com:document:sap:rfc:functions", className = "com.wumart.sap2.obdstatus.select.ZMPOBDSTATUSSELECTResponse")
    public TABLEOFBAPIRET2 zmpOBDSTATUSSELECT(
        @WebParam(name = "IT_HEAD", targetNamespace = "", mode = WebParam.Mode.INOUT)
        Holder<ZEWMTTSO> itHEAD,
        @WebParam(name = "IT_ITEM", targetNamespace = "", mode = WebParam.Mode.INOUT)
        Holder<ZEWMTTOBDOUT> itITEM,
        @WebParam(name = "RETURN", targetNamespace = "")
        TABLEOFBAPIRET2 _return);

}
