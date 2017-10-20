
package com.wumart.sap1.meipi.cus.maintain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Fknvk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Fknvk">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/>
 *         &lt;element name="Parnr" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="Kunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Namev" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Name1" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Ort01" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Adrnd" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Adrnp" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Abtpa" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Abtnr" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Uepar" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="Telf1" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="Anred" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Pafkt" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Parvo" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Pavip" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Parge" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Parla" type="{urn:sap-com:document:sap:rfc:functions}lang"/>
 *         &lt;element name="Gbdat" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Vrtnr" type="{urn:sap-com:document:sap:rfc:functions}numeric10"/>
 *         &lt;element name="Bryth" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Akver" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Nmail" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Parau" type="{urn:sap-com:document:sap:rfc:functions}char40"/>
 *         &lt;element name="Parh1" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Parh2" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Parh3" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Parh4" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Parh5" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Moab1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Mobi1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Moab2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Mobi2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Diab1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Dibi1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Diab2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Dibi2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Miab1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Mibi1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Miab2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Mibi2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Doab1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Dobi1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Doab2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Dobi2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Frab1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Frbi1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Frab2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Frbi2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Saab1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Sabi1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Saab2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Sabi2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Soab1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Sobi1" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Soab2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Sobi2" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Pakn1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Pakn2" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Pakn3" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Pakn4" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Pakn5" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Sortl" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Famst" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Spnam" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="TitelAp" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Erdat" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Ernam" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Duefl" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Lifnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Loevm" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kzherk" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Adrnp2" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Prsnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Kz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fknvk", propOrder = {
    "mandt",
    "parnr",
    "kunnr",
    "namev",
    "name1",
    "ort01",
    "adrnd",
    "adrnp",
    "abtpa",
    "abtnr",
    "uepar",
    "telf1",
    "anred",
    "pafkt",
    "parvo",
    "pavip",
    "parge",
    "parla",
    "gbdat",
    "vrtnr",
    "bryth",
    "akver",
    "nmail",
    "parau",
    "parh1",
    "parh2",
    "parh3",
    "parh4",
    "parh5",
    "moab1",
    "mobi1",
    "moab2",
    "mobi2",
    "diab1",
    "dibi1",
    "diab2",
    "dibi2",
    "miab1",
    "mibi1",
    "miab2",
    "mibi2",
    "doab1",
    "dobi1",
    "doab2",
    "dobi2",
    "frab1",
    "frbi1",
    "frab2",
    "frbi2",
    "saab1",
    "sabi1",
    "saab2",
    "sabi2",
    "soab1",
    "sobi1",
    "soab2",
    "sobi2",
    "pakn1",
    "pakn2",
    "pakn3",
    "pakn4",
    "pakn5",
    "sortl",
    "famst",
    "spnam",
    "titelAp",
    "erdat",
    "ernam",
    "duefl",
    "lifnr",
    "loevm",
    "kzherk",
    "adrnp2",
    "prsnr",
    "kz"
})
public class Fknvk {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "Parnr", required = true)
    protected String parnr;
    @XmlElement(name = "Kunnr", required = true)
    protected String kunnr;
    @XmlElement(name = "Namev", required = true)
    protected String namev;
    @XmlElement(name = "Name1", required = true)
    protected String name1;
    @XmlElement(name = "Ort01", required = true)
    protected String ort01;
    @XmlElement(name = "Adrnd", required = true)
    protected String adrnd;
    @XmlElement(name = "Adrnp", required = true)
    protected String adrnp;
    @XmlElement(name = "Abtpa", required = true)
    protected String abtpa;
    @XmlElement(name = "Abtnr", required = true)
    protected String abtnr;
    @XmlElement(name = "Uepar", required = true)
    protected String uepar;
    @XmlElement(name = "Telf1", required = true)
    protected String telf1;
    @XmlElement(name = "Anred", required = true)
    protected String anred;
    @XmlElement(name = "Pafkt", required = true)
    protected String pafkt;
    @XmlElement(name = "Parvo", required = true)
    protected String parvo;
    @XmlElement(name = "Pavip", required = true)
    protected String pavip;
    @XmlElement(name = "Parge", required = true)
    protected String parge;
    @XmlElement(name = "Parla", required = true)
    protected String parla;
    @XmlElement(name = "Gbdat", required = true)
    protected XMLGregorianCalendar gbdat;
    @XmlElement(name = "Vrtnr", required = true)
    protected String vrtnr;
    @XmlElement(name = "Bryth", required = true)
    protected String bryth;
    @XmlElement(name = "Akver", required = true)
    protected String akver;
    @XmlElement(name = "Nmail", required = true)
    protected String nmail;
    @XmlElement(name = "Parau", required = true)
    protected String parau;
    @XmlElement(name = "Parh1", required = true)
    protected String parh1;
    @XmlElement(name = "Parh2", required = true)
    protected String parh2;
    @XmlElement(name = "Parh3", required = true)
    protected String parh3;
    @XmlElement(name = "Parh4", required = true)
    protected String parh4;
    @XmlElement(name = "Parh5", required = true)
    protected String parh5;
    @XmlElement(name = "Moab1", required = true)
    protected XMLGregorianCalendar moab1;
    @XmlElement(name = "Mobi1", required = true)
    protected XMLGregorianCalendar mobi1;
    @XmlElement(name = "Moab2", required = true)
    protected XMLGregorianCalendar moab2;
    @XmlElement(name = "Mobi2", required = true)
    protected XMLGregorianCalendar mobi2;
    @XmlElement(name = "Diab1", required = true)
    protected XMLGregorianCalendar diab1;
    @XmlElement(name = "Dibi1", required = true)
    protected XMLGregorianCalendar dibi1;
    @XmlElement(name = "Diab2", required = true)
    protected XMLGregorianCalendar diab2;
    @XmlElement(name = "Dibi2", required = true)
    protected XMLGregorianCalendar dibi2;
    @XmlElement(name = "Miab1", required = true)
    protected XMLGregorianCalendar miab1;
    @XmlElement(name = "Mibi1", required = true)
    protected XMLGregorianCalendar mibi1;
    @XmlElement(name = "Miab2", required = true)
    protected XMLGregorianCalendar miab2;
    @XmlElement(name = "Mibi2", required = true)
    protected XMLGregorianCalendar mibi2;
    @XmlElement(name = "Doab1", required = true)
    protected XMLGregorianCalendar doab1;
    @XmlElement(name = "Dobi1", required = true)
    protected XMLGregorianCalendar dobi1;
    @XmlElement(name = "Doab2", required = true)
    protected XMLGregorianCalendar doab2;
    @XmlElement(name = "Dobi2", required = true)
    protected XMLGregorianCalendar dobi2;
    @XmlElement(name = "Frab1", required = true)
    protected XMLGregorianCalendar frab1;
    @XmlElement(name = "Frbi1", required = true)
    protected XMLGregorianCalendar frbi1;
    @XmlElement(name = "Frab2", required = true)
    protected XMLGregorianCalendar frab2;
    @XmlElement(name = "Frbi2", required = true)
    protected XMLGregorianCalendar frbi2;
    @XmlElement(name = "Saab1", required = true)
    protected XMLGregorianCalendar saab1;
    @XmlElement(name = "Sabi1", required = true)
    protected XMLGregorianCalendar sabi1;
    @XmlElement(name = "Saab2", required = true)
    protected XMLGregorianCalendar saab2;
    @XmlElement(name = "Sabi2", required = true)
    protected XMLGregorianCalendar sabi2;
    @XmlElement(name = "Soab1", required = true)
    protected XMLGregorianCalendar soab1;
    @XmlElement(name = "Sobi1", required = true)
    protected XMLGregorianCalendar sobi1;
    @XmlElement(name = "Soab2", required = true)
    protected XMLGregorianCalendar soab2;
    @XmlElement(name = "Sobi2", required = true)
    protected XMLGregorianCalendar sobi2;
    @XmlElement(name = "Pakn1", required = true)
    protected String pakn1;
    @XmlElement(name = "Pakn2", required = true)
    protected String pakn2;
    @XmlElement(name = "Pakn3", required = true)
    protected String pakn3;
    @XmlElement(name = "Pakn4", required = true)
    protected String pakn4;
    @XmlElement(name = "Pakn5", required = true)
    protected String pakn5;
    @XmlElement(name = "Sortl", required = true)
    protected String sortl;
    @XmlElement(name = "Famst", required = true)
    protected String famst;
    @XmlElement(name = "Spnam", required = true)
    protected String spnam;
    @XmlElement(name = "TitelAp", required = true)
    protected String titelAp;
    @XmlElement(name = "Erdat", required = true)
    protected XMLGregorianCalendar erdat;
    @XmlElement(name = "Ernam", required = true)
    protected String ernam;
    @XmlElement(name = "Duefl", required = true)
    protected String duefl;
    @XmlElement(name = "Lifnr", required = true)
    protected String lifnr;
    @XmlElement(name = "Loevm", required = true)
    protected String loevm;
    @XmlElement(name = "Kzherk", required = true)
    protected String kzherk;
    @XmlElement(name = "Adrnp2", required = true)
    protected String adrnp2;
    @XmlElement(name = "Prsnr", required = true)
    protected String prsnr;
    @XmlElement(name = "Kz", required = true)
    protected String kz;

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
     * Gets the value of the parnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParnr() {
        return parnr;
    }

    /**
     * Sets the value of the parnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParnr(String value) {
        this.parnr = value;
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
     * Gets the value of the namev property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamev() {
        return namev;
    }

    /**
     * Sets the value of the namev property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamev(String value) {
        this.namev = value;
    }

    /**
     * Gets the value of the name1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName1() {
        return name1;
    }

    /**
     * Sets the value of the name1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName1(String value) {
        this.name1 = value;
    }

    /**
     * Gets the value of the ort01 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrt01() {
        return ort01;
    }

    /**
     * Sets the value of the ort01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrt01(String value) {
        this.ort01 = value;
    }

    /**
     * Gets the value of the adrnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdrnd() {
        return adrnd;
    }

    /**
     * Sets the value of the adrnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdrnd(String value) {
        this.adrnd = value;
    }

    /**
     * Gets the value of the adrnp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdrnp() {
        return adrnp;
    }

    /**
     * Sets the value of the adrnp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdrnp(String value) {
        this.adrnp = value;
    }

    /**
     * Gets the value of the abtpa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbtpa() {
        return abtpa;
    }

    /**
     * Sets the value of the abtpa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbtpa(String value) {
        this.abtpa = value;
    }

    /**
     * Gets the value of the abtnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbtnr() {
        return abtnr;
    }

    /**
     * Sets the value of the abtnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbtnr(String value) {
        this.abtnr = value;
    }

    /**
     * Gets the value of the uepar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUepar() {
        return uepar;
    }

    /**
     * Sets the value of the uepar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUepar(String value) {
        this.uepar = value;
    }

    /**
     * Gets the value of the telf1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelf1() {
        return telf1;
    }

    /**
     * Sets the value of the telf1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelf1(String value) {
        this.telf1 = value;
    }

    /**
     * Gets the value of the anred property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnred() {
        return anred;
    }

    /**
     * Sets the value of the anred property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnred(String value) {
        this.anred = value;
    }

    /**
     * Gets the value of the pafkt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPafkt() {
        return pafkt;
    }

    /**
     * Sets the value of the pafkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPafkt(String value) {
        this.pafkt = value;
    }

    /**
     * Gets the value of the parvo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParvo() {
        return parvo;
    }

    /**
     * Sets the value of the parvo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParvo(String value) {
        this.parvo = value;
    }

    /**
     * Gets the value of the pavip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPavip() {
        return pavip;
    }

    /**
     * Sets the value of the pavip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPavip(String value) {
        this.pavip = value;
    }

    /**
     * Gets the value of the parge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParge() {
        return parge;
    }

    /**
     * Sets the value of the parge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParge(String value) {
        this.parge = value;
    }

    /**
     * Gets the value of the parla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParla() {
        return parla;
    }

    /**
     * Sets the value of the parla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParla(String value) {
        this.parla = value;
    }

    /**
     * Gets the value of the gbdat property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGbdat() {
        return gbdat;
    }

    /**
     * Sets the value of the gbdat property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGbdat(XMLGregorianCalendar value) {
        this.gbdat = value;
    }

    /**
     * Gets the value of the vrtnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrtnr() {
        return vrtnr;
    }

    /**
     * Sets the value of the vrtnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrtnr(String value) {
        this.vrtnr = value;
    }

    /**
     * Gets the value of the bryth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBryth() {
        return bryth;
    }

    /**
     * Sets the value of the bryth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBryth(String value) {
        this.bryth = value;
    }

    /**
     * Gets the value of the akver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAkver() {
        return akver;
    }

    /**
     * Sets the value of the akver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAkver(String value) {
        this.akver = value;
    }

    /**
     * Gets the value of the nmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNmail() {
        return nmail;
    }

    /**
     * Sets the value of the nmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNmail(String value) {
        this.nmail = value;
    }

    /**
     * Gets the value of the parau property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParau() {
        return parau;
    }

    /**
     * Sets the value of the parau property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParau(String value) {
        this.parau = value;
    }

    /**
     * Gets the value of the parh1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParh1() {
        return parh1;
    }

    /**
     * Sets the value of the parh1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParh1(String value) {
        this.parh1 = value;
    }

    /**
     * Gets the value of the parh2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParh2() {
        return parh2;
    }

    /**
     * Sets the value of the parh2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParh2(String value) {
        this.parh2 = value;
    }

    /**
     * Gets the value of the parh3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParh3() {
        return parh3;
    }

    /**
     * Sets the value of the parh3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParh3(String value) {
        this.parh3 = value;
    }

    /**
     * Gets the value of the parh4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParh4() {
        return parh4;
    }

    /**
     * Sets the value of the parh4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParh4(String value) {
        this.parh4 = value;
    }

    /**
     * Gets the value of the parh5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParh5() {
        return parh5;
    }

    /**
     * Sets the value of the parh5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParh5(String value) {
        this.parh5 = value;
    }

    /**
     * Gets the value of the moab1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMoab1() {
        return moab1;
    }

    /**
     * Sets the value of the moab1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMoab1(XMLGregorianCalendar value) {
        this.moab1 = value;
    }

    /**
     * Gets the value of the mobi1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMobi1() {
        return mobi1;
    }

    /**
     * Sets the value of the mobi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMobi1(XMLGregorianCalendar value) {
        this.mobi1 = value;
    }

    /**
     * Gets the value of the moab2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMoab2() {
        return moab2;
    }

    /**
     * Sets the value of the moab2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMoab2(XMLGregorianCalendar value) {
        this.moab2 = value;
    }

    /**
     * Gets the value of the mobi2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMobi2() {
        return mobi2;
    }

    /**
     * Sets the value of the mobi2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMobi2(XMLGregorianCalendar value) {
        this.mobi2 = value;
    }

    /**
     * Gets the value of the diab1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDiab1() {
        return diab1;
    }

    /**
     * Sets the value of the diab1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDiab1(XMLGregorianCalendar value) {
        this.diab1 = value;
    }

    /**
     * Gets the value of the dibi1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDibi1() {
        return dibi1;
    }

    /**
     * Sets the value of the dibi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDibi1(XMLGregorianCalendar value) {
        this.dibi1 = value;
    }

    /**
     * Gets the value of the diab2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDiab2() {
        return diab2;
    }

    /**
     * Sets the value of the diab2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDiab2(XMLGregorianCalendar value) {
        this.diab2 = value;
    }

    /**
     * Gets the value of the dibi2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDibi2() {
        return dibi2;
    }

    /**
     * Sets the value of the dibi2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDibi2(XMLGregorianCalendar value) {
        this.dibi2 = value;
    }

    /**
     * Gets the value of the miab1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMiab1() {
        return miab1;
    }

    /**
     * Sets the value of the miab1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMiab1(XMLGregorianCalendar value) {
        this.miab1 = value;
    }

    /**
     * Gets the value of the mibi1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMibi1() {
        return mibi1;
    }

    /**
     * Sets the value of the mibi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMibi1(XMLGregorianCalendar value) {
        this.mibi1 = value;
    }

    /**
     * Gets the value of the miab2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMiab2() {
        return miab2;
    }

    /**
     * Sets the value of the miab2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMiab2(XMLGregorianCalendar value) {
        this.miab2 = value;
    }

    /**
     * Gets the value of the mibi2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMibi2() {
        return mibi2;
    }

    /**
     * Sets the value of the mibi2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMibi2(XMLGregorianCalendar value) {
        this.mibi2 = value;
    }

    /**
     * Gets the value of the doab1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDoab1() {
        return doab1;
    }

    /**
     * Sets the value of the doab1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDoab1(XMLGregorianCalendar value) {
        this.doab1 = value;
    }

    /**
     * Gets the value of the dobi1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDobi1() {
        return dobi1;
    }

    /**
     * Sets the value of the dobi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDobi1(XMLGregorianCalendar value) {
        this.dobi1 = value;
    }

    /**
     * Gets the value of the doab2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDoab2() {
        return doab2;
    }

    /**
     * Sets the value of the doab2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDoab2(XMLGregorianCalendar value) {
        this.doab2 = value;
    }

    /**
     * Gets the value of the dobi2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDobi2() {
        return dobi2;
    }

    /**
     * Sets the value of the dobi2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDobi2(XMLGregorianCalendar value) {
        this.dobi2 = value;
    }

    /**
     * Gets the value of the frab1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrab1() {
        return frab1;
    }

    /**
     * Sets the value of the frab1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrab1(XMLGregorianCalendar value) {
        this.frab1 = value;
    }

    /**
     * Gets the value of the frbi1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrbi1() {
        return frbi1;
    }

    /**
     * Sets the value of the frbi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrbi1(XMLGregorianCalendar value) {
        this.frbi1 = value;
    }

    /**
     * Gets the value of the frab2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrab2() {
        return frab2;
    }

    /**
     * Sets the value of the frab2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrab2(XMLGregorianCalendar value) {
        this.frab2 = value;
    }

    /**
     * Gets the value of the frbi2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrbi2() {
        return frbi2;
    }

    /**
     * Sets the value of the frbi2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrbi2(XMLGregorianCalendar value) {
        this.frbi2 = value;
    }

    /**
     * Gets the value of the saab1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSaab1() {
        return saab1;
    }

    /**
     * Sets the value of the saab1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSaab1(XMLGregorianCalendar value) {
        this.saab1 = value;
    }

    /**
     * Gets the value of the sabi1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSabi1() {
        return sabi1;
    }

    /**
     * Sets the value of the sabi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSabi1(XMLGregorianCalendar value) {
        this.sabi1 = value;
    }

    /**
     * Gets the value of the saab2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSaab2() {
        return saab2;
    }

    /**
     * Sets the value of the saab2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSaab2(XMLGregorianCalendar value) {
        this.saab2 = value;
    }

    /**
     * Gets the value of the sabi2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSabi2() {
        return sabi2;
    }

    /**
     * Sets the value of the sabi2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSabi2(XMLGregorianCalendar value) {
        this.sabi2 = value;
    }

    /**
     * Gets the value of the soab1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSoab1() {
        return soab1;
    }

    /**
     * Sets the value of the soab1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSoab1(XMLGregorianCalendar value) {
        this.soab1 = value;
    }

    /**
     * Gets the value of the sobi1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSobi1() {
        return sobi1;
    }

    /**
     * Sets the value of the sobi1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSobi1(XMLGregorianCalendar value) {
        this.sobi1 = value;
    }

    /**
     * Gets the value of the soab2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSoab2() {
        return soab2;
    }

    /**
     * Sets the value of the soab2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSoab2(XMLGregorianCalendar value) {
        this.soab2 = value;
    }

    /**
     * Gets the value of the sobi2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSobi2() {
        return sobi2;
    }

    /**
     * Sets the value of the sobi2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSobi2(XMLGregorianCalendar value) {
        this.sobi2 = value;
    }

    /**
     * Gets the value of the pakn1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPakn1() {
        return pakn1;
    }

    /**
     * Sets the value of the pakn1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPakn1(String value) {
        this.pakn1 = value;
    }

    /**
     * Gets the value of the pakn2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPakn2() {
        return pakn2;
    }

    /**
     * Sets the value of the pakn2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPakn2(String value) {
        this.pakn2 = value;
    }

    /**
     * Gets the value of the pakn3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPakn3() {
        return pakn3;
    }

    /**
     * Sets the value of the pakn3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPakn3(String value) {
        this.pakn3 = value;
    }

    /**
     * Gets the value of the pakn4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPakn4() {
        return pakn4;
    }

    /**
     * Sets the value of the pakn4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPakn4(String value) {
        this.pakn4 = value;
    }

    /**
     * Gets the value of the pakn5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPakn5() {
        return pakn5;
    }

    /**
     * Sets the value of the pakn5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPakn5(String value) {
        this.pakn5 = value;
    }

    /**
     * Gets the value of the sortl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortl() {
        return sortl;
    }

    /**
     * Sets the value of the sortl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortl(String value) {
        this.sortl = value;
    }

    /**
     * Gets the value of the famst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamst() {
        return famst;
    }

    /**
     * Sets the value of the famst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamst(String value) {
        this.famst = value;
    }

    /**
     * Gets the value of the spnam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpnam() {
        return spnam;
    }

    /**
     * Sets the value of the spnam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpnam(String value) {
        this.spnam = value;
    }

    /**
     * Gets the value of the titelAp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitelAp() {
        return titelAp;
    }

    /**
     * Sets the value of the titelAp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitelAp(String value) {
        this.titelAp = value;
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
     * Gets the value of the duefl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuefl() {
        return duefl;
    }

    /**
     * Sets the value of the duefl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuefl(String value) {
        this.duefl = value;
    }

    /**
     * Gets the value of the lifnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifnr() {
        return lifnr;
    }

    /**
     * Sets the value of the lifnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifnr(String value) {
        this.lifnr = value;
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
     * Gets the value of the kzherk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKzherk() {
        return kzherk;
    }

    /**
     * Sets the value of the kzherk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKzherk(String value) {
        this.kzherk = value;
    }

    /**
     * Gets the value of the adrnp2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdrnp2() {
        return adrnp2;
    }

    /**
     * Sets the value of the adrnp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdrnp2(String value) {
        this.adrnp2 = value;
    }

    /**
     * Gets the value of the prsnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrsnr() {
        return prsnr;
    }

    /**
     * Sets the value of the prsnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrsnr(String value) {
        this.prsnr = value;
    }

    /**
     * Gets the value of the kz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKz() {
        return kz;
    }

    /**
     * Sets the value of the kz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKz(String value) {
        this.kz = value;
    }

}
