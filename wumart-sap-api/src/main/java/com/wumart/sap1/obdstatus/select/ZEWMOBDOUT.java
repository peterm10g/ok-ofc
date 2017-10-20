
package com.wumart.sap1.obdstatus.select;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZEWM_OBD_OUT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZEWM_OBD_OUT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VBELN" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="POSNR" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="MATNR" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="LFIMG" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="MEINS" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="WAVWR" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="VGBEL" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="VGPOS" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="TU_NUM" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="TU_IDENT" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="ADD1" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="ADD2" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="ADD3" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="ADD4" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="ADD5" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZEWM_OBD_OUT", propOrder = {
    "vbeln",
    "posnr",
    "matnr",
    "lfimg",
    "meins",
    "wavwr",
    "vgbel",
    "vgpos",
    "tunum",
    "tuident",
    "add1",
    "add2",
    "add3",
    "add4",
    "add5"
})
public class ZEWMOBDOUT {

    @XmlElement(name = "VBELN", required = true)
    protected String vbeln;
    @XmlElement(name = "POSNR", required = true)
    protected String posnr;
    @XmlElement(name = "MATNR", required = true)
    protected String matnr;
    @XmlElement(name = "LFIMG", required = true)
    protected BigDecimal lfimg;
    @XmlElement(name = "MEINS", required = true)
    protected String meins;
    @XmlElement(name = "WAVWR", required = true)
    protected BigDecimal wavwr;
    @XmlElement(name = "VGBEL", required = true)
    protected String vgbel;
    @XmlElement(name = "VGPOS", required = true)
    protected String vgpos;
    @XmlElement(name = "TU_NUM", required = true)
    protected String tunum;
    @XmlElement(name = "TU_IDENT", required = true)
    protected String tuident;
    @XmlElement(name = "ADD1", required = true)
    protected String add1;
    @XmlElement(name = "ADD2", required = true)
    protected String add2;
    @XmlElement(name = "ADD3", required = true)
    protected String add3;
    @XmlElement(name = "ADD4", required = true)
    protected String add4;
    @XmlElement(name = "ADD5", required = true)
    protected String add5;

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
     * Gets the value of the posnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSNR() {
        return posnr;
    }

    /**
     * Sets the value of the posnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSNR(String value) {
        this.posnr = value;
    }

    /**
     * Gets the value of the matnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMATNR() {
        return matnr;
    }

    /**
     * Sets the value of the matnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMATNR(String value) {
        this.matnr = value;
    }

    /**
     * Gets the value of the lfimg property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLFIMG() {
        return lfimg;
    }

    /**
     * Sets the value of the lfimg property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLFIMG(BigDecimal value) {
        this.lfimg = value;
    }

    /**
     * Gets the value of the meins property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMEINS() {
        return meins;
    }

    /**
     * Sets the value of the meins property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMEINS(String value) {
        this.meins = value;
    }

    /**
     * Gets the value of the wavwr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWAVWR() {
        return wavwr;
    }

    /**
     * Sets the value of the wavwr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWAVWR(BigDecimal value) {
        this.wavwr = value;
    }

    /**
     * Gets the value of the vgbel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVGBEL() {
        return vgbel;
    }

    /**
     * Sets the value of the vgbel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVGBEL(String value) {
        this.vgbel = value;
    }

    /**
     * Gets the value of the vgpos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVGPOS() {
        return vgpos;
    }

    /**
     * Sets the value of the vgpos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVGPOS(String value) {
        this.vgpos = value;
    }

    /**
     * Gets the value of the tunum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTUNUM() {
        return tunum;
    }

    /**
     * Sets the value of the tunum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTUNUM(String value) {
        this.tunum = value;
    }

    /**
     * Gets the value of the tuident property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTUIDENT() {
        return tuident;
    }

    /**
     * Sets the value of the tuident property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTUIDENT(String value) {
        this.tuident = value;
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

    /**
     * Gets the value of the add4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADD4() {
        return add4;
    }

    /**
     * Sets the value of the add4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADD4(String value) {
        this.add4 = value;
    }

    /**
     * Gets the value of the add5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADD5() {
        return add5;
    }

    /**
     * Sets the value of the add5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADD5(String value) {
        this.add5 = value;
    }

}
