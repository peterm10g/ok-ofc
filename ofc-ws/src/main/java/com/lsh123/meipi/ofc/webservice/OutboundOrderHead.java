package com.lsh123.meipi.ofc.webservice;

import javax.xml.bind.annotation.*;

/**
 * Created by zengwenjun on 15/12/18.
 */

   /* head-
     * so_id
     * obd_id
     * wumart 金额,
     * obj创建时间
     * 计划交货日期
     * 捡配日期
     */

    /* item-
     *
     * 物料编码
     * 出库数量
     *
     */


@XmlRootElement(name="OutboundOrderHead" )
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="OutboundOrderHead", propOrder = {
        "so_id",
        "obd_id",
        "waybill_id",
        "amount",
        "nt_amount",
        "create_time",
        "delivery_time",
        "pick_time",
        "driver_info",
        "box_num",
        "turnoverbox_num",
        "temp1",
        "temp2",
        "temp3",
        "temp4",
        "temp5",
        "temp6",
        "temp7",
        "temp8"
})

public class OutboundOrderHead {
    @XmlElement(name = "so_id")
    private String so_id;
    @XmlElement(name = "obd_id")
    private String obd_id;
    @XmlElement(name = "waybill_id")
    private String waybill_id;
    @XmlElement(name = "amount")
    private String amount;
    @XmlElement(name = "nt_amount")
    private String nt_amount;
    @XmlElement(name = "create_time")
    private String create_time;
    @XmlElement(name = "delivery_time")
    private String delivery_time;
    @XmlElement(name = "pick_time")
    private String pick_time;
    @XmlElement(name = "driver_info")
    private String driver_info;
    @XmlElement(name = "box_num")
    private int box_num;
    @XmlElement(name = "turnoverbox_num")
    private int turnoverbox_num;
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
        so_id = so_id.trim();
        obd_id = obd_id==null?"":obd_id.trim();
        waybill_id = waybill_id==null?"":waybill_id.trim();
        driver_info = driver_info==null?"":driver_info.trim();
    }


    public String getSoId() {
        return this.so_id;
    }
    public void setSoId(String so_id) {
        this.so_id = so_id;
    }
    public String getObdId() {
        return this.obd_id;
    }
    public void setObdId(String obd_id) {
        this.obd_id = obd_id;
    }
    public String getWaybillId(){
        return this.waybill_id;
    }
    public void setWaybillId(String waybill_id){
        this.waybill_id = waybill_id;
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
    public void setNtAmount(String amount){
        this.nt_amount = amount;
    }
    public void setCreateTime(String create_time){
        this.create_time = create_time;
    }
    public String getCreateTime(){
        return this.create_time;
    }
    public void setDeliveryTime(String delivery_time){
        this.delivery_time = delivery_time;
    }
    public String getDeliveryTime(){
        return this.delivery_time;
    }
    public void setPickTime(String pick_time){
        this.pick_time = pick_time;
    }
    public String getPTickTime(){
        return this.pick_time;
    }
    public void setDriverInfo(String driver_info){
        this.driver_info = driver_info;
    }
    public String getDriverInfo(){
        return this.driver_info;
    }
    public void setBoxNum(int box_num){
        this.box_num = box_num;
    }
    public int getBoxNum(){
        return this.box_num;
    }
    public void setTurnoverboxNum(int turnoverbox_num){
        this.turnoverbox_num = turnoverbox_num;
    }
    public int getTurnoverboxNum(){
        return this.turnoverbox_num;
    }
}
