
package com.wumart.sap1.meipi.cus.maintain;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Kna1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Kna1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mandt" type="{urn:sap-com:document:sap:rfc:functions}clnt3"/>
 *         &lt;element name="Kunnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Land1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Name1" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Name2" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Ort01" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Pstlz" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Regio" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Sortl" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Stras" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Telf1" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="Telfx" type="{urn:sap-com:document:sap:rfc:functions}char31"/>
 *         &lt;element name="Xcpdk" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Adrnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Mcod1" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="Mcod2" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="Mcod3" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="Anred" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="Aufsd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Bahne" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="Bahns" type="{urn:sap-com:document:sap:rfc:functions}char25"/>
 *         &lt;element name="Bbbnr" type="{urn:sap-com:document:sap:rfc:functions}numeric7"/>
 *         &lt;element name="Bbsnr" type="{urn:sap-com:document:sap:rfc:functions}numeric5"/>
 *         &lt;element name="Begru" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Brsch" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Bubkz" type="{urn:sap-com:document:sap:rfc:functions}numeric1"/>
 *         &lt;element name="Datlt" type="{urn:sap-com:document:sap:rfc:functions}char14"/>
 *         &lt;element name="Erdat" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Ernam" type="{urn:sap-com:document:sap:rfc:functions}char12"/>
 *         &lt;element name="Exabl" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Faksd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Fiskn" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Knazk" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Knrza" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Konzs" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Ktokd" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Kukla" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Lifnr" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Lifsd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Locco" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Loevm" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Name3" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Name4" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Niels" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Ort02" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Pfach" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Pstl2" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Counc" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Cityc" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Rpmkr" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="Sperr" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Spras" type="{urn:sap-com:document:sap:rfc:functions}lang"/>
 *         &lt;element name="Stcd1" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="Stcd2" type="{urn:sap-com:document:sap:rfc:functions}char11"/>
 *         &lt;element name="Stkza" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Stkzu" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Telbx" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="Telf2" type="{urn:sap-com:document:sap:rfc:functions}char16"/>
 *         &lt;element name="Teltx" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Telx1" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Lzone" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Xzemp" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Vbund" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="Stceg" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Dear1" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Dear2" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Dear3" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Dear4" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Dear5" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Gform" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Bran1" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Bran2" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Bran3" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Bran4" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Bran5" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Ekont" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Umsat" type="{urn:sap-com:document:sap:rfc:functions}curr8.2"/>
 *         &lt;element name="Umjah" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="Uwaer" type="{urn:sap-com:document:sap:rfc:functions}cuky5"/>
 *         &lt;element name="Jmzah" type="{urn:sap-com:document:sap:rfc:functions}numeric6"/>
 *         &lt;element name="Jmjah" type="{urn:sap-com:document:sap:rfc:functions}numeric4"/>
 *         &lt;element name="Katr1" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Katr2" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Katr3" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Katr4" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Katr5" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Katr6" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Katr7" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Katr8" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Katr9" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Katr10" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Stkzn" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Umsa1" type="{urn:sap-com:document:sap:rfc:functions}curr15.2"/>
 *         &lt;element name="Txjcd" type="{urn:sap-com:document:sap:rfc:functions}char15"/>
 *         &lt;element name="Periv" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Abrvw" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Inspbydebi" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Inspatdebi" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Ktocd" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Pfort" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Werks" type="{urn:sap-com:document:sap:rfc:functions}char4"/>
 *         &lt;element name="Dtams" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Dtaws" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Duefl" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Hzuor" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="Sperz" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Etikg" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Civve" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Milve" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Kdkg1" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Kdkg2" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Kdkg3" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Kdkg4" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Kdkg5" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Xknza" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Fityp" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Stcdt" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Stcd3" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="Stcd4" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="Stcd5" type="{urn:sap-com:document:sap:rfc:functions}char60"/>
 *         &lt;element name="Xicms" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Xxipi" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Xsubt" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Cfopc" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Txlw1" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Txlw2" type="{urn:sap-com:document:sap:rfc:functions}char3"/>
 *         &lt;element name="Ccc01" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Ccc02" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Ccc03" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Ccc04" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Cassd" type="{urn:sap-com:document:sap:rfc:functions}char2"/>
 *         &lt;element name="Knurl" type="{urn:sap-com:document:sap:rfc:functions}char132"/>
 *         &lt;element name="J1kfrepre" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="J1kftbus" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="J1kftind" type="{urn:sap-com:document:sap:rfc:functions}char30"/>
 *         &lt;element name="Confs" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Updat" type="{urn:sap-com:document:sap:rfc:functions}date"/>
 *         &lt;element name="Uptim" type="{urn:sap-com:document:sap:rfc:functions}time"/>
 *         &lt;element name="Nodel" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="Dear6" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="_-vso_-rPalhgt" type="{urn:sap-com:document:sap:rfc:functions}quantum13.3"/>
 *         &lt;element name="_-vso_-rPalUl" type="{urn:sap-com:document:sap:rfc:functions}unit3"/>
 *         &lt;element name="_-vso_-rPkMat" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="_-vso_-rMatpal" type="{urn:sap-com:document:sap:rfc:functions}char18"/>
 *         &lt;element name="_-vso_-rINoLyr" type="{urn:sap-com:document:sap:rfc:functions}numeric2"/>
 *         &lt;element name="_-vso_-rOneMat" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="_-vso_-rOneSort" type="{urn:sap-com:document:sap:rfc:functions}char1"/>
 *         &lt;element name="_-vso_-rUldSide" type="{urn:sap-com:document:sap:rfc:functions}numeric1"/>
 *         &lt;element name="_-vso_-rLoadPref" type="{urn:sap-com:document:sap:rfc:functions}numeric1"/>
 *         &lt;element name="_-vso_-rDpoint" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Alc" type="{urn:sap-com:document:sap:rfc:functions}char8"/>
 *         &lt;element name="PmtOffice" type="{urn:sap-com:document:sap:rfc:functions}char5"/>
 *         &lt;element name="Psofg" type="{urn:sap-com:document:sap:rfc:functions}char10"/>
 *         &lt;element name="Psois" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Pson1" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Pson2" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Pson3" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Psovn" type="{urn:sap-com:document:sap:rfc:functions}char35"/>
 *         &lt;element name="Psotl" type="{urn:sap-com:document:sap:rfc:functions}char20"/>
 *         &lt;element name="Psohs" type="{urn:sap-com:document:sap:rfc:functions}char6"/>
 *         &lt;element name="Psost" type="{urn:sap-com:document:sap:rfc:functions}char28"/>
 *         &lt;element name="Psoo1" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Psoo2" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Psoo3" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Psoo4" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *         &lt;element name="Psoo5" type="{urn:sap-com:document:sap:rfc:functions}char50"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Kna1", propOrder = {
    "mandt",
    "kunnr",
    "land1",
    "name1",
    "name2",
    "ort01",
    "pstlz",
    "regio",
    "sortl",
    "stras",
    "telf1",
    "telfx",
    "xcpdk",
    "adrnr",
    "mcod1",
    "mcod2",
    "mcod3",
    "anred",
    "aufsd",
    "bahne",
    "bahns",
    "bbbnr",
    "bbsnr",
    "begru",
    "brsch",
    "bubkz",
    "datlt",
    "erdat",
    "ernam",
    "exabl",
    "faksd",
    "fiskn",
    "knazk",
    "knrza",
    "konzs",
    "ktokd",
    "kukla",
    "lifnr",
    "lifsd",
    "locco",
    "loevm",
    "name3",
    "name4",
    "niels",
    "ort02",
    "pfach",
    "pstl2",
    "counc",
    "cityc",
    "rpmkr",
    "sperr",
    "spras",
    "stcd1",
    "stcd2",
    "stkza",
    "stkzu",
    "telbx",
    "telf2",
    "teltx",
    "telx1",
    "lzone",
    "xzemp",
    "vbund",
    "stceg",
    "dear1",
    "dear2",
    "dear3",
    "dear4",
    "dear5",
    "gform",
    "bran1",
    "bran2",
    "bran3",
    "bran4",
    "bran5",
    "ekont",
    "umsat",
    "umjah",
    "uwaer",
    "jmzah",
    "jmjah",
    "katr1",
    "katr2",
    "katr3",
    "katr4",
    "katr5",
    "katr6",
    "katr7",
    "katr8",
    "katr9",
    "katr10",
    "stkzn",
    "umsa1",
    "txjcd",
    "periv",
    "abrvw",
    "inspbydebi",
    "inspatdebi",
    "ktocd",
    "pfort",
    "werks",
    "dtams",
    "dtaws",
    "duefl",
    "hzuor",
    "sperz",
    "etikg",
    "civve",
    "milve",
    "kdkg1",
    "kdkg2",
    "kdkg3",
    "kdkg4",
    "kdkg5",
    "xknza",
    "fityp",
    "stcdt",
    "stcd3",
    "stcd4",
    "stcd5",
    "xicms",
    "xxipi",
    "xsubt",
    "cfopc",
    "txlw1",
    "txlw2",
    "ccc01",
    "ccc02",
    "ccc03",
    "ccc04",
    "cassd",
    "knurl",
    "j1Kfrepre",
    "j1Kftbus",
    "j1Kftind",
    "confs",
    "updat",
    "uptim",
    "nodel",
    "dear6",
    "vsoRPalhgt",
    "vsoRPalUl",
    "vsoRPkMat",
    "vsoRMatpal",
    "vsoRINoLyr",
    "vsoROneMat",
    "vsoROneSort",
    "vsoRUldSide",
    "vsoRLoadPref",
    "vsoRDpoint",
    "alc",
    "pmtOffice",
    "psofg",
    "psois",
    "pson1",
    "pson2",
    "pson3",
    "psovn",
    "psotl",
    "psohs",
    "psost",
    "psoo1",
    "psoo2",
    "psoo3",
    "psoo4",
    "psoo5"
})
public class Kna1 {

