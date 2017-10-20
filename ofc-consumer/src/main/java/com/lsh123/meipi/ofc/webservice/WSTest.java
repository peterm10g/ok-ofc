package com.lsh123.meipi.ofc.webservice;

import java.util.Arrays;

/**
 * Created by huangdong on 16/9/4.
 */
public class WSTest {
    public static void main(String[] args) {
        ObjectFactory factory = new ObjectFactory();
        OutboundOrderHead head = factory.createOutboundOrderHead();
        head.setAmount("1212");
        head.setBoxNum(5);
        OFCWebService service = new OFCWebServiceService().getOFCWebServicePort();
        service.putOutboundOrder(head, Arrays.asList(factory.createOutboundOrderItem()));
    }
}
