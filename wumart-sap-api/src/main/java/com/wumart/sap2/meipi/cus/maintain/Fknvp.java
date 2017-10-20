
package com.wumart.sap2.meipi.cus.maintain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Fknvp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Fknvp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/>
 *         &lt;element name="Kunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Vkorg" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Vtweg" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Spart" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Parvw" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Parza" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="Kunn2" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Lifnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/>
 *         &lt;element name="Parnr" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="Knref" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Defpa" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fknvp", propOrder = {
    "mandt",
    "kunnr",
    "vkorg",
    "vtweg",
    "spart",
    "parvw",
    "parza",
    "kunn2",
    "lifnr",
    "pernr",
    "parnr",
    "knref",
    "defpa",
    "kz"
})
public class Fknvp {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "Kunnr", required = true)
    protected String kunnr;
    @XmlElement(name = "Vkorg", required = true)
    protected String vkorg;
    @XmlElement(name = "Vtweg", required = true)
    protected String vtweg;
    @XmlElement(name = "Spart", required = true)
    protected String spart;
    @XmlElement(name = "Parvw", required = true)
    protected String parvw;
    @XmlElement(name = "Parza", required = true)
    protected String parza;
    @XmlElement(name = "Kunn2", required = true)
    protected String kunn2;
    @XmlElement(name = "Lifnr", required = true)
    protected String lifnr;
    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "Parnr", required = true)
    protected String parnr;
    @XmlElement(name = "Knref", required = true)
    protected String knref;
    @XmlElement(name = "Defpa", required = true)
    protected String defpa;
    @XmlElement(name = "Kz", required = true)
    protected String kz;

    /**
     * Gets the value of the mandt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMandt() {
        return mandt;
    }

    /**
     * Sets the value of the mandt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMandt(String value) {
        this.mandt = value;
    }

    /**
     * Gets the value of the kunnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKunnr() {
        return kunnr;
    }

    /**
     * Sets the value of the kunnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKunnr(String value) {
        this.kunnr = value;
    }

    /**
     * Gets the value of the vkorg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVkorg() {
        return vkorg;
    }

    /**
     * Sets the value of the vkorg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVkorg(String value) {
        this.vkorg = value;
    }

    /**
     * Gets the value of the vtweg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVtweg() {
        return vtweg;
    }

    /**
     * Sets the value of the vtweg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVtweg(String value) {
        this.vtweg = value;
    }

    /**
     * Gets the value of the spart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpart() {
        return spart;
    }

    /**
     * Sets the value of the spart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpart(String value) {
        this.spart = value;
    }

    /**
     * Gets the value of the parvw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParvw() {
        return parvw;
    }

    /**
     * Sets the value of the parvw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParvw(String value) {
        this.parvw = value;
    }

    /**
     * Gets the value of the parza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParza() {
        return parza;
    }

    /**
     * Sets the value of the parza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParza(String value) {
        this.parza = value;
    }

    /**
     * Gets the value of the kunn2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKunn2() {
        return kunn2;
    }

    /**
     * Sets the value of the kunn2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKunn2(String value) {
        this.kunn2 = value;
    }

    /**
     * Gets the value of the lifnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifnr() {
        return lifnr;
    }

    /**
     * Sets the value of the lifnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifnr(String value) {
        this.lifnr = value;
    }

    /**
     * Gets the value of the pernr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPernr() {
        return pernr;
    }

    /**
     * Sets the value of the pernr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPernr(String value) {
        this.pernr = value;
    }

    /**
     * Gets the value of the parnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParnr() {
        return parnr;
    }

    /**
     * Sets the value of the parnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParnr(String value) {
        this.parnr = value;
    }

    /**
     * Gets the value of the knref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnref() {
        return knref;
    }

    /**
     * Sets the value of the knref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnref(String value) {
        this.knref = value;
    }

    /**
     * Gets the value of the defpa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefpa() {
        return defpa;
    }

    /**
     * Sets the value of the defpa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefpa(String value) {
        this.defpa = value;
    }

    /**
     * Gets the value of the kz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKz() {
        return kz;
    }

    /**
     * Sets the value of the kz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKz(String value) {
        this.kz = value;
    }

}
