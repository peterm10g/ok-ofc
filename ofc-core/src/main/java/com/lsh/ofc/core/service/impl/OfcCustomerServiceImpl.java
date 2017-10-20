package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.dao.OfcCustomerDAO;
import com.lsh.ofc.core.entity.MeipiCustomer;
import com.lsh.ofc.core.entity.OfcCustomer;
import com.lsh.ofc.core.entity.OfcTask;
import com.lsh.ofc.core.enums.OfcTaskStatus;
import com.lsh.ofc.core.enums.OfcTaskType;
import com.lsh.ofc.core.enums.Valid;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.handler.MeipiCustomerHandler;
import com.lsh.ofc.core.service.MeipiCustomerService;
import com.lsh.ofc.core.service.OfcCustomerService;
import com.lsh.ofc.core.service.OfcTaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class OfcCustomerServiceImpl implements OfcCustomerService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcCustomerDAO ofcCustomerDAO;

    @Autowired
    private OfcTaskService ofcTaskService;

    @Autowired
    private MeipiCustomerHandler meipiCustomerHandler;

    @Override
    public OfcCustomer getCustomer(Long custCode) throws BusinessException {
        OfcCustomer filter = new OfcCustomer();
        filter.setCustCode(custCode);
        return this.ofcCustomerDAO.findOne(filter);
    }

    @Transactional
    @Override
    public OfcCustomer updateCustomer(OfcCustomer param) throws BusinessException {
        Integer regionCode = param.getRegionCode();
        Long custCode = param.getCustCode();
        String custName = param.getCustName();
        String contactName = param.getContactName();
        String contactPhone = param.getContactPhone();
        String province = param.getProvince();
        String city = param.getCity();
        String district = param.getDistrict();
        String address = param.getAddress();
        String location = param.getLocation();
        String ext = param.getExt();
        if (regionCode == null || custCode == null || StringUtils.isEmpty(custName)
                || StringUtils.isEmpty(contactName) || StringUtils.isEmpty(contactPhone)
                || StringUtils.isEmpty(province) || StringUtils.isEmpty(city) || StringUtils.isEmpty(district)
                || StringUtils.isEmpty(address) || StringUtils.isEmpty(location)) {
            throw EC.ERROR.exception("请求获取美批客户参数错误！" + JSON.toJSONString(param));
        }

        int length = address.length();
        int diff = province.length() + city.length() + district.length() + length + 3 - 35;
        if (diff > 0) {
            logger.warn("【" + custCode + "】截取客户地址开始... address=" + address);
            address = address.substring(diff);
            logger.warn("【" + custCode + "】截取客户地址结束... address=" + address);
        }

        //校验大车限行
        if (StringUtils.isEmpty(ext) || JSON.parseObject(ext).getInteger(Constants.USER_ADDRESS_TRANS_LIMIT) == null) {
            throw EC.ERROR.exception("请求获取美批客户参数错误！" + JSON.toJSONString(param));
        }

        OfcCustomer customer = this.getCustomer(custCode);
        boolean update = false;
        if (customer == null) {
            MeipiCustomer mpCustomer = this.meipiCustomerHandler.popMpCust(regionCode);
            customer = new OfcCustomer();
            customer.setCustCode(custCode);
            customer.setCustName(custName);
            customer.setRegionCode(regionCode);
            customer.setContactName(contactName);
            customer.setContactPhone(contactPhone);
            customer.setProvince(province);
            customer.setCity(city);
            customer.setDistrict(district);
            customer.setAddress(address);
            customer.setLocation(location);
            customer.setMpCustCode(mpCustomer.getCustCode());
            customer.setMpCustZone(mpCustomer.getCustZone());
            customer.setExt(ext);
            customer.setCreateTime(mpCustomer.getCreateTime());
            customer.setValid(Valid.enable.getValue());
            this.ofcCustomerDAO.insert(customer);
            //添加客户信息刷新任务
            this.addRefreshTask(customer);
        } else {
            if (!regionCode.equals(customer.getRegionCode())) {
                throw EC.ERROR.exception("OFC客户地域不匹配！" + customer.getRegionCode() + " != " + regionCode);
            }
            if (!custName.equals(customer.getCustName())) {
                logger.info("【" + custCode + "】客户信息已变更。。。custName(" + customer.getCustName() + ", " + custName + ")");
                customer.setCustName(custName);
                update = true;
            }
            if (!contactName.equals(customer.getContactName())) {
                logger.info("【" + custCode + "】客户信息已变更。。。contactName(" + customer.getContactName() + ", " + contactName + ")");
                customer.setContactName(contactName);
                update = true;
            }
            if (!contactPhone.equals(customer.getContactPhone())) {
                logger.info("【" + custCode + "】客户信息已变更。。。contactPhone(" + customer.getContactPhone() + ", " + contactPhone + ")");
                customer.setContactPhone(contactPhone);
                update = true;
            }
            if (!province.equals(customer.getProvince())) {
                logger.info("【" + custCode + "】客户信息已变更。。。province(" + customer.getProvince() + ", " + province + ")");
                customer.setProvince(province);
                update = true;
            }
            if (!city.equals(customer.getCity())) {
                logger.info("【" + custCode + "】客户信息已变更。。。city(" + customer.getCity() + ", " + city + ")");
                customer.setCity(city);
                update = true;
            }
            if (!district.equals(customer.getDistrict())) {
                logger.info("【" + custCode + "】客户信息已变更。。。district(" + customer.getDistrict() + ", " + district + ")");
                customer.setDistrict(district);
                update = true;
            }
            if (!address.equals(customer.getAddress())) {
                logger.info("【" + custCode + "】客户信息已变更。。。address(" + customer.getAddress() + ", " + address + ")");
                customer.setAddress(address);
                update = true;
            }
            if (update) {
                //添加客户信息刷新任务
                this.addRefreshTask(customer);
            }
            if (!location.equals(customer.getLocation())) {
                logger.info("【" + custCode + "】客户信息已变更。。。location(" + customer.getLocation() + ", " + location + ")");
                customer.setLocation(location);
                update = true;
            }
            if (!ext.equals(customer.getExt())) {
                logger.info("【" + custCode + "】客户信息已变更。。。ext(" + customer.getExt() + ", " + ext + ")");
                JSONObject obj = JSON.parseObject(customer.getExt());
                obj.putAll(JSON.parseObject(ext));
                customer.setExt(obj.toJSONString());
                update = true;
            }
            if (update) {
                logger.info("更新OFC用户！" + JSON.toJSONString(customer));
                this.ofcCustomerDAO.update(customer);
            }
        }
        return customer;
    }

    @Override
    public void refreshCustomer(Long custCode) throws BusinessException {
        OfcCustomer customer = this.getCustomer(custCode);
        this.modMpCust(customer);
    }

    /**
     * 添加客户信息刷新任务
     *
     * @param customer
     * @throws BusinessException
     */
    private void addRefreshTask(OfcCustomer customer) throws BusinessException {
        Long custCode = customer.getCustCode();
        OfcTask task = new OfcTask();
        task.setRefId(custCode);
        task.setType(OfcTaskType.CUSTOMER_REFRESH.getValue());
        task.setStatus(OfcTaskStatus.NEW.getValue());
        task.setContent(custCode.toString());
        this.ofcTaskService.addTask(task);
    }

    private void modMpCust(OfcCustomer customer) throws BusinessException {
        //更新美批客户信息
        MeipiCustomer mpCustomer = new MeipiCustomer();
        mpCustomer.setRegionCode(customer.getRegionCode());
        mpCustomer.setCustCode(customer.getMpCustCode());
        mpCustomer.setCustZone(customer.getMpCustZone());
        mpCustomer.setCustName(customer.getCustName());
        mpCustomer.setContactName(customer.getContactName());
        mpCustomer.setContactPhone(customer.getContactPhone());
        mpCustomer.setProvince(customer.getProvince());
        mpCustomer.setCity(customer.getCity());
        mpCustomer.setDistrict(customer.getDistrict());
        mpCustomer.setAddress(customer.getAddress());
        this.meipiCustomerHandler.modMpCust(mpCustomer);
    }
}