    @XmlElement(name = "Mandt", required = true)
    protected String mandt;
    @XmlElement(name = "Kunnr", required = true)
    protected String kunnr;
    @XmlElement(name = "Land1", required = true)
    protected String land1;
    @XmlElement(name = "Name1", required = true)
    protected String name1;
    @XmlElement(name = "Name2", required = true)
    protected String name2;
    @XmlElement(name = "Ort01", required = true)
    protected String ort01;
    @XmlElement(name = "Pstlz", required = true)
    protected String pstlz;
    @XmlElement(name = "Regio", required = true)
    protected String regio;
    @XmlElement(name = "Sortl", required = true)
    protected String sortl;
    @XmlElement(name = "Stras", required = true)
    protected String stras;
    @XmlElement(name = "Telf1", required = true)
    protected String telf1;
    @XmlElement(name = "Telfx", required = true)
    protected String telfx;
    @XmlElement(name = "Xcpdk", required = true)
    protected String xcpdk;
    @XmlElement(name = "Adrnr", required = true)
    protected String adrnr;
    @XmlElement(name = "Mcod1", required = true)
    protected String mcod1;
    @XmlElement(name = "Mcod2", required = true)
    protected String mcod2;
    @XmlElement(name = "Mcod3", required = true)
    protected String mcod3;
    @XmlElement(name = "Anred", required = true)
    protected String anred;
    @XmlElement(name = "Aufsd", required = true)
    protected String aufsd;
    @XmlElement(name = "Bahne", required = true)
    protected String bahne;
    @XmlElement(name = "Bahns", required = true)
    protected String bahns;
    @XmlElement(name = "Bbbnr", required = true)
    protected String bbbnr;
    @XmlElement(name = "Bbsnr", required = true)
    protected String bbsnr;
    @XmlElement(name = "Begru", required = true)
    protected String begru;
    @XmlElement(name = "Brsch", required = true)
    protected String brsch;
    @XmlElement(name = "Bubkz", required = true)
    protected String bubkz;
    @XmlElement(name = "Datlt", required = true)
    protected String datlt;
    @XmlElement(name = "Erdat", required = true)
    protected XMLGregorianCalendar erdat;
    @XmlElement(name = "Ernam", required = true)
    protected String ernam;
    @XmlElement(name = "Exabl", required = true)
    protected String exabl;
    @XmlElement(name = "Faksd", required = true)
    protected String faksd;
    @XmlElement(name = "Fiskn", required = true)
    protected String fiskn;
    @XmlElement(name = "Knazk", required = true)
    protected String knazk;
    @XmlElement(name = "Knrza", required = true)
    protected String knrza;
    @XmlElement(name = "Konzs", required = true)
    protected String konzs;
    @XmlElement(name = "Ktokd", required = true)
    protected String ktokd;
    @XmlElement(name = "Kukla", required = true)
    protected String kukla;
    @XmlElement(name = "Lifnr", required = true)
    protected String lifnr;
    @XmlElement(name = "Lifsd", required = true)
    protected String lifsd;
    @XmlElement(name = "Locco", required = true)
    protected String locco;
    @XmlElement(name = "Loevm", required = true)
    protected String loevm;
    @XmlElement(name = "Name3", required = true)
    protected String name3;
    @XmlElement(name = "Name4", required = true)
    protected String name4;
    @XmlElement(name = "Niels", required = true)
    protected String niels;
    @XmlElement(name = "Ort02", required = true)
    protected String ort02;
    @XmlElement(name = "Pfach", required = true)
    protected String pfach;
    @XmlElement(name = "Pstl2", required = true)
    protected String pstl2;
    @XmlElement(name = "Counc", required = true)
    protected String counc;
    @XmlElement(name = "Cityc", required = true)
    protected String cityc;
    @XmlElement(name = "Rpmkr", required = true)
    protected String rpmkr;
    @XmlElement(name = "Sperr", required = true)
    protected String sperr;
    @XmlElement(name = "Spras", required = true)
    protected String spras;
    @XmlElement(name = "Stcd1", required = true)
    protected String stcd1;
    @XmlElement(name = "Stcd2", required = true)
    protected String stcd2;
    @XmlElement(name = "Stkza", required = true)
    protected String stkza;
    @XmlElement(name = "Stkzu", required = true)
    protected String stkzu;
    @XmlElement(name = "Telbx", required = true)
    protected String telbx;
    @XmlElement(name = "Telf2", required = true)
    protected String telf2;
    @XmlElement(name = "Teltx", required = true)
    protected String teltx;
    @XmlElement(name = "Telx1", required = true)
    protected String telx1;
    @XmlElement(name = "Lzone", required = true)
    protected String lzone;
    @XmlElement(name = "Xzemp", required = true)
    protected String xzemp;
    @XmlElement(name = "Vbund", required = true)
    protected String vbund;
    @XmlElement(name = "Stceg", required = true)
    protected String stceg;
    @XmlElement(name = "Dear1", required = true)
    protected String dear1;
    @XmlElement(name = "Dear2", required = true)
    protected String dear2;
    @XmlElement(name = "Dear3", required = true)
    protected String dear3;
    @XmlElement(name = "Dear4", required = true)
    protected String dear4;
    @XmlElement(name = "Dear5", required = true)
    protected String dear5;
    @XmlElement(name = "Gform", required = true)
    protected String gform;
    @XmlElement(name = "Bran1", required = true)
    protected String bran1;
    @XmlElement(name = "Bran2", required = true)
    protected String bran2;
    @XmlElement(name = "Bran3", required = true)
    protected String bran3;
    @XmlElement(name = "Bran4", required = true)
    protected String bran4;
    @XmlElement(name = "Bran5", required = true)
    protected String bran5;
    @XmlElement(name = "Ekont", required = true)
    protected String ekont;
    @XmlElement(name = "Umsat", required = true)
    protected BigDecimal umsat;
    @XmlElement(name = "Umjah", required = true)
    protected String umjah;
    @XmlElement(name = "Uwaer", required = true)
    protected String uwaer;
    @XmlElement(name = "Jmzah", required = true)
    protected String jmzah;
    @XmlElement(name = "Jmjah", required = true)
    protected String jmjah;
    @XmlElement(name = "Katr1", required = true)
    protected String katr1;
    @XmlElement(name = "Katr2", required = true)
    protected String katr2;
    @XmlElement(name = "Katr3", required = true)
    protected String katr3;
    @XmlElement(name = "Katr4", required = true)
    protected String katr4;
    @XmlElement(name = "Katr5", required = true)
    protected String katr5;
    @XmlElement(name = "Katr6", required = true)
    protected String katr6;
    @XmlElement(name = "Katr7", required = true)
    protected String katr7;
    @XmlElement(name = "Katr8", required = true)
    protected String katr8;
    @XmlElement(name = "Katr9", required = true)
    protected String katr9;
    @XmlElement(name = "Katr10", required = true)
    protected String katr10;
    @XmlElement(name = "Stkzn", required = true)
    protected String stkzn;
    @XmlElement(name = "Umsa1", required = true)
    protected BigDecimal umsa1;
    @XmlElement(name = "Txjcd", required = true)
    protected String txjcd;
    @XmlElement(name = "Periv", required = true)
    protected String periv;
    @XmlElement(name = "Abrvw", required = true)
    protected String abrvw;
    @XmlElement(name = "Inspbydebi", required = true)
    protected String inspbydebi;
    @XmlElement(name = "Inspatdebi", required = true)
    protected String inspatdebi;
    @XmlElement(name = "Ktocd", required = true)
    protected String ktocd;
    @XmlElement(name = "Pfort", required = true)
    protected String pfort;
    @XmlElement(name = "Werks", required = true)
    protected String werks;
    @XmlElement(name = "Dtams", required = true)
    protected String dtams;
    @XmlElement(name = "Dtaws", required = true)
    protected String dtaws;
    @XmlElement(name = "Duefl", required = true)
    protected String duefl;
    @XmlElement(name = "Hzuor", required = true)
    protected String hzuor;
    @XmlElement(name = "Sperz", required = true)
    protected String sperz;
    @XmlElement(name = "Etikg", required = true)
    protected String etikg;
    @XmlElement(name = "Civve", required = true)
    protected String civve;
    @XmlElement(name = "Milve", required = true)
    protected String milve;
    @XmlElement(name = "Kdkg1", required = true)
    protected String kdkg1;
    @XmlElement(name = "Kdkg2", required = true)
    protected String kdkg2;
    @XmlElement(name = "Kdkg3", required = true)
    protected String kdkg3;
    @XmlElement(name = "Kdkg4", required = true)
    protected String kdkg4;
    @XmlElement(name = "Kdkg5", required = true)
    protected String kdkg5;
    @XmlElement(name = "Xknza", required = true)
    protected String xknza;
    @XmlElement(name = "Fityp", required = true)
    protected String fityp;
    @XmlElement(name = "Stcdt", required = true)
    protected String stcdt;
    @XmlElement(name = "Stcd3", required = true)
    protected String stcd3;
    @XmlElement(name = "Stcd4", required = true)
    protected String stcd4;
    @XmlElement(name = "Stcd5", required = true)
    protected String stcd5;
    @XmlElement(name = "Xicms", required = true)
    protected String xicms;
    @XmlElement(name = "Xxipi", required = true)
    protected String xxipi;
    @XmlElement(name = "Xsubt", required = true)
    protected String xsubt;
    @XmlElement(name = "Cfopc", required = true)
    protected String cfopc;
    @XmlElement(name = "Txlw1", required = true)
    protected String txlw1;
    @XmlElement(name = "Txlw2", required = true)
    protected String txlw2;
    @XmlElement(name = "Ccc01", required = true)
    protected String ccc01;
    @XmlElement(name = "Ccc02", required = true)
    protected String ccc02;
    @XmlElement(name = "Ccc03", required = true)
    protected String ccc03;
    @XmlElement(name = "Ccc04", required = true)
    protected String ccc04;
    @XmlElement(name = "Cassd", required = true)
    protected String cassd;
    @XmlElement(name = "Knurl", required = true)
    protected String knurl;
    @XmlElement(name = "J1kfrepre", required = true)
    protected String j1Kfrepre;
    @XmlElement(name = "J1kftbus", required = true)
    protected String j1Kftbus;
    @XmlElement(name = "J1kftind", required = true)
    protected String j1Kftind;
    @XmlElement(name = "Confs", required = true)
    protected String confs;
    @XmlElement(name = "Updat", required = true)
    protected XMLGregorianCalendar updat;
    @XmlElement(name = "Uptim", required = true)
    protected XMLGregorianCalendar uptim;
    @XmlElement(name = "Nodel", required = true)
    protected String nodel;
    @XmlElement(name = "Dear6", required = true)
    protected String dear6;
    @XmlElement(name = "_-vso_-rPalhgt", required = true)
    protected BigDecimal vsoRPalhgt;
    @XmlElement(name = "_-vso_-rPalUl", required = true)
    protected String vsoRPalUl;
    @XmlElement(name = "_-vso_-rPkMat", required = true)
    protected String vsoRPkMat;
    @XmlElement(name = "_-vso_-rMatpal", required = true)
    protected String vsoRMatpal;
    @XmlElement(name = "_-vso_-rINoLyr", required = true)
    protected String vsoRINoLyr;
    @XmlElement(name = "_-vso_-rOneMat", required = true)
    protected String vsoROneMat;
    @XmlElement(name = "_-vso_-rOneSort", required = true)
    protected String vsoROneSort;
    @XmlElement(name = "_-vso_-rUldSide", required = true)
    protected String vsoRUldSide;
    @XmlElement(name = "_-vso_-rLoadPref", required = true)
    protected String vsoRLoadPref;
    @XmlElement(name = "_-vso_-rDpoint", required = true)
    protected String vsoRDpoint;
    @XmlElement(name = "Alc", required = true)
    protected String alc;
    @XmlElement(name = "PmtOffice", required = true)
    protected String pmtOffice;
    @XmlElement(name = "Psofg", required = true)
    protected String psofg;
    @XmlElement(name = "Psois", required = true)
    protected String psois;
    @XmlElement(name = "Pson1", required = true)
    protected String pson1;
    @XmlElement(name = "Pson2", required = true)
    protected String pson2;
    @XmlElement(name = "Pson3", required = true)
    protected String pson3;
    @XmlElement(name = "Psovn", required = true)
    protected String psovn;
    @XmlElement(name = "Psotl", required = true)
    protected String psotl;
    @XmlElement(name = "Psohs", required = true)
    protected String psohs;
    @XmlElement(name = "Psost", required = true)
    protected String psost;
    @XmlElement(name = "Psoo1", required = true)
    protected String psoo1;
    @XmlElement(name = "Psoo2", required = true)
    protected String psoo2;
    @XmlElement(name = "Psoo3", required = true)
    protected String psoo3;
    @XmlElement(name = "Psoo4", required = true)
    protected String psoo4;
    @XmlElement(name = "Psoo5", required = true)
    protected String psoo5;

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
     * Gets the value of the land1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLand1() {
        return land1;
    }

