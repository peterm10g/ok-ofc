
package com.wumart.sap2.meipi.cus.maintain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Zbankinfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Zbankinfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="BankCode" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="BankAccnt" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="BankOwner" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="RefDetail" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="BankName" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="Region" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="City" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="BankNo" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Zbankinfo", propOrder = {
    "country",
    "bankCode",
    "bankAccnt",
    "bankOwner",
    "refDetail",
    "bankName",
    "region",
    "street",
    "city",
    "bankNo"
})
public class Zbankinfo {

    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "BankCode", required = true)
    protected String bankCode;
    @XmlElement(name = "BankAccnt", required = true)
    protected String bankAccnt;
    @XmlElement(name = "BankOwner", required = true)
    protected String bankOwner;
    @XmlElement(name = "RefDetail", required = true)
    protected String refDetail;
    @XmlElement(name = "BankName", required = true)
    protected String bankName;
    @XmlElement(name = "Region", required = true)
    protected String region;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "BankNo", required = true)
    protected String bankNo;

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the bankCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Sets the value of the bankCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCode(String value) {
        this.bankCode = value;
    }

    /**
     * Gets the value of the bankAccnt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccnt() {
        return bankAccnt;
    }

    /**
     * Sets the value of the bankAccnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccnt(String value) {
        this.bankAccnt = value;
    }

    /**
     * Gets the value of the bankOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankOwner() {
        return bankOwner;
    }

    /**
     * Sets the value of the bankOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankOwner(String value) {
        this.bankOwner = value;
    }

    /**
     * Gets the value of the refDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefDetail() {
        return refDetail;
    }

    /**
     * Sets the value of the refDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefDetail(String value) {
        this.refDetail = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the bankNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * Sets the value of the bankNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankNo(String value) {
        this.bankNo = value;
    }

}
