package com.lsh.ofc.proxy.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huangdong on 16/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-test.xml")
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public abstract class BaseSpringTest extends BaseTest {
}