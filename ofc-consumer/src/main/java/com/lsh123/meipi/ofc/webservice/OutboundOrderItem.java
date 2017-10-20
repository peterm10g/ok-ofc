
package com.lsh123.meipi.ofc.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OutboundOrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OutboundOrderItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="item_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="obd_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waybill_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="driver_info" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nt_amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutboundOrderItem", propOrder = {
    "itemId",
    "qty",
    "obdId",
    "waybillId",
    "driverInfo",
    "price",
    "amount",
    "ntAmount",
    "temp1",
    "temp2",
    "temp3",
    "temp4",
    "temp5",
    "temp6",
    "temp7",
    "temp8"
})
public class OutboundOrderItem {

    @XmlElement(name = "item_id")
    protected String itemId;
    protected String qty;
    @XmlElement(name = "obd_id")
    protected String obdId;
    @XmlElement(name = "waybill_id")
    protected String waybillId;
    @XmlElement(name = "driver_info")
    protected String driverInfo;
    protected String price;
    protected String amount;
    @XmlElement(name = "nt_amount")
    protected String ntAmount;
    protected String temp1;
    protected String temp2;
    protected String temp3;
    protected String temp4;
    protected String temp5;
    protected String temp6;
    protected String temp7;
    protected String temp8;

    /**
     * Gets the value of the itemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the value of the itemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemId(String value) {
        this.itemId = value;
    }

    /**
     * Gets the value of the qty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQty() {
        return qty;
    }

    /**
     * Sets the value of the qty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQty(String value) {
        this.qty = value;
    }

    /**
     * Gets the value of the obdId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObdId() {
        return obdId;
    }

    /**
     * Sets the value of the obdId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObdId(String value) {
        this.obdId = value;
    }

    /**
     * Gets the value of the waybillId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybillId() {
        return waybillId;
    }

    /**
     * Sets the value of the waybillId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillId(String value) {
        this.waybillId = value;
    }

    /**
     * Gets the value of the driverInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverInfo() {
        return driverInfo;
    }

    /**
     * Sets the value of the driverInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverInfo(String value) {
        this.driverInfo = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrice(String value) {
        this.price = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Gets the value of the ntAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNtAmount() {
        return ntAmount;
    }

    /**
     * Sets the value of the ntAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNtAmount(String value) {
        this.ntAmount = value;
    }

    /**
     * Gets the value of the temp1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp1() {
        return temp1;
    }

    /**
     * Sets the value of the temp1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp1(String value) {
        this.temp1 = value;
    }

    /**
     * Gets the value of the temp2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp2() {
        return temp2;
    }

    /**
     * Sets the value of the temp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp2(String value) {
        this.temp2 = value;
    }

    /**
     * Gets the value of the temp3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp3() {
        return temp3;
    }

    /**
     * Sets the value of the temp3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp3(String value) {
        this.temp3 = value;
    }

    /**
     * Gets the value of the temp4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp4() {
        return temp4;
    }

    /**
     * Sets the value of the temp4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp4(String value) {
        this.temp4 = value;
    }

    /**
     * Gets the value of the temp5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp5() {
        return temp5;
    }

    /**
     * Sets the value of the temp5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp5(String value) {
        this.temp5 = value;
    }

    /**
     * Gets the value of the temp6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp6() {
        return temp6;
    }

    /**
     * Sets the value of the temp6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp6(String value) {
        this.temp6 = value;
    }

    /**
     * Gets the value of the temp7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp7() {
        return temp7;
    }

    /**
     * Sets the value of the temp7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp7(String value) {
        this.temp7 = value;
    }

    /**
     * Gets the value of the temp8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemp8() {
        return temp8;
    }

    /**
     * Sets the value of the temp8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemp8(String value) {
        this.temp8 = value;
    }

}
