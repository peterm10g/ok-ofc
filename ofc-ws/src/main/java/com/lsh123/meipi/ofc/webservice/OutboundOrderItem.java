package com.lsh123.meipi.ofc.webservice;

import javax.xml.bind.annotation.*;

/**
 * Created by zengwenjun on 15/12/18.
 */

@XmlRootElement(name="OutboundOrderItem" )
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="OutboundOrderItem", propOrder = {
        "item_id",
        "qty",
        "obd_id",
        "waybill_id",
        "driver_info",
        "price",
        "amount",
        "nt_amount",
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
    private String item_id;
    @XmlElement(name = "qty")
    private String qty;
    @XmlElement(name = "obd_id")
    private String obd_id;
    @XmlElement(name = "waybill_id")
    private String waybill_id;
    @XmlElement(name = "driver_info")
    private String driver_info;
    @XmlElement(name = "price")
    private String price;
    @XmlElement(name = "amount")
    private String amount;
    @XmlElement(name = "nt_amount")
    private String nt_amount;
    @XmlElement(name = "temp1")
    public String temp1;
    @XmlElement(name = "temp2")
    public String temp2;
    @XmlElement(name = "temp3")
    public String temp3;
    @XmlElement(name = "temp4")
    public String temp4;
    @XmlElement(name = "temp5")
    public String temp5;
    @XmlElement(name = "temp6")
    public String temp6;
    @XmlElement(name = "temp7")
    public String temp7;
    @XmlElement(name = "temp8")
    public String temp8;


    public void trim(){
        item_id = item_id.trim();
        qty = qty.trim();
        obd_id = obd_id==null?"":obd_id.trim();
        waybill_id = waybill_id==null?"":waybill_id.trim();
        driver_info = driver_info==null?"":driver_info.trim();
    }

    public String getPackNum(){
        return temp1==null?"":temp1.trim();
    }
    public void setPacknum(String pack_num){
        temp1 = pack_num;
    }
    public String getBoxNum(){
        return temp2==null?"":temp2.trim();
    }
    public void setBoxNum(String box_num){
        temp2 = box_num;
    }
    public String getLeftEANum(){
        return temp3==null?"":temp3.trim();
    }
    public void setLeftEANum(String leftEANum){
        temp3 = leftEANum;
    }


    public String getItemId(){
        return this.item_id;
    }
    public void setItemId(String item_id){
        this.item_id = item_id;
    }
    public String getQty(){
        return this.qty;
    }
    public void setQty(String qty){
        this.qty = qty;
    }
    public String getObdId(){
        return this.obd_id;
    }
    public void setObdId(String obd_id){
        this.obd_id = obd_id;
    }
    public void setWaybillId(String waybill_id){
        this.waybill_id = waybill_id;
    }
    public String getWaybillId(){
        return this.waybill_id;
    }
    public String getDriverInfo(){
        return this.driver_info;
    }
    public void setDriverInfo(String driver_info){
        this.driver_info = driver_info;
    }
    public String getPrice(){
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getAmount(){
        return this.amount;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }
    public String getNtAmount(){
        return this.nt_amount;
    }
    public void setNtAmount(String nt_amount){
        this.nt_amount = nt_amount;
    }
}
