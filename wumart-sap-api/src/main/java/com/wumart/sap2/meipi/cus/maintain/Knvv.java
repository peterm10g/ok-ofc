
package com.wumart.sap2.meipi.cus.maintain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Knvv complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Knvv">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/>
 *         &lt;element name="Kunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Vkorg" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Vtweg" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Spart" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Ernam" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Erdat" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Begru" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Loevm" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Versg" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Aufsd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Kalks" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kdgrp" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Bzirk" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="Konda" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Pltyp" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Awahr" type="{urn:sap-com:document:sap:rfc:functions}numeric3"/>
 *         &lt;element name="Inco1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Inco2" type="{urn:sap-com:document:sap:rfc:functions}char28"/>
 *         &lt;element name="Lifsd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Autlf" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Antlf" type="{urn:sap-com:document:sap:rfc:functions}decimal1.0"/>
 *         &lt;element name="Kztlf" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kzazu" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Chspl" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Lprio" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="Eikto" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Vsbed" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Faksd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Mrnkz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Perfk" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Perrl" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Kvakz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kvawt" type="{urn:sap-com:document:sap:rfc:functions}curr13.2"/>
 *         &lt;element name="Waers" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="Klabc" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Ktgrd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Zterm" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Vwerk" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Vkgrp" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Vkbur" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Vsort" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Kvgr1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Kvgr2" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Kvgr3" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Kvgr4" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Kvgr5" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Bokre" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Boidt" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Kurst" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Prfre" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat1" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat2" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat3" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat4" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat5" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat6" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat7" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat8" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prat9" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Prata" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kabss" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Kkber" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Cassd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Rdoff" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Agrel" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Megru" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Uebto" type="{urn:sap-com:document:sap:rfc:functions}decimal3.1"/>
 *         &lt;element name="Untto" type="{urn:sap-com:document:sap:rfc:functions}decimal3.1"/>
 *         &lt;element name="Uebtk" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Pvksm" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Podkz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Podtg" type="{urn:sap-com:document:sap:rfc:functions}decimal11.0"/>
 *         &lt;element name="Blind" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="CarrierNotif" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="_-bev1_-emlgpfand" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="_-bev1_-emlgforts" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Knvv", propOrder = {
    "mandt",
    "kunnr",
    "vkorg",
    "vtweg",
    "spart",
    "ernam",
    "erdat",
    "begru",
    "loevm",
    "versg",
    "aufsd",
    "kalks",
    "kdgrp",
    "bzirk",
    "konda",
    "pltyp",
    "awahr",
    "inco1",
    "inco2",
    "lifsd",
    "autlf",
    "antlf",
    "kztlf",
    "kzazu",
    "chspl",
    "lprio",
    "eikto",
    "vsbed",
    "faksd",
    "mrnkz",
    "perfk",
    "perrl",
    "kvakz",
    "kvawt",
    "waers",
    "klabc",
    "ktgrd",
    "zterm",
    "vwerk",
    "vkgrp",
    "vkbur",
    "vsort",
    "kvgr1",
    "kvgr2",
    "kvgr3",
    "kvgr4",
    "kvgr5",
    "bokre",
    "boidt",
    "kurst",
    "prfre",
    "prat1",
    "prat2",
    "prat3",
    "prat4",
    "prat5",
    "prat6",
    "prat7",
    "prat8",
    "prat9",
    "prata",
    "kabss",
    "kkber",
    "cassd",
    "rdoff",
    "agrel",
    "megru",
    "uebto",
    "untto",
    "uebtk",
    "pvksm",
    "podkz",
    "podtg",
    "blind",
    "carrierNotif",
    "bev1Emlgpfand",
    "bev1Emlgforts"
})
public class Knvv {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "Kunnr", required = true)
    protected String kunnr;
    @XmlElement(name = "Vkorg", required = true)
    protected String vkorg;
    @XmlElement(name = "Vtweg", required = true)
    protected String vtweg;
    @XmlElement(name = "Spart", required = true)
    protected String spart;
    @XmlElement(name = "Ernam", required = true)
    protected String ernam;
    @XmlElement(name = "Erdat", required = true)
    protected XMLGregorianCalendar erdat;
    @XmlElement(name = "Begru", required = true)
    protected String begru;
    @XmlElement(name = "Loevm", required = true)
    protected String loevm;
    @XmlElement(name = "Versg", required = true)
    protected String versg;
    @XmlElement(name = "Aufsd", required = true)
    protected String aufsd;
    @XmlElement(name = "Kalks", required = true)
    protected String kalks;
    @XmlElement(name = "Kdgrp", required = true)
    protected String kdgrp;
    @XmlElement(name = "Bzirk", required = true)
    protected String bzirk;
    @XmlElement(name = "Konda", required = true)
    protected String konda;
    @XmlElement(name = "Pltyp", required = true)
    protected String pltyp;
    @XmlElement(name = "Awahr", required = true)
    protected String awahr;
    @XmlElement(name = "Inco1", required = true)
    protected String inco1;
    @XmlElement(name = "Inco2", required = true)
    protected String inco2;
    @XmlElement(name = "Lifsd", required = true)
    protected String lifsd;
    @XmlElement(name = "Autlf", required = true)
    protected String autlf;
    @XmlElement(name = "Antlf", required = true)
    protected BigDecimal antlf;
    @XmlElement(name = "Kztlf", required = true)
    protected String kztlf;
    @XmlElement(name = "Kzazu", required = true)
    protected String kzazu;
    @XmlElement(name = "Chspl", required = true)
    protected String chspl;
    @XmlElement(name = "Lprio", required = true)
    protected String lprio;
    @XmlElement(name = "Eikto", required = true)
    protected String eikto;
    @XmlElement(name = "Vsbed", required = true)
    protected String vsbed;
    @XmlElement(name = "Faksd", required = true)
    protected String faksd;
    @XmlElement(name = "Mrnkz", required = true)
    protected String mrnkz;
    @XmlElement(name = "Perfk", required = true)
    protected String perfk;
    @XmlElement(name = "Perrl", required = true)
    protected String perrl;
    @XmlElement(name = "Kvakz", required = true)
    protected String kvakz;
    @XmlElement(name = "Kvawt", required = true)
    protected BigDecimal kvawt;
    @XmlElement(name = "Waers", required = true)
    protected String waers;
    @XmlElement(name = "Klabc", required = true)
    protected String klabc;
    @XmlElement(name = "Ktgrd", required = true)
    protected String ktgrd;
    @XmlElement(name = "Zterm", required = true)
    protected String zterm;
    @XmlElement(name = "Vwerk", required = true)
    protected String vwerk;
    @XmlElement(name = "Vkgrp", required = true)
    protected String vkgrp;
    @XmlElement(name = "Vkbur", required = true)
    protected String vkbur;
    @XmlElement(name = "Vsort", required = true)
    protected String vsort;
    @XmlElement(name = "Kvgr1", required = true)
    protected String kvgr1;
    @XmlElement(name = "Kvgr2", required = true)
    protected String kvgr2;
    @XmlElement(name = "Kvgr3", required = true)
    protected String kvgr3;
    @XmlElement(name = "Kvgr4", required = true)
    protected String kvgr4;
    @XmlElement(name = "Kvgr5", required = true)
    protected String kvgr5;
    @XmlElement(name = "Bokre", required = true)
    protected String bokre;
    @XmlElement(name = "Boidt", required = true)
    protected XMLGregorianCalendar boidt;
    @XmlElement(name = "Kurst", required = true)
    protected String kurst;
    @XmlElement(name = "Prfre", required = true)
    protected String prfre;
    @XmlElement(name = "Prat1", required = true)
    protected String prat1;
    @XmlElement(name = "Prat2", required = true)
    protected String prat2;
    @XmlElement(name = "Prat3", required = true)
    protected String prat3;
    @XmlElement(name = "Prat4", required = true)
    protected String prat4;
    @XmlElement(name = "Prat5", required = true)
    protected String prat5;
    @XmlElement(name = "Prat6", required = true)
    protected String prat6;
    @XmlElement(name = "Prat7", required = true)
    protected String prat7;
    @XmlElement(name = "Prat8", required = true)
    protected String prat8;
    @XmlElement(name = "Prat9", required = true)
    protected String prat9;
    @XmlElement(name = "Prata", required = true)
    protected String prata;
    @XmlElement(name = "Kabss", required = true)
    protected String kabss;
    @XmlElement(name = "Kkber", required = true)
    protected String kkber;
    @XmlElement(name = "Cassd", required = true)
    protected String cassd;
    @XmlElement(name = "Rdoff", required = true)
    protected String rdoff;
    @XmlElement(name = "Agrel", required = true)
    protected String agrel;
    @XmlElement(name = "Megru", required = true)
    protected String megru;
    @XmlElement(name = "Uebto", required = true)
    protected BigDecimal uebto;
    @XmlElement(name = "Untto", required = true)
    protected BigDecimal untto;
    @XmlElement(name = "Uebtk", required = true)
    protected String uebtk;
    @XmlElement(name = "Pvksm", required = true)
    protected String pvksm;
    @XmlElement(name = "Podkz", required = true)
    protected String podkz;
    @XmlElement(name = "Podtg", required = true)
    protected BigDecimal podtg;
    @XmlElement(name = "Blind", required = true)
    protected String blind;
    @XmlElement(name = "CarrierNotif", required = true)
    protected String carrierNotif;
    @XmlElement(name = "_-bev1_-emlgpfand", required = true)
    protected String bev1Emlgpfand;
    @XmlElement(name = "_-bev1_-emlgforts", required = true)
    protected String bev1Emlgforts;

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
     * Gets the value of the vkorg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVkorg() {
        return vkorg;
    }

    /**
     * Sets the value of the vkorg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVkorg(String value) {
        this.vkorg = value;
    }

    /**
     * Gets the value of the vtweg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVtweg() {
        return vtweg;
    }

    /**
     * Sets the value of the vtweg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVtweg(String value) {
        this.vtweg = value;
    }

    /**
     * Gets the value of the spart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpart() {
        return spart;
    }

    /**
     * Sets the value of the spart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpart(String value) {
        this.spart = value;
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
     * Gets the value of the versg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersg() {
        return versg;
    }

    /**
     * Sets the value of the versg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersg(String value) {
        this.versg = value;
    }

    /**
     * Gets the value of the aufsd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAufsd() {
        return aufsd;
    }

    /**
     * Sets the value of the aufsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAufsd(String value) {
        this.aufsd = value;
    }

    /**
     * Gets the value of the kalks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKalks() {
        return kalks;
    }

    /**
     * Sets the value of the kalks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKalks(String value) {
        this.kalks = value;
    }

    /**
     * Gets the value of the kdgrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKdgrp() {
        return kdgrp;
    }

    /**
     * Sets the value of the kdgrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKdgrp(String value) {
        this.kdgrp = value;
    }

    /**
     * Gets the value of the bzirk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBzirk() {
        return bzirk;
    }

    /**
     * Sets the value of the bzirk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBzirk(String value) {
        this.bzirk = value;
    }

    /**
     * Gets the value of the konda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKonda() {
        return konda;
    }

    /**
     * Sets the value of the konda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKonda(String value) {
        this.konda = value;
    }

    /**
     * Gets the value of the pltyp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPltyp() {
        return pltyp;
    }

    /**
     * Sets the value of the pltyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPltyp(String value) {
        this.pltyp = value;
    }

    /**
     * Gets the value of the awahr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAwahr() {
        return awahr;
    }

    /**
     * Sets the value of the awahr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAwahr(String value) {
        this.awahr = value;
    }

    /**
     * Gets the value of the inco1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInco1() {
        return inco1;
    }

    /**
     * Sets the value of the inco1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInco1(String value) {
        this.inco1 = value;
    }

    /**
     * Gets the value of the inco2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInco2() {
        return inco2;
    }

    /**
     * Sets the value of the inco2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInco2(String value) {
        this.inco2 = value;
    }

    /**
     * Gets the value of the lifsd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifsd() {
        return lifsd;
    }

    /**
     * Sets the value of the lifsd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifsd(String value) {
        this.lifsd = value;
    }

    /**
     * Gets the value of the autlf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutlf() {
        return autlf;
    }

    /**
     * Sets the value of the autlf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutlf(String value) {
        this.autlf = value;
    }

    /**
     * Gets the value of the antlf property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAntlf() {
        return antlf;
    }

    /**
     * Sets the value of the antlf property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAntlf(BigDecimal value) {
        this.antlf = value;
    }

    /**
     * Gets the value of the kztlf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKztlf() {
        return kztlf;
    }

    /**
     * Sets the value of the kztlf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKztlf(String value) {
        this.kztlf = value;
    }

    /**
     * Gets the value of the kzazu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKzazu() {
        return kzazu;
    }

    /**
     * Sets the value of the kzazu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKzazu(String value) {
        this.kzazu = value;
    }

    /**
     * Gets the value of the chspl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChspl() {
        return chspl;
    }

    /**
     * Sets the value of the chspl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChspl(String value) {
        this.chspl = value;
    }

    /**
     * Gets the value of the lprio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLprio() {
        return lprio;
    }

    /**
     * Sets the value of the lprio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLprio(String value) {
        this.lprio = value;
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
     * Gets the value of the vsbed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsbed() {
        return vsbed;
    }

    /**
     * Sets the value of the vsbed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsbed(String value) {
        this.vsbed = value;
    }

    /**
     * Gets the value of the faksd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaksd() {
        return faksd;
    }

    /**
     * Sets the value of the faksd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaksd(String value) {
        this.faksd = value;
    }

    /**
     * Gets the value of the mrnkz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMrnkz() {
        return mrnkz;
    }

    /**
     * Sets the value of the mrnkz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMrnkz(String value) {
        this.mrnkz = value;
    }

    /**
     * Gets the value of the perfk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerfk() {
        return perfk;
    }

    /**
     * Sets the value of the perfk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerfk(String value) {
        this.perfk = value;
    }

    /**
     * Gets the value of the perrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerrl() {
        return perrl;
    }

    /**
     * Sets the value of the perrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerrl(String value) {
        this.perrl = value;
    }

    /**
     * Gets the value of the kvakz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKvakz() {
        return kvakz;
    }

    /**
     * Sets the value of the kvakz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKvakz(String value) {
        this.kvakz = value;
    }

    /**
     * Gets the value of the kvawt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getKvawt() {
        return kvawt;
    }

    /**
     * Sets the value of the kvawt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setKvawt(BigDecimal value) {
        this.kvawt = value;
    }

    /**
     * Gets the value of the waers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaers() {
        return waers;
    }

    /**
     * Sets the value of the waers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaers(String value) {
        this.waers = value;
    }

    /**
     * Gets the value of the klabc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKlabc() {
        return klabc;
    }

    /**
     * Sets the value of the klabc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKlabc(String value) {
        this.klabc = value;
    }

    /**
     * Gets the value of the ktgrd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKtgrd() {
        return ktgrd;
    }

    /**
     * Sets the value of the ktgrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKtgrd(String value) {
        this.ktgrd = value;
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
     * Gets the value of the vwerk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVwerk() {
        return vwerk;
    }

    /**
     * Sets the value of the vwerk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVwerk(String value) {
        this.vwerk = value;
    }

    /**
     * Gets the value of the vkgrp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVkgrp() {
        return vkgrp;
    }

    /**
     * Sets the value of the vkgrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVkgrp(String value) {
        this.vkgrp = value;
    }

    /**
     * Gets the value of the vkbur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVkbur() {
        return vkbur;
    }

    /**
     * Sets the value of the vkbur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVkbur(String value) {
        this.vkbur = value;
    }

    /**
     * Gets the value of the vsort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsort() {
        return vsort;
    }

    /**
     * Sets the value of the vsort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsort(String value) {
        this.vsort = value;
    }

    /**
     * Gets the value of the kvgr1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKvgr1() {
        return kvgr1;
    }

    /**
     * Sets the value of the kvgr1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKvgr1(String value) {
        this.kvgr1 = value;
    }

    /**
     * Gets the value of the kvgr2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKvgr2() {
        return kvgr2;
    }

    /**
     * Sets the value of the kvgr2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKvgr2(String value) {
        this.kvgr2 = value;
    }

    /**
     * Gets the value of the kvgr3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKvgr3() {
        return kvgr3;
    }

    /**
     * Sets the value of the kvgr3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKvgr3(String value) {
        this.kvgr3 = value;
    }

    /**
     * Gets the value of the kvgr4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKvgr4() {
        return kvgr4;
    }

    /**
     * Sets the value of the kvgr4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKvgr4(String value) {
        this.kvgr4 = value;
    }

    /**
     * Gets the value of the kvgr5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKvgr5() {
        return kvgr5;
    }

    /**
     * Sets the value of the kvgr5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKvgr5(String value) {
        this.kvgr5 = value;
    }

    /**
     * Gets the value of the bokre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBokre() {
        return bokre;
    }

    /**
     * Sets the value of the bokre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBokre(String value) {
        this.bokre = value;
    }

    /**
     * Gets the value of the boidt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBoidt() {
        return boidt;
    }

    /**
     * Sets the value of the boidt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBoidt(XMLGregorianCalendar value) {
        this.boidt = value;
    }

    /**
     * Gets the value of the kurst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKurst() {
        return kurst;
    }

    /**
     * Sets the value of the kurst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKurst(String value) {
        this.kurst = value;
    }

    /**
     * Gets the value of the prfre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrfre() {
        return prfre;
    }

    /**
     * Sets the value of the prfre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrfre(String value) {
        this.prfre = value;
    }

    /**
     * Gets the value of the prat1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat1() {
        return prat1;
    }

    /**
     * Sets the value of the prat1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat1(String value) {
        this.prat1 = value;
    }

    /**
     * Gets the value of the prat2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat2() {
        return prat2;
    }

    /**
     * Sets the value of the prat2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat2(String value) {
        this.prat2 = value;
    }

    /**
     * Gets the value of the prat3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat3() {
        return prat3;
    }

    /**
     * Sets the value of the prat3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat3(String value) {
        this.prat3 = value;
    }

    /**
     * Gets the value of the prat4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat4() {
        return prat4;
    }

    /**
     * Sets the value of the prat4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat4(String value) {
        this.prat4 = value;
    }

    /**
     * Gets the value of the prat5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat5() {
        return prat5;
    }

    /**
     * Sets the value of the prat5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat5(String value) {
        this.prat5 = value;
    }

    /**
     * Gets the value of the prat6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat6() {
        return prat6;
    }

    /**
     * Sets the value of the prat6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat6(String value) {
        this.prat6 = value;
    }

    /**
     * Gets the value of the prat7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat7() {
        return prat7;
    }

    /**
     * Sets the value of the prat7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat7(String value) {
        this.prat7 = value;
    }

    /**
     * Gets the value of the prat8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat8() {
        return prat8;
    }

    /**
     * Sets the value of the prat8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat8(String value) {
        this.prat8 = value;
    }

    /**
     * Gets the value of the prat9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrat9() {
        return prat9;
    }

    /**
     * Sets the value of the prat9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrat9(String value) {
        this.prat9 = value;
    }

    /**
     * Gets the value of the prata property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrata() {
        return prata;
    }

    /**
     * Sets the value of the prata property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrata(String value) {
        this.prata = value;
    }

    /**
     * Gets the value of the kabss property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKabss() {
        return kabss;
    }

    /**
     * Sets the value of the kabss property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKabss(String value) {
        this.kabss = value;
    }

    /**
     * Gets the value of the kkber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKkber() {
        return kkber;
    }

    /**
     * Sets the value of the kkber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKkber(String value) {
        this.kkber = value;
    }

    /**
     * Gets the value of the cassd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCassd() {
        return cassd;
    }

    /**
     * Sets the value of the cassd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCassd(String value) {
        this.cassd = value;
    }

    /**
     * Gets the value of the rdoff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRdoff() {
        return rdoff;
    }

    /**
     * Sets the value of the rdoff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRdoff(String value) {
        this.rdoff = value;
    }

    /**
     * Gets the value of the agrel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgrel() {
        return agrel;
    }

    /**
     * Sets the value of the agrel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgrel(String value) {
        this.agrel = value;
    }

    /**
     * Gets the value of the megru property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMegru() {
        return megru;
    }

    /**
     * Sets the value of the megru property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMegru(String value) {
        this.megru = value;
    }

    /**
     * Gets the value of the uebto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUebto() {
        return uebto;
    }

    /**
     * Sets the value of the uebto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUebto(BigDecimal value) {
        this.uebto = value;
    }

    /**
     * Gets the value of the untto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUntto() {
        return untto;
    }

    /**
     * Sets the value of the untto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUntto(BigDecimal value) {
        this.untto = value;
    }

    /**
     * Gets the value of the uebtk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUebtk() {
        return uebtk;
    }

    /**
     * Sets the value of the uebtk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUebtk(String value) {
        this.uebtk = value;
    }

    /**
     * Gets the value of the pvksm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPvksm() {
        return pvksm;
    }

    /**
     * Sets the value of the pvksm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPvksm(String value) {
        this.pvksm = value;
    }

    /**
     * Gets the value of the podkz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodkz() {
        return podkz;
    }

    /**
     * Sets the value of the podkz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodkz(String value) {
        this.podkz = value;
    }

    /**
     * Gets the value of the podtg property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPodtg() {
        return podtg;
    }

    /**
     * Sets the value of the podtg property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPodtg(BigDecimal value) {
        this.podtg = value;
    }

    /**
     * Gets the value of the blind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlind() {
        return blind;
    }

    /**
     * Sets the value of the blind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlind(String value) {
        this.blind = value;
    }

    /**
     * Gets the value of the carrierNotif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierNotif() {
        return carrierNotif;
    }

    /**
     * Sets the value of the carrierNotif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierNotif(String value) {
        this.carrierNotif = value;
    }

    /**
     * Gets the value of the bev1Emlgpfand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBev1Emlgpfand() {
        return bev1Emlgpfand;
    }

    /**
     * Sets the value of the bev1Emlgpfand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBev1Emlgpfand(String value) {
        this.bev1Emlgpfand = value;
    }

    /**
     * Gets the value of the bev1Emlgforts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBev1Emlgforts() {
        return bev1Emlgforts;
    }

    /**
     * Sets the value of the bev1Emlgforts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBev1Emlgforts(String value) {
        this.bev1Emlgforts = value;
    }

}
