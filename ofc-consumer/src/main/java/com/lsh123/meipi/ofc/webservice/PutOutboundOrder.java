
package com.lsh123.meipi.ofc.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for putOutboundOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="putOutboundOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="head" type="{http://webservice.ofc.meipi.lsh123.com/}OutboundOrderHead" minOccurs="0"/>
 *         &lt;element name="items" type="{http://webservice.ofc.meipi.lsh123.com/}OutboundOrderItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "putOutboundOrder", propOrder = {
    "head",
    "items"
})
public class PutOutboundOrder {

    protected OutboundOrderHead head;
    protected List<OutboundOrderItem> items;

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link OutboundOrderHead }
     *     
     */
    public OutboundOrderHead getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutboundOrderHead }
     *     
     */
    public void setHead(OutboundOrderHead value) {
        this.head = value;
    }

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OutboundOrderItem }
     * 
     * 
     */
    public List<OutboundOrderItem> getItems() {
        if (items == null) {
            items = new ArrayList<OutboundOrderItem>();
        }
        return this.items;
    }

}
