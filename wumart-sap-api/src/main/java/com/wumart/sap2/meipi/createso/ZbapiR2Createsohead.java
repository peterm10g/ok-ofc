
package com.wumart.sap2.meipi.createso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ZbapiR2Createsohead complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZbapiR2Createsohead">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocType" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="RefDoc" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Plant" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Incoterms2" type="{urn:sap-com:document:sap:rfc:functions}char28"/>
 *         &lt;element name="ReqDateH" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="PurchDate" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="SoDoc" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Temp01" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Temp02" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Temp03" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Temp04" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Temp05" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZbapiR2Createsohead", propOrder = {
    "docType",
    "refDoc",
    "plant",
    "incoterms2",
    "reqDateH",
    "purchDate",
    "soDoc",
    "temp01",
    "temp02",
    "temp03",
    "temp04",
    "temp05"
})
public class ZbapiR2Createsohead {

    @XmlElement(name = "DocType", required = true)
    protected String docType;
    @XmlElement(name = "RefDoc", required = true)
    protected String refDoc;
    @XmlElement(name = "Plant", required = true)
    protected String plant;
    @XmlElement(name = "Incoterms2", required = true)
    protected String incoterms2;
    @XmlElement(name = "ReqDateH", required = true)
    protected XMLGregorianCalendar reqDateH;
    @XmlElement(name = "PurchDate", required = true)
    protected XMLGregorianCalendar purchDate;
    @XmlElement(name = "SoDoc", required = true)
    protected String soDoc;
    @XmlElement(name = "Temp01", required = true)
    protected String temp01;
    @XmlElement(name = "Temp02", required = true)
    protected String temp02;
    @XmlElement(name = "Temp03", required = true)
    protected String temp03;
    @XmlElement(name = "Temp04", required = true)
    protected String temp04;
    @XmlElement(name = "Temp05", required = true)
    protected String temp05;

    /**
     * Gets the value of the docType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocType() {
        return docType;
    }

    /**
     * Sets the value of the docType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocType(String value) {
        this.docType = value;
    }

    /**
     * Gets the value of the refDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefDoc() {
        return refDoc;
    }

    /**
     * Sets the value of the refDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefDoc(String value) {
        this.refDoc = value;
    }

    /**
     * Gets the value of the plant property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlant() {
        return plant;
    }

    /**
     * Sets the value of the plant property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlant(String value) {
        this.plant = value;
    }

    /**
     * Gets the value of the incoterms2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncoterms2() {
        return incoterms2;
    }

    /**
     * Sets the value of the incoterms2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncoterms2(String value) {
        this.incoterms2 = value;
    }

    /**
     * Gets the value of the reqDateH property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReqDateH() {
        return reqDateH;
    }

    /**
     * Sets the value of the reqDateH property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReqDateH(XMLGregorianCalendar value) {
        this.reqDateH = value;
    }

    /**
     * Gets the value of the purchDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPurchDate() {
        return purchDate;
    }

    /**
     * Sets the value of the purchDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPurchDate(XMLGregorianCalendar value) {
        this.purchDate = value;
    }

    /**
     * Gets the value of the soDoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoDoc() {
        return soDoc;
    }

    /**
     * Sets the value of the soDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoDoc(String value) {
        this.soDoc = value;
    }

    /**
     * Gets the value of the temp01 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp01() {
        return temp01;
    }

    /**
     * Sets the value of the temp01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp01(String value) {
        this.temp01 = value;
    }

    /**
     * Gets the value of the temp02 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp02() {
        return temp02;
    }

    /**
     * Sets the value of the temp02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp02(String value) {
        this.temp02 = value;
    }

    /**
     * Gets the value of the temp03 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp03() {
        return temp03;
    }

    /**
     * Sets the value of the temp03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp03(String value) {
        this.temp03 = value;
    }

    /**
     * Gets the value of the temp04 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp04() {
        return temp04;
    }

    /**
     * Sets the value of the temp04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp04(String value) {
        this.temp04 = value;
    }

    /**
     * Gets the value of the temp05 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp05() {
        return temp05;
    }

    /**
     * Sets the value of the temp05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp05(String value) {
        this.temp05 = value;
    }

}
