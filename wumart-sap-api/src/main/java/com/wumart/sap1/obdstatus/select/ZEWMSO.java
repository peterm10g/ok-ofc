
package com.wumart.sap1.obdstatus.select;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZEWM_SO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZEWM_SO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VBELN" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="AUART" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="ERDAT" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="VDATU" type="{urn:sap-com:document:sap:rfc:functions}date10"/>
 *         &lt;element name="VSBED" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="BSTNK" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="TEXT" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="ADD1" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ADD2" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="ADD3" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZEWM_SO", propOrder = {
    "vbeln",
    "auart",
    "erdat",
    "vdatu",
    "vsbed",
    "bstnk",
    "text",
    "add1",
    "add2",
    "add3"
})
public class ZEWMSO {

    @XmlElement(name = "VBELN", required = true)
    protected String vbeln;
    @XmlElement(name = "AUART", required = true)
    protected String auart;
    @XmlElement(name = "ERDAT", required = true)
    protected String erdat;
    @XmlElement(name = "VDATU", required = true)
    protected String vdatu;
    @XmlElement(name = "VSBED", required = true)
    protected String vsbed;
    @XmlElement(name = "BSTNK", required = true)
    protected String bstnk;
    @XmlElement(name = "TEXT", required = true)
    protected String text;
    @XmlElement(name = "ADD1", required = true)
    protected String add1;
    @XmlElement(name = "ADD2", required = true)
    protected String add2;
    @XmlElement(name = "ADD3", required = true)
    protected String add3;

    /**
     * Gets the value of the vbeln property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVBELN() {
        return vbeln;
    }

    /**
     * Sets the value of the vbeln property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVBELN(String value) {
        this.vbeln = value;
    }

    /**
     * Gets the value of the auart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAUART() {
        return auart;
    }

    /**
     * Sets the value of the auart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAUART(String value) {
        this.auart = value;
    }

    /**
     * Gets the value of the erdat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getERDAT() {
        return erdat;
    }

    /**
     * Sets the value of the erdat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setERDAT(String value) {
        this.erdat = value;
    }

    /**
     * Gets the value of the vdatu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVDATU() {
        return vdatu;
    }

    /**
     * Sets the value of the vdatu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVDATU(String value) {
        this.vdatu = value;
    }

    /**
     * Gets the value of the vsbed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVSBED() {
        return vsbed;
    }

    /**
     * Sets the value of the vsbed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVSBED(String value) {
        this.vsbed = value;
    }

    /**
     * Gets the value of the bstnk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBSTNK() {
        return bstnk;
    }

    /**
     * Sets the value of the bstnk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBSTNK(String value) {
        this.bstnk = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTEXT() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTEXT(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the add1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADD1() {
        return add1;
    }

    /**
     * Sets the value of the add1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADD1(String value) {
        this.add1 = value;
    }

    /**
     * Gets the value of the add2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADD2() {
        return add2;
    }

    /**
     * Sets the value of the add2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADD2(String value) {
        this.add2 = value;
    }

    /**
     * Gets the value of the add3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADD3() {
        return add3;
    }

    /**
     * Sets the value of the add3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADD3(String value) {
        this.add3 = value;
    }

}
