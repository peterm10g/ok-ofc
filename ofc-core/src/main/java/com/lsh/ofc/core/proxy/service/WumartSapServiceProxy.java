package com.lsh.ofc.core.proxy.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.proxy.handler.WumartSap1Handler;
import com.lsh.ofc.core.proxy.handler.WumartSap2Handler;
import com.lsh.ofc.core.proxy.handler.WumartSapHandler;
import com.lsh.ofc.proxy.model.CreateSoReqHead;
import com.lsh.ofc.proxy.model.CreateSoRetHead;
import com.lsh.ofc.proxy.model.MeipiCustomer;
import com.lsh.ofc.proxy.model.ObdHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 物美SAP服务代理
 * Created by huangdong on 16/8/28.
 */
@Service
public class WumartSapServiceProxy {

    @Resource(name = "wumartSap1Handler")
    private WumartSap1Handler wumartSap1Handler;

    @Resource(name = "wumartSap2Handler")
    private WumartSap2Handler wumartSap2Handler;

    @Autowired
    private WumartBasicService wumartBasicService;

    public String addMeipiCustomer(MeipiCustomer model) throws BusinessException {
        this.wumartBasicService.validAddress(model.getProvince(), model.getCity(), model.getDistrict(), model.getAddress());
        model.setSoUserCode(null);
        return this.getHandler(model.getRegionCode()).commitMeipiCustomer(model);
    }

    public String modMeipiCustomer(MeipiCustomer model, String meipiCustomerCode) throws BusinessException {
        if (!StringUtils.hasText(meipiCustomerCode)) {
            throw new BusinessException(CommonResult.ERROR, "美批客户号为空，无法修改！");
        }
        model.setSoUserCode(meipiCustomerCode);
        this.wumartBasicService.validAddress(model.getProvince(), model.getCity(), model.getDistrict(), model.getAddress());
        return this.getHandler(model.getRegionCode()).commitMeipiCustomer(model);
    }

    public CreateSoRetHead createMeipiSo(CreateSoReqHead model) throws BusinessException {
        model.setSoCode(null);
        return this.getHandler(model.getRegionCode()).createMeipiSo(model, true);
    }

    public CreateSoRetHead createMeipiRo(CreateSoReqHead model, String soCode, boolean first) throws BusinessException {
        if (!StringUtils.hasText(soCode)) {
            throw new BusinessException(CommonResult.ERROR, "SO单号为空，无法创建返仓！");
        }
        model.setSoCode(soCode);
        return this.getHandler(model.getRegionCode()).createMeipiSo(model, first);
    }

    public ObdHead queryObdStatus4Ro(String soCode, Integer regionCode) throws BusinessException {
        if (!StringUtils.hasText(soCode)) {
            throw new BusinessException(CommonResult.ERROR, "SO单号为空，无法查询OBD状态！");
        }
        return this.getHandler(regionCode).queryObdStatus(soCode, regionCode, true);
    }

    private WumartSapHandler getHandler(Integer regionCode) throws BusinessException {
        switch (regionCode.intValue()) {
            case 1000: //北京
            case 1001: //天津
                return this.wumartSap1Handler;
            case 1002: //杭州
                return this.wumartSap2Handler;
            default:
                throw new BusinessException(CommonResult.ERROR, "地域编号[" + regionCode + "]暂不支持！");
        }
    }
}
