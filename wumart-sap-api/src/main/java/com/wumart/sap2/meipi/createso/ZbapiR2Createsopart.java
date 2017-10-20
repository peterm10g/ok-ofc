
package com.wumart.sap2.meipi.createso;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZbapiR2Createsopart complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZbapiR2Createsopart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PartnRole" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="PartnNumb" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Title" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="Name" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Name2" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Street" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Country" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="PostlCode" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="City" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Telephone" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="Telephone2" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="TeletexNo" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="TelexNo" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ZbapiR2Createsopart", propOrder = {
    "partnRole",
    "partnNumb",
    "title",
    "name",
    "name2",
    "street",
    "country",
    "postlCode",
    "city",
    "telephone",
    "telephone2",
    "teletexNo",
    "telexNo"
})
public class ZbapiR2Createsopart {

    @XmlElement(name = "PartnRole", required = true)
    protected String partnRole;
    @XmlElement(name = "PartnNumb", required = true)
    protected String partnNumb;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Name2", required = true)
    protected String name2;
    @XmlElement(name = "Street", required = true)
    protected String street;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlElement(name = "PostlCode", required = true)
    protected String postlCode;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "Telephone", required = true)
    protected String telephone;
    @XmlElement(name = "Telephone2", required = true)
    protected String telephone2;
    @XmlElement(name = "TeletexNo", required = true)
    protected String teletexNo;
    @XmlElement(name = "TelexNo", required = true)
    protected String telexNo;

    /**
     * Gets the value of the partnRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnRole() {
        return partnRole;
    }

    /**
     * Sets the value of the partnRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnRole(String value) {
        this.partnRole = value;
    }

    /**
     * Gets the value of the partnNumb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnNumb() {
        return partnNumb;
    }

    /**
     * Sets the value of the partnNumb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnNumb(String value) {
        this.partnNumb = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the name2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName2() {
        return name2;
    }

    /**
     * Sets the value of the name2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName2(String value) {
        this.name2 = value;
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
     * Gets the value of the postlCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostlCode() {
        return postlCode;
    }

    /**
     * Sets the value of the postlCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostlCode(String value) {
        this.postlCode = value;
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
     * Gets the value of the telephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone(String value) {
        this.telephone = value;
    }

    /**
     * Gets the value of the telephone2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephone2() {
        return telephone2;
    }

    /**
     * Sets the value of the telephone2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephone2(String value) {
        this.telephone2 = value;
    }

    /**
     * Gets the value of the teletexNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeletexNo() {
        return teletexNo;
    }

    /**
     * Sets the value of the teletexNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeletexNo(String value) {
        this.teletexNo = value;
    }

    /**
     * Gets the value of the telexNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelexNo() {
        return telexNo;
    }

    /**
     * Sets the value of the telexNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelexNo(String value) {
        this.telexNo = value;
    }

}
