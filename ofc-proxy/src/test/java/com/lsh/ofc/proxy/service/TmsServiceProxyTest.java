package com.lsh.ofc.proxy.service;

import com.lsh.ofc.proxy.common.BaseSpringTest;
import com.lsh.ofc.proxy.model.ReceiptOrderHead;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangdong on 16/8/31.
 */
public class TmsServiceProxyTest extends BaseSpringTest {

    @Autowired
    private TmsServiceProxy proxy;

    @Test
    public void test1() throws Exception {
        ReceiptOrderHead info = proxy.getReceiptOrder(1L);
        System.out.println(info);
    }
}