    /**
     * Sets the value of the land1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLand1(String value) {
        this.land1 = value;
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
     * Gets the value of the pstlz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPstlz() {
        return pstlz;
    }

    /**
     * Sets the value of the pstlz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPstlz(String value) {
        this.pstlz = value;
    }

    /**
     * Gets the value of the regio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegio() {
        return regio;
    }

    /**
     * Sets the value of the regio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegio(String value) {
        this.regio = value;
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
     * Gets the value of the stras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStras() {
        return stras;
    }

    /**
     * Sets the value of the stras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStras(String value) {
        this.stras = value;
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
     * Gets the value of the telfx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelfx() {
        return telfx;
    }

    /**
     * Sets the value of the telfx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelfx(String value) {
        this.telfx = value;
    }

    /**
     * Gets the value of the xcpdk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXcpdk() {
        return xcpdk;
    }

    /**
     * Sets the value of the xcpdk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXcpdk(String value) {
        this.xcpdk = value;
    }

    /**
     * Gets the value of the adrnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdrnr() {
        return adrnr;
    }

    /**
     * Sets the value of the adrnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdrnr(String value) {
        this.adrnr = value;
    }

    /**
     * Gets the value of the mcod1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMcod1() {
        return mcod1;
    }

    /**
     * Sets the value of the mcod1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMcod1(String value) {
        this.mcod1 = value;
    }

    /**
     * Gets the value of the mcod2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMcod2() {
        return mcod2;
    }

    /**
     * Sets the value of the mcod2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMcod2(String value) {
        this.mcod2 = value;
    }

    /**
     * Gets the value of the mcod3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMcod3() {
        return mcod3;
    }

    /**
     * Sets the value of the mcod3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMcod3(String value) {
        this.mcod3 = value;
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
     * Gets the value of the bahne property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBahne() {
        return bahne;
    }

    /**
     * Sets the value of the bahne property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBahne(String value) {
        this.bahne = value;
    }

    /**
     * Gets the value of the bahns property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBahns() {
        return bahns;
    }

    /**
     * Sets the value of the bahns property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBahns(String value) {
        this.bahns = value;
    }

    /**
     * Gets the value of the bbbnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBbbnr() {
        return bbbnr;
    }

    /**
     * Sets the value of the bbbnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBbbnr(String value) {
        this.bbbnr = value;
    }

    /**
     * Gets the value of the bbsnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBbsnr() {
        return bbsnr;
    }

    /**
     * Sets the value of the bbsnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBbsnr(String value) {
        this.bbsnr = value;
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
     * Gets the value of the brsch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrsch() {
        return brsch;
    }

    /**
     * Sets the value of the brsch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrsch(String value) {
        this.brsch = value;
    }

    /**
     * Gets the value of the bubkz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBubkz() {
        return bubkz;
    }

    /**
     * Sets the value of the bubkz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBubkz(String value) {
        this.bubkz = value;
    }

    /**
     * Gets the value of the datlt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatlt() {
        return datlt;
    }

    /**
     * Sets the value of the datlt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatlt(String value) {
        this.datlt = value;
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
     * Gets the value of the exabl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExabl() {
        return exabl;
    }

    /**
     * Sets the value of the exabl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExabl(String value) {
        this.exabl = value;
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
     * Gets the value of the fiskn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiskn() {
        return fiskn;
    }

    /**
     * Sets the value of the fiskn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiskn(String value) {
        this.fiskn = value;
    }

    /**
     * Gets the value of the knazk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnazk() {
        return knazk;
    }

    /**
     * Sets the value of the knazk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnazk(String value) {
        this.knazk = value;
    }

    /**
     * Gets the value of the knrza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnrza() {
        return knrza;
    }

    /**
     * Sets the value of the knrza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnrza(String value) {
        this.knrza = value;
    }

    /**
     * Gets the value of the konzs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKonzs() {
        return konzs;
    }

    /**
     * Sets the value of the konzs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKonzs(String value) {
        this.konzs = value;
    }

    /**
     * Gets the value of the ktokd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKtokd() {
        return ktokd;
    }

    /**
     * Sets the value of the ktokd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKtokd(String value) {
        this.ktokd = value;
    }

    /**
     * Gets the value of the kukla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKukla() {
        return kukla;
    }

    /**
     * Sets the value of the kukla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKukla(String value) {
        this.kukla = value;
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
     * Gets the value of the locco property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocco() {
        return locco;
    }

    /**
     * Sets the value of the locco property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocco(String value) {
        this.locco = value;
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
     * Gets the value of the name3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName3() {
        return name3;
    }

    /**
     * Sets the value of the name3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName3(String value) {
        this.name3 = value;
    }

    /**
     * Gets the value of the name4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName4() {
        return name4;
    }

    /**
     * Sets the value of the name4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName4(String value) {
        this.name4 = value;
    }

    /**
     * Gets the value of the niels property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNiels() {
        return niels;
    }

    /**
     * Sets the value of the niels property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNiels(String value) {
        this.niels = value;
    }

    /**
     * Gets the value of the ort02 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrt02() {
        return ort02;
    }

    /**
     * Sets the value of the ort02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrt02(String value) {
        this.ort02 = value;
    }

    /**
     * Gets the value of the pfach property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPfach() {
        return pfach;
    }

    /**
     * Sets the value of the pfach property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPfach(String value) {
        this.pfach = value;
    }

    /**
     * Gets the value of the pstl2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPstl2() {
        return pstl2;
    }

    /**
     * Sets the value of the pstl2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPstl2(String value) {
        this.pstl2 = value;
    }

    /**
     * Gets the value of the counc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCounc() {
        return counc;
    }

    /**
     * Sets the value of the counc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCounc(String value) {
        this.counc = value;
    }

    /**
     * Gets the value of the cityc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityc() {
        return cityc;
    }

    /**
     * Sets the value of the cityc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityc(String value) {
        this.cityc = value;
    }

    /**
     * Gets the value of the rpmkr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRpmkr() {
        return rpmkr;
    }

    /**
     * Sets the value of the rpmkr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRpmkr(String value) {
        this.rpmkr = value;
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
     * Gets the value of the spras property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpras() {
        return spras;
    }

    /**
     * Sets the value of the spras property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpras(String value) {
        this.spras = value;
    }

    /**
     * Gets the value of the stcd1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStcd1() {
        return stcd1;
    }

    /**
     * Sets the value of the stcd1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStcd1(String value) {
        this.stcd1 = value;
    }

    /**
     * Gets the value of the stcd2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStcd2() {
        return stcd2;
    }

    /**
     * Sets the value of the stcd2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStcd2(String value) {
        this.stcd2 = value;
    }

    /**
     * Gets the value of the stkza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStkza() {
        return stkza;
    }

    /**
     * Sets the value of the stkza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStkza(String value) {
        this.stkza = value;
    }

    /**
     * Gets the value of the stkzu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStkzu() {
        return stkzu;
    }

    /**
     * Sets the value of the stkzu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStkzu(String value) {
        this.stkzu = value;
    }

    /**
     * Gets the value of the telbx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelbx() {
        return telbx;
    }

    /**
     * Sets the value of the telbx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelbx(String value) {
        this.telbx = value;
    }

    /**
     * Gets the value of the telf2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelf2() {
        return telf2;
    }

    /**
     * Sets the value of the telf2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelf2(String value) {
        this.telf2 = value;
    }

    /**
     * Gets the value of the teltx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeltx() {
        return teltx;
    }

    /**
     * Sets the value of the teltx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeltx(String value) {
        this.teltx = value;
    }

    /**
     * Gets the value of the telx1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelx1() {
        return telx1;
    }

    /**
     * Sets the value of the telx1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelx1(String value) {
        this.telx1 = value;
    }

    /**
     * Gets the value of the lzone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLzone() {
        return lzone;
    }

    /**
     * Sets the value of the lzone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLzone(String value) {
        this.lzone = value;
    }

    /**
     * Gets the value of the xzemp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXzemp() {
        return xzemp;
    }

    /**
     * Sets the value of the xzemp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXzemp(String value) {
        this.xzemp = value;
    }

    /**
     * Gets the value of the vbund property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVbund() {
        return vbund;
    }

    /**
     * Sets the value of the vbund property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVbund(String value) {
        this.vbund = value;
    }

    /**
     * Gets the value of the stceg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStceg() {
        return stceg;
    }

    /**
     * Sets the value of the stceg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStceg(String value) {
        this.stceg = value;
    }

    /**
     * Gets the value of the dear1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDear1() {
        return dear1;
    }

    /**
     * Sets the value of the dear1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDear1(String value) {
        this.dear1 = value;
    }

    /**
     * Gets the value of the dear2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDear2() {
        return dear2;
    }

    /**
     * Sets the value of the dear2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDear2(String value) {
        this.dear2 = value;
    }

    /**
     * Gets the value of the dear3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDear3() {
        return dear3;
    }

    /**
     * Sets the value of the dear3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDear3(String value) {
        this.dear3 = value;
    }

    /**
     * Gets the value of the dear4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDear4() {
        return dear4;
    }

    /**
     * Sets the value of the dear4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDear4(String value) {
        this.dear4 = value;
    }

    /**
     * Gets the value of the dear5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDear5() {
        return dear5;
    }

    /**
     * Sets the value of the dear5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDear5(String value) {
        this.dear5 = value;
    }

    /**
     * Gets the value of the gform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGform() {
        return gform;
    }

    /**
     * Sets the value of the gform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGform(String value) {
        this.gform = value;
    }

    /**
     * Gets the value of the bran1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBran1() {
        return bran1;
    }

    /**
     * Sets the value of the bran1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBran1(String value) {
        this.bran1 = value;
    }

    /**
     * Gets the value of the bran2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBran2() {
        return bran2;
    }

    /**
     * Sets the value of the bran2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBran2(String value) {
        this.bran2 = value;
    }

    /**
     * Gets the value of the bran3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBran3() {
        return bran3;
    }

    /**
     * Sets the value of the bran3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBran3(String value) {
        this.bran3 = value;
    }

    /**
     * Gets the value of the bran4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBran4() {
        return bran4;
    }

    /**
     * Sets the value of the bran4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBran4(String value) {
        this.bran4 = value;
    }

    /**
     * Gets the value of the bran5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBran5() {
        return bran5;
    }

    /**
     * Sets the value of the bran5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBran5(String value) {
        this.bran5 = value;
    }

    /**
     * Gets the value of the ekont property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEkont() {
        return ekont;
    }

    /**
     * Sets the value of the ekont property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEkont(String value) {
        this.ekont = value;
    }

    /**
     * Gets the value of the umsat property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUmsat() {
        return umsat;
    }

    /**
     * Sets the value of the umsat property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUmsat(BigDecimal value) {
        this.umsat = value;
    }

    /**
     * Gets the value of the umjah property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUmjah() {
        return umjah;
    }

    /**
     * Sets the value of the umjah property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUmjah(String value) {
        this.umjah = value;
    }

    /**
     * Gets the value of the uwaer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUwaer() {
        return uwaer;
    }

    /**
     * Sets the value of the uwaer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUwaer(String value) {
        this.uwaer = value;
    }

    /**
     * Gets the value of the jmzah property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmzah() {
        return jmzah;
    }

    /**
     * Sets the value of the jmzah property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmzah(String value) {
        this.jmzah = value;
    }

    /**
     * Gets the value of the jmjah property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmjah() {
        return jmjah;
    }

    /**
     * Sets the value of the jmjah property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmjah(String value) {
        this.jmjah = value;
    }

    /**
     * Gets the value of the katr1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr1() {
        return katr1;
    }

    /**
     * Sets the value of the katr1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr1(String value) {
        this.katr1 = value;
    }

    /**
     * Gets the value of the katr2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr2() {
        return katr2;
    }

    /**
     * Sets the value of the katr2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr2(String value) {
        this.katr2 = value;
    }

    /**
     * Gets the value of the katr3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr3() {
        return katr3;
    }

    /**
     * Sets the value of the katr3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr3(String value) {
        this.katr3 = value;
    }

    /**
     * Gets the value of the katr4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr4() {
        return katr4;
    }

    /**
     * Sets the value of the katr4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr4(String value) {
        this.katr4 = value;
    }

    /**
     * Gets the value of the katr5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr5() {
        return katr5;
    }

    /**
     * Sets the value of the katr5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr5(String value) {
        this.katr5 = value;
    }

    /**
     * Gets the value of the katr6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr6() {
        return katr6;
    }

    /**
     * Sets the value of the katr6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr6(String value) {
        this.katr6 = value;
    }

    /**
     * Gets the value of the katr7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr7() {
        return katr7;
    }

    /**
     * Sets the value of the katr7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr7(String value) {
        this.katr7 = value;
    }

    /**
     * Gets the value of the katr8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr8() {
        return katr8;
    }

    /**
     * Sets the value of the katr8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr8(String value) {
        this.katr8 = value;
    }

    /**
     * Gets the value of the katr9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr9() {
        return katr9;
    }

    /**
     * Sets the value of the katr9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr9(String value) {
        this.katr9 = value;
    }

    /**
     * Gets the value of the katr10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKatr10() {
        return katr10;
    }

    /**
     * Sets the value of the katr10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKatr10(String value) {
        this.katr10 = value;
    }

    /**
     * Gets the value of the stkzn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStkzn() {
        return stkzn;
    }

    /**
     * Sets the value of the stkzn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStkzn(String value) {
        this.stkzn = value;
    }

    /**
     * Gets the value of the umsa1 property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUmsa1() {
        return umsa1;
    }

    /**
     * Sets the value of the umsa1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUmsa1(BigDecimal value) {
        this.umsa1 = value;
    }

    /**
     * Gets the value of the txjcd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxjcd() {
        return txjcd;
    }

    /**
     * Sets the value of the txjcd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxjcd(String value) {
        this.txjcd = value;
    }

    /**
     * Gets the value of the periv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriv() {
        return periv;
    }

    /**
     * Sets the value of the periv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriv(String value) {
        this.periv = value;
    }

    /**
     * Gets the value of the abrvw property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbrvw() {
        return abrvw;
    }

    /**
     * Sets the value of the abrvw property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbrvw(String value) {
        this.abrvw = value;
    }

    /**
     * Gets the value of the inspbydebi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspbydebi() {
        return inspbydebi;
    }

    /**
     * Sets the value of the inspbydebi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspbydebi(String value) {
        this.inspbydebi = value;
    }

    /**
     * Gets the value of the inspatdebi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInspatdebi() {
        return inspatdebi;
    }

    /**
     * Sets the value of the inspatdebi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInspatdebi(String value) {
        this.inspatdebi = value;
    }

    /**
     * Gets the value of the ktocd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKtocd() {
        return ktocd;
    }

    /**
     * Sets the value of the ktocd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKtocd(String value) {
        this.ktocd = value;
    }

    /**
     * Gets the value of the pfort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPfort() {
        return pfort;
    }

    /**
     * Sets the value of the pfort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPfort(String value) {
        this.pfort = value;
    }

    /**
     * Gets the value of the werks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWerks() {
        return werks;
    }

    /**
     * Sets the value of the werks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWerks(String value) {
        this.werks = value;
    }

    /**
     * Gets the value of the dtams property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDtams() {
        return dtams;
    }

    /**
     * Sets the value of the dtams property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDtams(String value) {
        this.dtams = value;
    }

    /**
     * Gets the value of the dtaws property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDtaws() {
        return dtaws;
    }

    /**
     * Sets the value of the dtaws property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDtaws(String value) {
        this.dtaws = value;
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
     * Gets the value of the hzuor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHzuor() {
        return hzuor;
    }

    /**
     * Sets the value of the hzuor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHzuor(String value) {
        this.hzuor = value;
    }

    /**
     * Gets the value of the sperz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSperz() {
        return sperz;
    }

    /**
     * Sets the value of the sperz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSperz(String value) {
        this.sperz = value;
    }

    /**
     * Gets the value of the etikg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtikg() {
        return etikg;
    }

    /**
     * Sets the value of the etikg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtikg(String value) {
        this.etikg = value;
    }

    /**
     * Gets the value of the civve property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivve() {
        return civve;
    }

    /**
     * Sets the value of the civve property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivve(String value) {
        this.civve = value;
    }

    /**
     * Gets the value of the milve property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMilve() {
        return milve;
    }

    /**
     * Sets the value of the milve property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMilve(String value) {
        this.milve = value;
    }

    /**
     * Gets the value of the kdkg1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKdkg1() {
        return kdkg1;
    }

    /**
     * Sets the value of the kdkg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKdkg1(String value) {
        this.kdkg1 = value;
    }

    /**
     * Gets the value of the kdkg2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKdkg2() {
        return kdkg2;
    }

    /**
     * Sets the value of the kdkg2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKdkg2(String value) {
        this.kdkg2 = value;
    }

    /**
     * Gets the value of the kdkg3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKdkg3() {
        return kdkg3;
    }

    /**
     * Sets the value of the kdkg3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKdkg3(String value) {
        this.kdkg3 = value;
    }

    /**
     * Gets the value of the kdkg4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKdkg4() {
        return kdkg4;
    }

    /**
     * Sets the value of the kdkg4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKdkg4(String value) {
        this.kdkg4 = value;
    }

    /**
     * Gets the value of the kdkg5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKdkg5() {
        return kdkg5;
    }

    /**
     * Sets the value of the kdkg5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKdkg5(String value) {
        this.kdkg5 = value;
    }

    /**
     * Gets the value of the xknza property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXknza() {
        return xknza;
    }

    /**
     * Sets the value of the xknza property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXknza(String value) {
        this.xknza = value;
    }

    /**
     * Gets the value of the fityp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFityp() {
        return fityp;
    }

    /**
     * Sets the value of the fityp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFityp(String value) {
        this.fityp = value;
    }

    /**
     * Gets the value of the stcdt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStcdt() {
        return stcdt;
    }

    /**
     * Sets the value of the stcdt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStcdt(String value) {
        this.stcdt = value;
    }

    /**
     * Gets the value of the stcd3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStcd3() {
        return stcd3;
    }

    /**
     * Sets the value of the stcd3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStcd3(String value) {
        this.stcd3 = value;
    }

    /**
     * Gets the value of the stcd4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStcd4() {
        return stcd4;
    }

    /**
     * Sets the value of the stcd4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStcd4(String value) {
        this.stcd4 = value;
    }

    /**
     * Gets the value of the stcd5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStcd5() {
        return stcd5;
    }

    /**
     * Sets the value of the stcd5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStcd5(String value) {
        this.stcd5 = value;
    }

    /**
     * Gets the value of the xicms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXicms() {
        return xicms;
    }

    /**
     * Sets the value of the xicms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXicms(String value) {
        this.xicms = value;
    }

    /**
     * Gets the value of the xxipi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXxipi() {
        return xxipi;
    }

    /**
     * Sets the value of the xxipi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXxipi(String value) {
        this.xxipi = value;
    }

    /**
     * Gets the value of the xsubt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXsubt() {
        return xsubt;
    }

    /**
     * Sets the value of the xsubt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXsubt(String value) {
        this.xsubt = value;
    }

    /**
     * Gets the value of the cfopc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCfopc() {
        return cfopc;
    }

    /**
     * Sets the value of the cfopc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCfopc(String value) {
        this.cfopc = value;
    }

    /**
     * Gets the value of the txlw1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxlw1() {
        return txlw1;
    }

    /**
     * Sets the value of the txlw1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxlw1(String value) {
        this.txlw1 = value;
    }

    /**
     * Gets the value of the txlw2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxlw2() {
        return txlw2;
    }

    /**
     * Sets the value of the txlw2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxlw2(String value) {
        this.txlw2 = value;
    }

    /**
     * Gets the value of the ccc01 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcc01() {
        return ccc01;
    }

    /**
     * Sets the value of the ccc01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcc01(String value) {
        this.ccc01 = value;
    }

    /**
     * Gets the value of the ccc02 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcc02() {
        return ccc02;
    }

    /**
     * Sets the value of the ccc02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcc02(String value) {
        this.ccc02 = value;
    }

    /**
     * Gets the value of the ccc03 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcc03() {
        return ccc03;
    }

    /**
     * Sets the value of the ccc03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcc03(String value) {
        this.ccc03 = value;
    }

    /**
     * Gets the value of the ccc04 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcc04() {
        return ccc04;
    }

    /**
     * Sets the value of the ccc04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcc04(String value) {
        this.ccc04 = value;
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
     * Gets the value of the knurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnurl() {
        return knurl;
    }

    /**
     * Sets the value of the knurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnurl(String value) {
        this.knurl = value;
    }

    /**
     * Gets the value of the j1Kfrepre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJ1Kfrepre() {
        return j1Kfrepre;
    }

    /**
     * Sets the value of the j1Kfrepre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJ1Kfrepre(String value) {
        this.j1Kfrepre = value;
    }

    /**
     * Gets the value of the j1Kftbus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJ1Kftbus() {
        return j1Kftbus;
    }

    /**
     * Sets the value of the j1Kftbus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJ1Kftbus(String value) {
        this.j1Kftbus = value;
    }

    /**
     * Gets the value of the j1Kftind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJ1Kftind() {
        return j1Kftind;
    }

    /**
     * Sets the value of the j1Kftind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJ1Kftind(String value) {
        this.j1Kftind = value;
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
     * Gets the value of the dear6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDear6() {
        return dear6;
    }

    /**
     * Sets the value of the dear6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDear6(String value) {
        this.dear6 = value;
    }

    /**
     * Gets the value of the vsoRPalhgt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVsoRPalhgt() {
        return vsoRPalhgt;
    }

    /**
     * Sets the value of the vsoRPalhgt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVsoRPalhgt(BigDecimal value) {
        this.vsoRPalhgt = value;
    }

    /**
     * Gets the value of the vsoRPalUl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoRPalUl() {
        return vsoRPalUl;
    }

    /**
     * Sets the value of the vsoRPalUl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoRPalUl(String value) {
        this.vsoRPalUl = value;
    }

    /**
     * Gets the value of the vsoRPkMat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoRPkMat() {
        return vsoRPkMat;
    }

    /**
     * Sets the value of the vsoRPkMat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoRPkMat(String value) {
        this.vsoRPkMat = value;
    }

    /**
     * Gets the value of the vsoRMatpal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoRMatpal() {
        return vsoRMatpal;
    }

    /**
     * Sets the value of the vsoRMatpal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoRMatpal(String value) {
        this.vsoRMatpal = value;
    }

    /**
     * Gets the value of the vsoRINoLyr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoRINoLyr() {
        return vsoRINoLyr;
    }

    /**
     * Sets the value of the vsoRINoLyr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoRINoLyr(String value) {
        this.vsoRINoLyr = value;
    }

    /**
     * Gets the value of the vsoROneMat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoROneMat() {
        return vsoROneMat;
    }

    /**
     * Sets the value of the vsoROneMat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoROneMat(String value) {
        this.vsoROneMat = value;
    }

    /**
     * Gets the value of the vsoROneSort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoROneSort() {
        return vsoROneSort;
    }

    /**
     * Sets the value of the vsoROneSort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoROneSort(String value) {
        this.vsoROneSort = value;
    }

    /**
     * Gets the value of the vsoRUldSide property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoRUldSide() {
        return vsoRUldSide;
    }

    /**
     * Sets the value of the vsoRUldSide property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoRUldSide(String value) {
        this.vsoRUldSide = value;
    }

    /**
     * Gets the value of the vsoRLoadPref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoRLoadPref() {
        return vsoRLoadPref;
    }

    /**
     * Sets the value of the vsoRLoadPref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoRLoadPref(String value) {
        this.vsoRLoadPref = value;
    }

    /**
     * Gets the value of the vsoRDpoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVsoRDpoint() {
        return vsoRDpoint;
    }

    /**
     * Sets the value of the vsoRDpoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVsoRDpoint(String value) {
        this.vsoRDpoint = value;
    }

    /**
     * Gets the value of the alc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlc() {
        return alc;
    }

    /**
     * Sets the value of the alc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlc(String value) {
        this.alc = value;
    }

    /**
     * Gets the value of the pmtOffice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPmtOffice() {
        return pmtOffice;
    }

    /**
     * Sets the value of the pmtOffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPmtOffice(String value) {
        this.pmtOffice = value;
    }

    /**
     * Gets the value of the psofg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsofg() {
        return psofg;
    }

    /**
     * Sets the value of the psofg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsofg(String value) {
        this.psofg = value;
    }

    /**
     * Gets the value of the psois property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsois() {
        return psois;
    }

    /**
     * Sets the value of the psois property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsois(String value) {
        this.psois = value;
    }

    /**
     * Gets the value of the pson1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPson1() {
        return pson1;
    }

    /**
     * Sets the value of the pson1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPson1(String value) {
        this.pson1 = value;
    }

    /**
     * Gets the value of the pson2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPson2() {
        return pson2;
    }

    /**
     * Sets the value of the pson2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPson2(String value) {
        this.pson2 = value;
    }

    /**
     * Gets the value of the pson3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPson3() {
        return pson3;
    }

    /**
     * Sets the value of the pson3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPson3(String value) {
        this.pson3 = value;
    }

    /**
     * Gets the value of the psovn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsovn() {
        return psovn;
    }

    /**
     * Sets the value of the psovn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsovn(String value) {
        this.psovn = value;
    }

    /**
     * Gets the value of the psotl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsotl() {
        return psotl;
    }

    /**
     * Sets the value of the psotl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsotl(String value) {
        this.psotl = value;
    }

    /**
     * Gets the value of the psohs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsohs() {
        return psohs;
    }

    /**
     * Sets the value of the psohs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsohs(String value) {
        this.psohs = value;
    }

    /**
     * Gets the value of the psost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsost() {
        return psost;
    }

    /**
     * Sets the value of the psost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsost(String value) {
        this.psost = value;
    }

    /**
     * Gets the value of the psoo1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsoo1() {
        return psoo1;
    }

    /**
     * Sets the value of the psoo1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsoo1(String value) {
        this.psoo1 = value;
    }

    /**
     * Gets the value of the psoo2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsoo2() {
        return psoo2;
    }

    /**
     * Sets the value of the psoo2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsoo2(String value) {
        this.psoo2 = value;
    }

    /**
     * Gets the value of the psoo3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsoo3() {
        return psoo3;
    }

    /**
     * Sets the value of the psoo3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsoo3(String value) {
        this.psoo3 = value;
    }

    /**
     * Gets the value of the psoo4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsoo4() {
        return psoo4;
    }

    /**
     * Sets the value of the psoo4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsoo4(String value) {
        this.psoo4 = value;
    }

    /**
     * Gets the value of the psoo5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsoo5() {
        return psoo5;
    }

    /**
     * Sets the value of the psoo5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsoo5(String value) {
        this.psoo5 = value;
    }

}
