
package com.wumart.sap1.meipi.cus.maintain;

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
 *         &lt;element name="IBank" type="{urn:sap-com:document:sap:soap:functions:mc-style}Zbankinfo" minOccurs="0"/>
 *         &lt;element name="IKna1" type="{urn:sap-com:document:sap:soap:functions:mc-style}Kna1" minOccurs="0"/>
 *         &lt;element name="IKnb1" type="{urn:sap-com:document:sap:soap:functions:mc-style}Knb1" minOccurs="0"/>
 *         &lt;element name="IKnvv" type="{urn:sap-com:document:sap:soap:functions:mc-style}Knvv" minOccurs="0"/>
 *         &lt;element name="Mode" type="{urn:sap-com:document:sap:rfc:functions}char1" minOccurs="0"/>
 *         &lt;element name="TKnvv" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfKnvv"/>
 *         &lt;element name="TXknbk" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfFknbk" minOccurs="0"/>
 *         &lt;element name="TXknvk" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfFknvk" minOccurs="0"/>
 *         &lt;element name="TXknvp" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfFknvp"/>
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
    "iBank",
    "iKna1",
    "iKnb1",
    "iKnvv",
    "mode",
    "tKnvv",
    "tXknbk",
    "tXknvk",
    "tXknvp"
})
@XmlRootElement(name = "ZmdMeipiCustomerMaintain")
public class ZmdMeipiCustomerMaintain {

    @XmlElement(name = "IBank")
    protected Zbankinfo iBank;
    @XmlElement(name = "IKna1")
    protected Kna1 iKna1;
    @XmlElement(name = "IKnb1")
    protected Knb1 iKnb1;
    @XmlElement(name = "IKnvv")
    protected Knvv iKnvv;
    @XmlElement(name = "Mode")
    protected String mode;
    @XmlElement(name = "TKnvv", required = true)
    protected TableOfKnvv tKnvv;
    @XmlElement(name = "TXknbk")
    protected TableOfFknbk tXknbk;
    @XmlElement(name = "TXknvk")
    protected TableOfFknvk tXknvk;
    @XmlElement(name = "TXknvp", required = true)
    protected TableOfFknvp tXknvp;

    /**
     * Gets the value of the iBank property.
     * 
     * @return
     *     possible object is
     *     {@link Zbankinfo }
     *     
     */
    public Zbankinfo getIBank() {
        return iBank;
    }

    /**
     * Sets the value of the iBank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zbankinfo }
     *     
     */
    public void setIBank(Zbankinfo value) {
        this.iBank = value;
    }

    /**
     * Gets the value of the iKna1 property.
     * 
     * @return
     *     possible object is
     *     {@link Kna1 }
     *     
     */
    public Kna1 getIKna1() {
        return iKna1;
    }

    /**
     * Sets the value of the iKna1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Kna1 }
     *     
     */
    public void setIKna1(Kna1 value) {
        this.iKna1 = value;
    }

    /**
     * Gets the value of the iKnb1 property.
     * 
     * @return
     *     possible object is
     *     {@link Knb1 }
     *     
     */
    public Knb1 getIKnb1() {
        return iKnb1;
    }

    /**
     * Sets the value of the iKnb1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Knb1 }
     *     
     */
    public void setIKnb1(Knb1 value) {
        this.iKnb1 = value;
    }

    /**
     * Gets the value of the iKnvv property.
     * 
     * @return
     *     possible object is
     *     {@link Knvv }
     *     
     */
    public Knvv getIKnvv() {
        return iKnvv;
    }

    /**
     * Sets the value of the iKnvv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Knvv }
     *     
     */
    public void setIKnvv(Knvv value) {
        this.iKnvv = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
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

}
