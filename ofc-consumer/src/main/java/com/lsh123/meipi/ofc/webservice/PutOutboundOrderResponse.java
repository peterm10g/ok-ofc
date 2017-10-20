
package com.lsh123.meipi.ofc.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for putOutboundOrderResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="putOutboundOrderResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ret" type="{http://webservice.ofc.meipi.lsh123.com/}CallRet" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "putOutboundOrderResponse", propOrder = {
    "ret"
})
public class PutOutboundOrderResponse {

    protected CallRet ret;

    /**
     * Gets the value of the ret property.
     * 
     * @return
     *     possible object is
     *     {@link CallRet }
     *     
     */
    public CallRet getRet() {
        return ret;
    }

    /**
     * Sets the value of the ret property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallRet }
     *     
     */
    public void setRet(CallRet value) {
        this.ret = value;
    }

}
