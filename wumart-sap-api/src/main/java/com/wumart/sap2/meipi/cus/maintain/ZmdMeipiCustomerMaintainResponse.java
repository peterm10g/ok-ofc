
package com.wumart.sap2.meipi.cus.maintain;

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
 *         &lt;element name="ErrorMsg" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Flag" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="OKna1" type="{urn:sap-com:document:sap:soap:functions:mc-style}Kna1"/>
 *         &lt;element name="TKnvv" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfKnvv"/>
 *         &lt;element name="TXknbk" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfFknbk"/>
 *         &lt;element name="TXknvk" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfFknvk"/>
 *         &lt;element name="TXknvp" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfFknvp"/>
 *         &lt;element name="Tkunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
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
    "errorMsg",
    "flag",
    "oKna1",
    "tKnvv",
    "tXknbk",
    "tXknvk",
    "tXknvp",
    "tkunnr"
})
@XmlRootElement(name = "ZmdMeipiCustomerMaintainResponse")
public class ZmdMeipiCustomerMaintainResponse {

    @XmlElement(name = "ErrorMsg", required = true)
    protected String errorMsg;
    @XmlElement(name = "Flag", required = true)
    protected String flag;
    @XmlElement(name = "OKna1", required = true)
    protected Kna1 oKna1;
    @XmlElement(name = "TKnvv", required = true)
    protected TableOfKnvv tKnvv;
    @XmlElement(name = "TXknbk", required = true)
    protected TableOfFknbk tXknbk;
    @XmlElement(name = "TXknvk", required = true)
    protected TableOfFknvk tXknvk;
    @XmlElement(name = "TXknvp", required = true)
    protected TableOfFknvp tXknvp;
    @XmlElement(name = "Tkunnr", required = true)
    protected String tkunnr;

    /**
     * Gets the value of the errorMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Sets the value of the errorMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMsg(String value) {
        this.errorMsg = value;
    }

    /**
     * Gets the value of the flag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Sets the value of the flag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlag(String value) {
        this.flag = value;
    }

    /**
     * Gets the value of the oKna1 property.
     * 
     * @return
     *     possible object is
     *     {@link Kna1 }
     *     
     */
    public Kna1 getOKna1() {
        return oKna1;
    }

    /**
     * Sets the value of the oKna1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Kna1 }
     *     
     */
    public void setOKna1(Kna1 value) {
        this.oKna1 = value;
    }

    /**
     * Gets the value of the tKnvv property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfKnvv }
     *     
     */
    public TableOfKnvv getTKnvv() {
        return tKnvv;
    }

    /**
     * Sets the value of the tKnvv property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfKnvv }
     *     
     */
    public void setTKnvv(TableOfKnvv value) {
        this.tKnvv = value;
    }

    /**
     * Gets the value of the tXknbk property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfFknbk }
     *     
     */
    public TableOfFknbk getTXknbk() {
        return tXknbk;
    }

    /**
     * Sets the value of the tXknbk property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfFknbk }
     *     
     */
    public void setTXknbk(TableOfFknbk value) {
        this.tXknbk = value;
    }

    /**
     * Gets the value of the tXknvk property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfFknvk }
     *     
     */
    public TableOfFknvk getTXknvk() {
        return tXknvk;
    }

    /**
     * Sets the value of the tXknvk property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfFknvk }
     *     
     */
    public void setTXknvk(TableOfFknvk value) {
        this.tXknvk = value;
    }

    /**
     * Gets the value of the tXknvp property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfFknvp }
     *     
     */
    public TableOfFknvp getTXknvp() {
        return tXknvp;
    }

    /**
     * Sets the value of the tXknvp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfFknvp }
     *     
     */
    public void setTXknvp(TableOfFknvp value) {
        this.tXknvp = value;
    }

    /**
     * Gets the value of the tkunnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTkunnr() {
        return tkunnr;
    }

    /**
     * Sets the value of the tkunnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTkunnr(String value) {
        this.tkunnr = value;
    }

}
