
package com.wumart.sap1.meipi.cus.maintain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Knb1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Knb1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/>
 *         &lt;element name="Kunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Bukrs" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Pernr" type="{urn:sap-com:document:sap:rfc:functions}numeric8"/>
 *         &lt;element name="Erdat" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Ernam" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Sperr" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Loevm" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zuawa" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Busab" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Akont" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Begru" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Knrze" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Knrzb" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Zamim" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zamiv" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zamir" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zamib" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zamio" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zwels" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Xverr" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zahls" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Zterm" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Wakon" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Vzskz" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Zindt" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Zinrt" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="Eikto" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Zsabe" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="Kverm" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Fdgrv" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Vrbkz" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Vlibb" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="Vrszl" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/>
 *         &lt;element name="Vrspr" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/>
 *         &lt;element name="Vrsnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Verdt" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Perkz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Xdezv" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Xausz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Webtr" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="Remit" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Datlz" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Xzver" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Togru" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Kultg" type="{urn:sap-com:document:sap:rfc:functions}decimal3.0"/>
 *         &lt;element name="Hbkid" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="Xpore" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Blnkz" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Altkn" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Zgrup" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Urlid" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Mgrup" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Lockb" type="{urn:sap-com:document:sap:rfc:functions}char7"/>
 *         &lt;element name="Uzawe" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Ekvbd" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Sregl" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Xedip" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Frgrp" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Vrsdg" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Tlfxs" type="{urn:sap-com:document:sap:rfc:functions}char31"/>
 *         &lt;element name="Intad" type="{urn:sap-com:document:sap:rfc:functions}char130"/>
 *         &lt;element name="Xknzb" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Guzte" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Gricd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Gridt" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Wbrsl" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Confs" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Updat" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Uptim" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Nodel" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Tlfns" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="CessionKz" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Avsnd" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="AdHash" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Qland" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Gmvkzd" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Knb1", propOrder = {
    "mandt",
    "kunnr",
    "bukrs",
    "pernr",
    "erdat",
    "ernam",
    "sperr",
    "loevm",
    "zuawa",
    "busab",
    "akont",
    "begru",
    "knrze",
    "knrzb",
    "zamim",
    "zamiv",
    "zamir",
    "zamib",
    "zamio",
    "zwels",
    "xverr",
    "zahls",
    "zterm",
    "wakon",
    "vzskz",
    "zindt",
    "zinrt",
    "eikto",
    "zsabe",
    "kverm",
    "fdgrv",
    "vrbkz",
    "vlibb",
    "vrszl",
    "vrspr",
    "vrsnr",
    "verdt",
    "perkz",
    "xdezv",
    "xausz",
    "webtr",
    "remit",
    "datlz",
    "xzver",
    "togru",
    "kultg",
    "hbkid",
    "xpore",
    "blnkz",
    "altkn",
    "zgrup",
    "urlid",
    "mgrup",
    "lockb",
    "uzawe",
    "ekvbd",
    "sregl",
    "xedip",
    "frgrp",
    "vrsdg",
    "tlfxs",
    "intad",
    "xknzb",
    "guzte",
    "gricd",
    "gridt",
    "wbrsl",
    "confs",
    "updat",
    "uptim",
    "nodel",
    "tlfns",
    "cessionKz",
    "avsnd",
    "adHash",
    "qland",
    "gmvkzd"
})
public class Knb1 {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "Kunnr", required = true)
    protected String kunnr;
    @XmlElement(name = "Bukrs", required = true)
    protected String bukrs;
    @XmlElement(name = "Pernr", required = true)
    protected String pernr;
    @XmlElement(name = "Erdat", required = true)
    protected XMLGregorianCalendar erdat;
    @XmlElement(name = "Ernam", required = true)
    protected String ernam;
    @XmlElement(name = "Sperr", required = true)
    protected String sperr;
    @XmlElement(name = "Loevm", required = true)
    protected String loevm;
    @XmlElement(name = "Zuawa", required = true)
    protected String zuawa;
    @XmlElement(name = "Busab", required = true)
    protected String busab;
    @XmlElement(name = "Akont", required = true)
    protected String akont;
    @XmlElement(name = "Begru", required = true)
    protected String begru;
    @XmlElement(name = "Knrze", required = true)
    protected String knrze;
    @XmlElement(name = "Knrzb", required = true)
    protected String knrzb;
    @XmlElement(name = "Zamim", required = true)
    protected String zamim;
    @XmlElement(name = "Zamiv", required = true)
    protected String zamiv;
    @XmlElement(name = "Zamir", required = true)
    protected String zamir;
    @XmlElement(name = "Zamib", required = true)
    protected String zamib;
    @XmlElement(name = "Zamio", required = true)
    protected String zamio;
    @XmlElement(name = "Zwels", required = true)
    protected String zwels;
    @XmlElement(name = "Xverr", required = true)
    protected String xverr;
    @XmlElement(name = "Zahls", required = true)
    protected String zahls;
    @XmlElement(name = "Zterm", required = true)
    protected String zterm;
    @XmlElement(name = "Wakon", required = true)
    protected String wakon;
    @XmlElement(name = "Vzskz", required = true)
    protected String vzskz;
    @XmlElement(name = "Zindt", required = true)
    protected XMLGregorianCalendar zindt;
    @XmlElement(name = "Zinrt", required = true)
    protected String zinrt;
    @XmlElement(name = "Eikto", required = true)
    protected String eikto;
    @XmlElement(name = "Zsabe", required = true)
    protected String zsabe;
    @XmlElement(name = "Kverm", required = true)
    protected String kverm;
    @XmlElement(name = "Fdgrv", required = true)
    protected String fdgrv;
    @XmlElement(name = "Vrbkz", required = true)
    protected String vrbkz;
    @XmlElement(name = "Vlibb", required = true)
    protected BigDecimal vlibb;
    @XmlElement(name = "Vrszl", required = true)
    protected BigDecimal vrszl;
    @XmlElement(name = "Vrspr", required = true)
    protected BigDecimal vrspr;
    @XmlElement(name = "Vrsnr", required = true)
    protected String vrsnr;
    @XmlElement(name = "Verdt", required = true)
    protected XMLGregorianCalendar verdt;
    @XmlElement(name = "Perkz", required = true)
    protected String perkz;
    @XmlElement(name = "Xdezv", required = true)
    protected String xdezv;
    @XmlElement(name = "Xausz", required = true)
    protected String xausz;
    @XmlElement(name = "Webtr", required = true)
    protected BigDecimal webtr;
    @XmlElement(name = "Remit", required = true)
    protected String remit;
    @XmlElement(name = "Datlz", required = true)
    protected XMLGregorianCalendar datlz;
    @XmlElement(name = "Xzver", required = true)
    protected String xzver;
    @XmlElement(name = "Togru", required = true)
    protected String togru;
    @XmlElement(name = "Kultg", required = true)
    protected BigDecimal kultg;
    @XmlElement(name = "Hbkid", required = true)
    protected String hbkid;
    @XmlElement(name = "Xpore", required = true)
    protected String xpore;
    @XmlElement(name = "Blnkz", required = true)
    protected String blnkz;
    @XmlElement(name = "Altkn", required = true)
    protected String altkn;
    @XmlElement(name = "Zgrup", required = true)
    protected String zgrup;
    @XmlElement(name = "Urlid", required = true)
    protected String urlid;
    @XmlElement(name = "Mgrup", required = true)
    protected String mgrup;
    @XmlElement(name = "Lockb", required = true)
    protected String lockb;
    @XmlElement(name = "Uzawe", required = true)
    protected String uzawe;
    @XmlElement(name = "Ekvbd", required = true)
    protected String ekvbd;
    @XmlElement(name = "Sregl", required = true)
    protected String sregl;
    @XmlElement(name = "Xedip", required = true)
    protected String xedip;
    @XmlElement(name = "Frgrp", required = true)
    protected String frgrp;
    @XmlElement(name = "Vrsdg", required = true)
    protected String vrsdg;
    @XmlElement(name = "Tlfxs", required = true)
    protected String tlfxs;
    @XmlElement(name = "Intad", required = true)
    protected String intad;
    @XmlElement(name = "Xknzb", required = true)
    protected String xknzb;
    @XmlElement(name = "Guzte", required = true)
    protected String guzte;
    @XmlElement(name = "Gricd", required = true)
    protected String gricd;
    @XmlElement(name = "Gridt", required = true)
    protected String gridt;
    @XmlElement(name = "Wbrsl", required = true)
    protected String wbrsl;
    @XmlElement(name = "Confs", required = true)
    protected String confs;
    @XmlElement(name = "Updat", required = true)
    protected XMLGregorianCalendar updat;
    @XmlElement(name = "Uptim", required = true)
    protected XMLGregorianCalendar uptim;
    @XmlElement(name = "Nodel", required = true)
    protected String nodel;
    @XmlElement(name = "Tlfns", required = true)
    protected String tlfns;
    @XmlElement(name = "CessionKz", required = true)
    protected String cessionKz;
    @XmlElement(name = "Avsnd", required = true)
    protected String avsnd;
    @XmlElement(name = "AdHash", required = true)
    protected String adHash;
    @XmlElement(name = "Qland", required = true)
    protected String qland;
    @XmlElement(name = "Gmvkzd", required = true)
    protected String gmvkzd;

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
     * Gets the value of the bukrs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBukrs() {
        return bukrs;
    }

    /**
     * Sets the value of the bukrs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBukrs(String value) {
        this.bukrs = value;
    }

    /**
     * Gets the value of the pernr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPernr() {
        return pernr;
    }

    /**
     * Sets the value of the pernr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPernr(String value) {
        this.pernr = value;
    }

    /**
     * Gets the value of the erdat property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getErdat() {
        return erdat;
    }

    /**
     * Sets the value of the erdat property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setErdat(XMLGregorianCalendar value) {
        this.erdat = value;
    }

    /**
     * Gets the value of the ernam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErnam() {
        return ernam;
    }

    /**
     * Sets the value of the ernam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErnam(String value) {
        this.ernam = value;
    }

    /**
     * Gets the value of the sperr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSperr() {
        return sperr;
    }

    /**
     * Sets the value of the sperr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSperr(String value) {
        this.sperr = value;
    }

    /**
     * Gets the value of the loevm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoevm() {
        return loevm;
    }

    /**
     * Sets the value of the loevm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoevm(String value) {
        this.loevm = value;
    }

    /**
     * Gets the value of the zuawa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZuawa() {
        return zuawa;
    }

    /**
     * Sets the value of the zuawa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZuawa(String value) {
        this.zuawa = value;
    }

    /**
     * Gets the value of the busab property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusab() {
        return busab;
    }

    /**
     * Sets the value of the busab property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusab(String value) {
        this.busab = value;
    }

    /**
     * Gets the value of the akont property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAkont() {
        return akont;
    }

    /**
     * Sets the value of the akont property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAkont(String value) {
        this.akont = value;
    }

    /**
     * Gets the value of the begru property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBegru() {
        return begru;
    }

    /**
     * Sets the value of the begru property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBegru(String value) {
        this.begru = value;
    }

    /**
     * Gets the value of the knrze property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnrze() {
        return knrze;
    }

    /**
     * Sets the value of the knrze property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnrze(String value) {
        this.knrze = value;
    }

    /**
     * Gets the value of the knrzb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnrzb() {
        return knrzb;
    }

    /**
     * Sets the value of the knrzb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnrzb(String value) {
        this.knrzb = value;
    }

    /**
     * Gets the value of the zamim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZamim() {
        return zamim;
    }

    /**
     * Sets the value of the zamim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZamim(String value) {
        this.zamim = value;
    }

    /**
     * Gets the value of the zamiv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZamiv() {
        return zamiv;
    }

    /**
     * Sets the value of the zamiv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZamiv(String value) {
        this.zamiv = value;
    }

    /**
     * Gets the value of the zamir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZamir() {
        return zamir;
    }

    /**
     * Sets the value of the zamir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZamir(String value) {
        this.zamir = value;
    }

    /**
     * Gets the value of the zamib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZamib() {
        return zamib;
    }

    /**
     * Sets the value of the zamib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZamib(String value) {
        this.zamib = value;
    }

    /**
     * Gets the value of the zamio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZamio() {
        return zamio;
    }

    /**
     * Sets the value of the zamio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZamio(String value) {
        this.zamio = value;
    }

    /**
     * Gets the value of the zwels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZwels() {
        return zwels;
    }

    /**
     * Sets the value of the zwels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZwels(String value) {
        this.zwels = value;
    }

    /**
     * Gets the value of the xverr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXverr() {
        return xverr;
    }

    /**
     * Sets the value of the xverr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXverr(String value) {
        this.xverr = value;
    }

    /**
     * Gets the value of the zahls property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZahls() {
        return zahls;
    }

    /**
     * Sets the value of the zahls property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZahls(String value) {
        this.zahls = value;
    }

    /**
     * Gets the value of the zterm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZterm() {
        return zterm;
    }

    /**
     * Sets the value of the zterm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZterm(String value) {
        this.zterm = value;
    }

    /**
     * Gets the value of the wakon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWakon() {
        return wakon;
    }

    /**
     * Sets the value of the wakon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWakon(String value) {
        this.wakon = value;
    }

    /**
     * Gets the value of the vzskz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVzskz() {
        return vzskz;
    }

    /**
     * Sets the value of the vzskz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVzskz(String value) {
        this.vzskz = value;
    }

    /**
     * Gets the value of the zindt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getZindt() {
        return zindt;
    }

    /**
     * Sets the value of the zindt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setZindt(XMLGregorianCalendar value) {
        this.zindt = value;
    }

    /**
     * Gets the value of the zinrt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZinrt() {
        return zinrt;
    }

    /**
     * Sets the value of the zinrt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZinrt(String value) {
        this.zinrt = value;
    }

    /**
     * Gets the value of the eikto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEikto() {
        return eikto;
    }

    /**
     * Sets the value of the eikto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEikto(String value) {
        this.eikto = value;
    }

    /**
     * Gets the value of the zsabe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZsabe() {
        return zsabe;
    }

    /**
     * Sets the value of the zsabe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZsabe(String value) {
        this.zsabe = value;
    }

    /**
     * Gets the value of the kverm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKverm() {
        return kverm;
    }

    /**
     * Sets the value of the kverm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKverm(String value) {
        this.kverm = value;
    }

    /**
     * Gets the value of the fdgrv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFdgrv() {
        return fdgrv;
    }

    /**
     * Sets the value of the fdgrv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFdgrv(String value) {
        this.fdgrv = value;
    }

    /**
     * Gets the value of the vrbkz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrbkz() {
        return vrbkz;
    }

    /**
     * Sets the value of the vrbkz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrbkz(String value) {
        this.vrbkz = value;
    }

    /**
     * Gets the value of the vlibb property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVlibb() {
        return vlibb;
    }

    /**
     * Sets the value of the vlibb property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVlibb(BigDecimal value) {
        this.vlibb = value;
    }

    /**
     * Gets the value of the vrszl property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVrszl() {
        return vrszl;
    }

    /**
     * Sets the value of the vrszl property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVrszl(BigDecimal value) {
        this.vrszl = value;
    }

    /**
     * Gets the value of the vrspr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVrspr() {
        return vrspr;
    }

    /**
     * Sets the value of the vrspr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVrspr(BigDecimal value) {
        this.vrspr = value;
    }

    /**
     * Gets the value of the vrsnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrsnr() {
        return vrsnr;
    }

    /**
     * Sets the value of the vrsnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrsnr(String value) {
        this.vrsnr = value;
    }

    /**
     * Gets the value of the verdt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getVerdt() {
        return verdt;
    }

    /**
     * Sets the value of the verdt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setVerdt(XMLGregorianCalendar value) {
        this.verdt = value;
    }

    /**
     * Gets the value of the perkz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerkz() {
        return perkz;
    }

    /**
     * Sets the value of the perkz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerkz(String value) {
        this.perkz = value;
    }

    /**
     * Gets the value of the xdezv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXdezv() {
        return xdezv;
    }

    /**
     * Sets the value of the xdezv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXdezv(String value) {
        this.xdezv = value;
    }

    /**
     * Gets the value of the xausz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXausz() {
        return xausz;
    }

    /**
     * Sets the value of the xausz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXausz(String value) {
        this.xausz = value;
    }

    /**
     * Gets the value of the webtr property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWebtr() {
        return webtr;
    }

    /**
     * Sets the value of the webtr property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWebtr(BigDecimal value) {
        this.webtr = value;
    }

    /**
     * Gets the value of the remit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemit() {
        return remit;
    }

    /**
     * Sets the value of the remit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemit(String value) {
        this.remit = value;
    }

    /**
     * Gets the value of the datlz property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatlz() {
        return datlz;
    }

    /**
     * Sets the value of the datlz property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatlz(XMLGregorianCalendar value) {
        this.datlz = value;
    }

    /**
     * Gets the value of the xzver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXzver() {
        return xzver;
    }

    /**
     * Sets the value of the xzver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXzver(String value) {
        this.xzver = value;
    }

    /**
     * Gets the value of the togru property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTogru() {
        return togru;
    }

    /**
     * Sets the value of the togru property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTogru(String value) {
        this.togru = value;
    }

    /**
     * Gets the value of the kultg property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKultg() {
        return kultg;
    }

    /**
     * Sets the value of the kultg property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKultg(BigDecimal value) {
        this.kultg = value;
    }

    /**
     * Gets the value of the hbkid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHbkid() {
        return hbkid;
    }

    /**
     * Sets the value of the hbkid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHbkid(String value) {
        this.hbkid = value;
    }

    /**
     * Gets the value of the xpore property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpore() {
        return xpore;
    }

    /**
     * Sets the value of the xpore property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpore(String value) {
        this.xpore = value;
    }

    /**
     * Gets the value of the blnkz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlnkz() {
        return blnkz;
    }

    /**
     * Sets the value of the blnkz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlnkz(String value) {
        this.blnkz = value;
    }

    /**
     * Gets the value of the altkn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltkn() {
        return altkn;
    }

    /**
     * Sets the value of the altkn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltkn(String value) {
        this.altkn = value;
    }

    /**
     * Gets the value of the zgrup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZgrup() {
        return zgrup;
    }

    /**
     * Sets the value of the zgrup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZgrup(String value) {
        this.zgrup = value;
    }

    /**
     * Gets the value of the urlid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlid() {
        return urlid;
    }

    /**
     * Sets the value of the urlid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlid(String value) {
        this.urlid = value;
    }

    /**
     * Gets the value of the mgrup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMgrup() {
        return mgrup;
    }

    /**
     * Sets the value of the mgrup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMgrup(String value) {
        this.mgrup = value;
    }

    /**
     * Gets the value of the lockb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockb() {
        return lockb;
    }

    /**
     * Sets the value of the lockb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockb(String value) {
        this.lockb = value;
    }

    /**
     * Gets the value of the uzawe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUzawe() {
        return uzawe;
    }

    /**
     * Sets the value of the uzawe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUzawe(String value) {
        this.uzawe = value;
    }

    /**
     * Gets the value of the ekvbd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEkvbd() {
        return ekvbd;
    }

    /**
     * Sets the value of the ekvbd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEkvbd(String value) {
        this.ekvbd = value;
    }

    /**
     * Gets the value of the sregl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSregl() {
        return sregl;
    }

    /**
     * Sets the value of the sregl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSregl(String value) {
        this.sregl = value;
    }

    /**
     * Gets the value of the xedip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXedip() {
        return xedip;
    }

    /**
     * Sets the value of the xedip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXedip(String value) {
        this.xedip = value;
    }

    /**
     * Gets the value of the frgrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrgrp() {
        return frgrp;
    }

    /**
     * Sets the value of the frgrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrgrp(String value) {
        this.frgrp = value;
    }

    /**
     * Gets the value of the vrsdg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrsdg() {
        return vrsdg;
    }

    /**
     * Sets the value of the vrsdg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrsdg(String value) {
        this.vrsdg = value;
    }

    /**
     * Gets the value of the tlfxs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlfxs() {
        return tlfxs;
    }

    /**
     * Sets the value of the tlfxs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlfxs(String value) {
        this.tlfxs = value;
    }

    /**
     * Gets the value of the intad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntad() {
        return intad;
    }

    /**
     * Sets the value of the intad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntad(String value) {
        this.intad = value;
    }

    /**
     * Gets the value of the xknzb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXknzb() {
        return xknzb;
    }

    /**
     * Sets the value of the xknzb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXknzb(String value) {
        this.xknzb = value;
    }

    /**
     * Gets the value of the guzte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuzte() {
        return guzte;
    }

    /**
     * Sets the value of the guzte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuzte(String value) {
        this.guzte = value;
    }

    /**
     * Gets the value of the gricd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGricd() {
        return gricd;
    }

    /**
     * Sets the value of the gricd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGricd(String value) {
        this.gricd = value;
    }

    /**
     * Gets the value of the gridt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridt() {
        return gridt;
    }

    /**
     * Sets the value of the gridt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridt(String value) {
        this.gridt = value;
    }

    /**
     * Gets the value of the wbrsl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWbrsl() {
        return wbrsl;
    }

    /**
     * Sets the value of the wbrsl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWbrsl(String value) {
        this.wbrsl = value;
    }

    /**
     * Gets the value of the confs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfs() {
        return confs;
    }

    /**
     * Sets the value of the confs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfs(String value) {
        this.confs = value;
    }

    /**
     * Gets the value of the updat property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdat() {
        return updat;
    }

    /**
     * Sets the value of the updat property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdat(XMLGregorianCalendar value) {
        this.updat = value;
    }

    /**
     * Gets the value of the uptim property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUptim() {
        return uptim;
    }

    /**
     * Sets the value of the uptim property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUptim(XMLGregorianCalendar value) {
        this.uptim = value;
    }

    /**
     * Gets the value of the nodel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodel() {
        return nodel;
    }

    /**
     * Sets the value of the nodel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodel(String value) {
        this.nodel = value;
    }

    /**
     * Gets the value of the tlfns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTlfns() {
        return tlfns;
    }

    /**
     * Sets the value of the tlfns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTlfns(String value) {
        this.tlfns = value;
    }

    /**
     * Gets the value of the cessionKz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCessionKz() {
        return cessionKz;
    }

    /**
     * Sets the value of the cessionKz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCessionKz(String value) {
        this.cessionKz = value;
    }

    /**
     * Gets the value of the avsnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAvsnd() {
        return avsnd;
    }

    /**
     * Sets the value of the avsnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAvsnd(String value) {
        this.avsnd = value;
    }

    /**
     * Gets the value of the adHash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdHash() {
        return adHash;
    }

    /**
     * Sets the value of the adHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdHash(String value) {
        this.adHash = value;
    }

    /**
     * Gets the value of the qland property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQland() {
        return qland;
    }

    /**
     * Sets the value of the qland property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQland(String value) {
        this.qland = value;
    }

    /**
     * Gets the value of the gmvkzd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGmvkzd() {
        return gmvkzd;
    }

    /**
     * Sets the value of the gmvkzd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGmvkzd(String value) {
        this.gmvkzd = value;
    }

}
