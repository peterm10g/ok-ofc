package com.lsh.ofc.proxy.service;

import com.lsh.ofc.proxy.common.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * Created by huangdong on 16/8/31.
 */
public class OmsServiceProxyTest extends BaseSpringTest {

    @Autowired
    private OmsServiceProxy proxy;

    @Test
    public void test1() throws Exception {
//      Object obj =  proxy.getOrderByCode(6183485176195059712L);
        Object info = proxy.updateOrderStatus(937359924193108012L, 1, 1, Arrays.asList("1"));
        System.out.println(info);
    }
}
