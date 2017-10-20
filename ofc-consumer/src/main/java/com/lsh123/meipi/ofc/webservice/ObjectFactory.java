
package com.lsh123.meipi.ofc.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lsh123.meipi.ofc.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CallRet_QNAME = new QName("http://webservice.ofc.meipi.lsh123.com/", "CallRet");
    private final static QName _OutboundOrderHead_QNAME = new QName("http://webservice.ofc.meipi.lsh123.com/", "OutboundOrderHead");
    private final static QName _PutOutboundOrder_QNAME = new QName("http://webservice.ofc.meipi.lsh123.com/", "putOutboundOrder");
    private final static QName _OutboundOrderItem_QNAME = new QName("http://webservice.ofc.meipi.lsh123.com/", "OutboundOrderItem");
    private final static QName _PutOutboundOrderResponse_QNAME = new QName("http://webservice.ofc.meipi.lsh123.com/", "putOutboundOrderResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lsh123.meipi.ofc.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CallRet }
     * 
     */
    public CallRet createCallRet() {
        return new CallRet();
    }

    /**
     * Create an instance of {@link OutboundOrderHead }
     * 
     */
    public OutboundOrderHead createOutboundOrderHead() {
        return new OutboundOrderHead();
    }

    /**
     * Create an instance of {@link PutOutboundOrder }
     * 
     */
    public PutOutboundOrder createPutOutboundOrder() {
        return new PutOutboundOrder();
    }

    /**
     * Create an instance of {@link PutOutboundOrderResponse }
     * 
     */
    public PutOutboundOrderResponse createPutOutboundOrderResponse() {
        return new PutOutboundOrderResponse();
    }

    /**
     * Create an instance of {@link OutboundOrderItem }
     * 
     */
    public OutboundOrderItem createOutboundOrderItem() {
        return new OutboundOrderItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallRet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ofc.meipi.lsh123.com/", name = "CallRet")
    public JAXBElement<CallRet> createCallRet(CallRet value) {
        return new JAXBElement<CallRet>(_CallRet_QNAME, CallRet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutboundOrderHead }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ofc.meipi.lsh123.com/", name = "OutboundOrderHead")
    public JAXBElement<OutboundOrderHead> createOutboundOrderHead(OutboundOrderHead value) {
        return new JAXBElement<OutboundOrderHead>(_OutboundOrderHead_QNAME, OutboundOrderHead.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutOutboundOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ofc.meipi.lsh123.com/", name = "putOutboundOrder")
    public JAXBElement<PutOutboundOrder> createPutOutboundOrder(PutOutboundOrder value) {
        return new JAXBElement<PutOutboundOrder>(_PutOutboundOrder_QNAME, PutOutboundOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OutboundOrderItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ofc.meipi.lsh123.com/", name = "OutboundOrderItem")
    public JAXBElement<OutboundOrderItem> createOutboundOrderItem(OutboundOrderItem value) {
        return new JAXBElement<OutboundOrderItem>(_OutboundOrderItem_QNAME, OutboundOrderItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PutOutboundOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.ofc.meipi.lsh123.com/", name = "putOutboundOrderResponse")
    public JAXBElement<PutOutboundOrderResponse> createPutOutboundOrderResponse(PutOutboundOrderResponse value) {
        return new JAXBElement<PutOutboundOrderResponse>(_PutOutboundOrderResponse_QNAME, PutOutboundOrderResponse.class, null, value);
    }

}
