package com.lsh.ofc.core.handler;

import com.alibaba.fastjson.JSON;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.MeipiCustomer;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.service.impl.AbstractMeipiCustomerService;
import com.lsh.ofc.core.proxy.service.WumartOfcServiceProxy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("meipiCustomerOfcHandler")
public class MeipiCustomerOfcHandler extends AbstractMeipiCustomerService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private WumartOfcServiceProxy wumartOfcServiceProxy;

    /**
     * 提交美批用户信息
     *
     * @param customer
     * @return
     * @throws BusinessException
     */
    @Override
    protected String commitMeipiCustomer(MeipiCustomer customer) throws BusinessException {
        com.lsh.ofc.proxy.model.MeipiCustomer user = this.getMeipiCustomer(customer);
        String meipiCustomerCode = customer.getCustCode();
        try {
            String ret;
            logger.info("提交美批用户信息开始... params=" + JSON.toJSONString(user));
            if (meipiCustomerCode == null) {
                ret = this.wumartOfcServiceProxy.addMeipiCustomer(user);
            } else {
                ret = this.wumartOfcServiceProxy.modMeipiCustomer(user);
            }
            logger.info("提交美批用户信息完成... ret=" + ret + "params=" + JSON.toJSONString(user));
            return ret;
        } catch (Throwable e) {
            logger.error("提交美批用户信息异常... params=" + JSON.toJSONString(user) + "\n" + e.getMessage(), e);
            throw new BusinessException(EC.ERROR.getCode(), e.getMessage(), e);
        }
    }

}
