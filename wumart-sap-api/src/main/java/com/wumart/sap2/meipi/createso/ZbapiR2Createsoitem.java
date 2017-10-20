
package com.wumart.sap2.meipi.createso;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ZbapiR2Createsoitem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ZbapiR2Createsoitem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItmNumber" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="HgLvItem" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="Material" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="ReqQty" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="SalesUnit" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="CondValue" type="{urn:sap-com:document:sap:rfc:functions}decimal28.9"/>
 *         &lt;element name="Quantity" type="{urn:sap-com:document:sap:rfc:functions}quantum15.3"/>
 *         &lt;element name="CostPrice" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="SellingPrice" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="Tax" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
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
@XmlType(name = "ZbapiR2Createsoitem", propOrder = {
    "itmNumber",
    "hgLvItem",
    "material",
    "reqQty",
    "salesUnit",
    "condValue",
    "quantity",
    "costPrice",
    "sellingPrice",
    "tax",
    "temp01",
    "temp02",
    "temp03",
    "temp04",
    "temp05"
})
public class ZbapiR2Createsoitem {

    @XmlElement(name = "ItmNumber", required = true)
    protected String itmNumber;
    @XmlElement(name = "HgLvItem", required = true)
    protected String hgLvItem;
    @XmlElement(name = "Material", required = true)
    protected String material;
    @XmlElement(name = "ReqQty", required = true)
    protected BigDecimal reqQty;
    @XmlElement(name = "SalesUnit", required = true)
    protected String salesUnit;
    @XmlElement(name = "CondValue", required = true)
    protected BigDecimal condValue;
    @XmlElement(name = "Quantity", required = true)
    protected BigDecimal quantity;
    @XmlElement(name = "CostPrice", required = true)
    protected BigDecimal costPrice;
    @XmlElement(name = "SellingPrice", required = true)
    protected BigDecimal sellingPrice;
    @XmlElement(name = "Tax", required = true)
    protected BigDecimal tax;
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
     * Gets the value of the itmNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItmNumber() {
        return itmNumber;
    }

    /**
     * Sets the value of the itmNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItmNumber(String value) {
        this.itmNumber = value;
    }

    /**
     * Gets the value of the hgLvItem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHgLvItem() {
        return hgLvItem;
    }

    /**
     * Sets the value of the hgLvItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHgLvItem(String value) {
        this.hgLvItem = value;
    }

    /**
     * Gets the value of the material property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the value of the material property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterial(String value) {
        this.material = value;
    }

    /**
     * Gets the value of the reqQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getReqQty() {
        return reqQty;
    }

    /**
     * Sets the value of the reqQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setReqQty(BigDecimal value) {
        this.reqQty = value;
    }

    /**
     * Gets the value of the salesUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesUnit() {
        return salesUnit;
    }

    /**
     * Sets the value of the salesUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesUnit(String value) {
        this.salesUnit = value;
    }

    /**
     * Gets the value of the condValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCondValue() {
        return condValue;
    }

    /**
     * Sets the value of the condValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCondValue(BigDecimal value) {
        this.condValue = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQuantity(BigDecimal value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the costPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     * Sets the value of the costPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCostPrice(BigDecimal value) {
        this.costPrice = value;
    }

    /**
     * Gets the value of the sellingPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the value of the sellingPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSellingPrice(BigDecimal value) {
        this.sellingPrice = value;
    }

    /**
     * Gets the value of the tax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * Sets the value of the tax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTax(BigDecimal value) {
        this.tax = value;
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
