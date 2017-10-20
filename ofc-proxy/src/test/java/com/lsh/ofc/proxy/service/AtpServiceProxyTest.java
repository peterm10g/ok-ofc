package com.lsh.ofc.proxy.service;

import com.lsh.ofc.proxy.common.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangdong on 16/8/31.
 */
public class AtpServiceProxyTest extends BaseSpringTest {

    @Autowired
    private AtpServiceProxy proxy;

    @Test
    public void test1() throws Exception {
        Object info = proxy.querySkuDcQtyMap(937359924193108012L);
        System.out.println(info);
    }
}
