
package com.wumart.sap1.obdstatus.select;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IT_HEAD" type="{urn:sap-com:document:sap:rfc:functions}ZEWM_TT_SO" minOccurs="0"/>
 *         &lt;element name="IT_ITEM" type="{urn:sap-com:document:sap:rfc:functions}ZEWM_TT_OBD_OUT" minOccurs="0"/>
 *         &lt;element name="RETURN" type="{urn:sap-com:document:sap:rfc:functions}TABLE_OF_BAPIRET2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ithead",
    "ititem",
    "_return"
})
@XmlRootElement(name = "ZMP_OBDSTATUS_SELECTResponse")
public class ZMPOBDSTATUSSELECTResponse {

    @XmlElement(name = "IT_HEAD")
    protected ZEWMTTSO ithead;
    @XmlElement(name = "IT_ITEM")
    protected ZEWMTTOBDOUT ititem;
    @XmlElement(name = "RETURN")
    protected TABLEOFBAPIRET2 _return;

    /**
     * Gets the value of the ithead property.
     * 
     * @return
     *     possible object is
     *     {@link ZEWMTTSO }
     *     
     */
    public ZEWMTTSO getITHEAD() {
        return ithead;
    }

    /**
     * Sets the value of the ithead property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZEWMTTSO }
     *     
     */
    public void setITHEAD(ZEWMTTSO value) {
        this.ithead = value;
    }

    /**
     * Gets the value of the ititem property.
     * 
     * @return
     *     possible object is
     *     {@link ZEWMTTOBDOUT }
     *     
     */
    public ZEWMTTOBDOUT getITITEM() {
        return ititem;
    }

    /**
     * Sets the value of the ititem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZEWMTTOBDOUT }
     *     
     */
    public void setITITEM(ZEWMTTOBDOUT value) {
        this.ititem = value;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TABLEOFBAPIRET2 }
     *     
     */
    public TABLEOFBAPIRET2 getRETURN() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TABLEOFBAPIRET2 }
     *     
     */
    public void setRETURN(TABLEOFBAPIRET2 value) {
        this._return = value;
    }

}
