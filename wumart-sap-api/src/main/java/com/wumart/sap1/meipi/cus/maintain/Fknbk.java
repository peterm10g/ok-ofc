
package com.wumart.sap1.meipi.cus.maintain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Fknbk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Fknbk">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/>
 *         &lt;element name="Kunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Banks" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Bankl" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="Bankn" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="Bkont" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Bvtyp" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Xezer" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Bkref" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Koinh" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="EbppAccname" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="EbppBvstatus" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kovon" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Kobis" type="{urn:sap-com:document:sap:rfc:functions}date"/>
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
@XmlType(name = "Fknbk", propOrder = {
    "mandt",
    "kunnr",
    "banks",
    "bankl",
    "bankn",
    "bkont",
    "bvtyp",
    "xezer",
    "bkref",
    "koinh",
    "ebppAccname",
    "ebppBvstatus",
    "kovon",
    "kobis",
    "kz"
})
public class Fknbk {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "Kunnr", required = true)
    protected String kunnr;
    @XmlElement(name = "Banks", required = true)
    protected String banks;
    @XmlElement(name = "Bankl", required = true)
    protected String bankl;
    @XmlElement(name = "Bankn", required = true)
    protected String bankn;
    @XmlElement(name = "Bkont", required = true)
    protected String bkont;
    @XmlElement(name = "Bvtyp", required = true)
    protected String bvtyp;
    @XmlElement(name = "Xezer", required = true)
    protected String xezer;
    @XmlElement(name = "Bkref", required = true)
    protected String bkref;
    @XmlElement(name = "Koinh", required = true)
    protected String koinh;
    @XmlElement(name = "EbppAccname", required = true)
    protected String ebppAccname;
    @XmlElement(name = "EbppBvstatus", required = true)
    protected String ebppBvstatus;
    @XmlElement(name = "Kovon", required = true)
    protected XMLGregorianCalendar kovon;
    @XmlElement(name = "Kobis", required = true)
    protected XMLGregorianCalendar kobis;
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
     * Gets the value of the banks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBanks() {
        return banks;
    }

    /**
     * Sets the value of the banks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBanks(String value) {
        this.banks = value;
    }

    /**
     * Gets the value of the bankl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankl() {
        return bankl;
    }

    /**
     * Sets the value of the bankl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankl(String value) {
        this.bankl = value;
    }

    /**
     * Gets the value of the bankn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankn() {
        return bankn;
    }

    /**
     * Sets the value of the bankn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankn(String value) {
        this.bankn = value;
    }

    /**
     * Gets the value of the bkont property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBkont() {
        return bkont;
    }

    /**
     * Sets the value of the bkont property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBkont(String value) {
        this.bkont = value;
    }

    /**
     * Gets the value of the bvtyp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBvtyp() {
        return bvtyp;
    }

    /**
     * Sets the value of the bvtyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBvtyp(String value) {
        this.bvtyp = value;
    }

    /**
     * Gets the value of the xezer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXezer() {
        return xezer;
    }

    /**
     * Sets the value of the xezer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXezer(String value) {
        this.xezer = value;
    }

    /**
     * Gets the value of the bkref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBkref() {
        return bkref;
    }

    /**
     * Sets the value of the bkref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBkref(String value) {
        this.bkref = value;
    }

    /**
     * Gets the value of the koinh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKoinh() {
        return koinh;
    }

    /**
     * Sets the value of the koinh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKoinh(String value) {
        this.koinh = value;
    }

    /**
     * Gets the value of the ebppAccname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEbppAccname() {
        return ebppAccname;
    }

    /**
     * Sets the value of the ebppAccname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEbppAccname(String value) {
        this.ebppAccname = value;
    }

    /**
     * Gets the value of the ebppBvstatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEbppBvstatus() {
        return ebppBvstatus;
    }

    /**
     * Sets the value of the ebppBvstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEbppBvstatus(String value) {
        this.ebppBvstatus = value;
    }

    /**
     * Gets the value of the kovon property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getKovon() {
        return kovon;
    }

    /**
     * Sets the value of the kovon property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setKovon(XMLGregorianCalendar value) {
        this.kovon = value;
    }

    /**
     * Gets the value of the kobis property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getKobis() {
        return kobis;
    }

    /**
     * Sets the value of the kobis property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setKobis(XMLGregorianCalendar value) {
        this.kobis = value;
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
