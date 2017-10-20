
package com.wumart.sap1.meipi.createso;

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
 *         &lt;element name="Return" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfBapiret2" minOccurs="0"/>
 *         &lt;element name="Soheader" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZbapiR2Createsohead"/>
 *         &lt;element name="Soitem" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZbapiR2Createsoitem"/>
 *         &lt;element name="Sopartners" type="{urn:sap-com:document:sap:soap:functions:mc-style}TableOfZbapiR2Createsopart" minOccurs="0"/>
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
    "_return",
    "soheader",
    "soitem",
    "sopartners"
})
@XmlRootElement(name = "ZBapiMeipiCreateso")
public class ZBapiMeipiCreateso {

    @XmlElement(name = "Return")
    protected TableOfBapiret2 _return;
    @XmlElement(name = "Soheader", required = true)
    protected TableOfZbapiR2Createsohead soheader;
    @XmlElement(name = "Soitem", required = true)
    protected TableOfZbapiR2Createsoitem soitem;
    @XmlElement(name = "Sopartners")
    protected TableOfZbapiR2Createsopart sopartners;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfBapiret2 }
     *     
     */
    public TableOfBapiret2 getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfBapiret2 }
     *     
     */
    public void setReturn(TableOfBapiret2 value) {
        this._return = value;
    }

    /**
     * Gets the value of the soheader property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZbapiR2Createsohead }
     *     
     */
    public TableOfZbapiR2Createsohead getSoheader() {
        return soheader;
    }

    /**
     * Sets the value of the soheader property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZbapiR2Createsohead }
     *     
     */
    public void setSoheader(TableOfZbapiR2Createsohead value) {
        this.soheader = value;
    }

    /**
     * Gets the value of the soitem property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZbapiR2Createsoitem }
     *     
     */
    public TableOfZbapiR2Createsoitem getSoitem() {
        return soitem;
    }

    /**
     * Sets the value of the soitem property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZbapiR2Createsoitem }
     *     
     */
    public void setSoitem(TableOfZbapiR2Createsoitem value) {
        this.soitem = value;
    }

    /**
     * Gets the value of the sopartners property.
     * 
     * @return
     *     possible object is
     *     {@link TableOfZbapiR2Createsopart }
     *     
     */
    public TableOfZbapiR2Createsopart getSopartners() {
        return sopartners;
    }

    /**
     * Sets the value of the sopartners property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableOfZbapiR2Createsopart }
     *     
     */
    public void setSopartners(TableOfZbapiR2Createsopart value) {
        this.sopartners = value;
    }

}
