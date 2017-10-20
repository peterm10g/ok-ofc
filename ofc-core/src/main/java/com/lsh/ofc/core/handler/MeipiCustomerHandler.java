package com.lsh.ofc.core.handler;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.MeipiCustomer;
import com.lsh.ofc.core.service.MeipiCustomerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by panxudong on 17/3/22.
 */
@Service
public class MeipiCustomerHandler {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Resource(name = "meipiCustomerSapHandler")
    private MeipiCustomerService meipiCustomerSapService;

    @Resource(name = "meipiCustomerOfcHandler")
    private MeipiCustomerService meipiCustomerOfcService;

    public boolean isWumartOfc() {
        return true;
    }

    private MeipiCustomerService getHandler() {
        if (isWumartOfc()) {
            return meipiCustomerOfcService;
        } else {
            return meipiCustomerSapService;
        }
    }

    public String addMpCust(Integer regionCode) throws BusinessException {
        return this.getHandler().addMpCust(regionCode);
    }

    public String modMpCust(MeipiCustomer mpCustomer) throws BusinessException {
        return this.getHandler().modMpCust(mpCustomer);
    }

    public MeipiCustomer popMpCust(Integer regionCode) throws BusinessException {
        return this.getHandler().popMpCust(regionCode);
    }

}
