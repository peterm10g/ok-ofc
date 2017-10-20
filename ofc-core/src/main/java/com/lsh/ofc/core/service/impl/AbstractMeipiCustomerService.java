package com.lsh.ofc.core.service.impl;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.dao.MeipiCustomerDAO;
import com.lsh.ofc.core.entity.MeipiCustomer;
import com.lsh.ofc.core.enums.Region;
import com.lsh.ofc.core.enums.Valid;
import com.lsh.ofc.core.exception.EC;
import com.lsh.ofc.core.proxy.service.WumartBasicService;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.core.service.MeipiCustomerService;
import com.lsh.ofc.proxy.context.WumartBasicContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public abstract class AbstractMeipiCustomerService implements MeipiCustomerService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MeipiCustomerDAO meipiCustomerDAO;

    @Autowired
    private WumartBasicService wumartBasicService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Transactional
    @Override
    public String addMpCust(Integer regionCode) throws BusinessException {
        logger.info("新增美批客户开始... 地域编号=" + regionCode);
        long ts = System.currentTimeMillis();
        MeipiCustomer mpCustomer = new MeipiCustomer();
        mpCustomer.setCustName("超市" + ts);
        mpCustomer.setRegionCode(regionCode);
        mpCustomer.setContactName("用户" + ts);
        mpCustomer.setContactPhone("13800000000");
        if (Region.Beijing.getCode().equals(regionCode)) {
            mpCustomer.setProvince("北京市");
            mpCustomer.setCity("市辖区");
            mpCustomer.setDistrict("海淀区");
        } else if (Region.Tianjin.getCode().equals(regionCode)) {
            mpCustomer.setProvince("天津市");
            mpCustomer.setCity("市辖区");
            mpCustomer.setDistrict("河西区");
        } else if (Region.Hangzhou.getCode().equals(regionCode)) {
            mpCustomer.setProvince("浙江省");
            mpCustomer.setCity("杭州市");
            mpCustomer.setDistrict("西湖区");
        } else if (Region.Beijingcg.getCode().equals(regionCode)) {
            mpCustomer.setProvince("北京市");
            mpCustomer.setCity("市辖区");
            mpCustomer.setDistrict("海淀区");
        } else if (Region.BeijingKA.getCode().equals(regionCode)) {
            mpCustomer.setProvince("北京市");
            mpCustomer.setCity("市辖区");
            mpCustomer.setDistrict("海淀区");
        }
        mpCustomer.setAddress("占位地址待修改");
        mpCustomer.setCustZone(this.wumartBasicService.tansRegion2WumartZone(WumartBasicContext.buildContext(regionCode)));
        String custCode = this.commitMeipiCustomer(mpCustomer);
        mpCustomer.setCustCode(custCode);
        mpCustomer.setValid(Valid.disable.getValue());
        this.meipiCustomerDAO.insert(mpCustomer);
        this.redisTemplate.rpush(this.getKey4List(regionCode), custCode);
        logger.info("新增美批客户完成... 地域编号=" + regionCode + "。返回美批客户号=" + custCode);
        return custCode;
    }

    @Transactional
    @Override
    public String modMpCust(MeipiCustomer customer) throws BusinessException {
        return this.commitMeipiCustomer(customer);
    }

    @Transactional
    @Override
    public MeipiCustomer popMpCust(Integer regionCode) throws BusinessException {
        for (int i = 0; i < 5; i++) {
            String mpCustCode = this.redisTemplate.lpop(this.getKey4List(regionCode));
            if (StringUtils.isEmpty(mpCustCode)) {
                logger.warn("美批客户队列为空，重建队列！地域编号=" + regionCode);
                this.rebuildMpCustList(regionCode, false);
                continue;
            }
            MeipiCustomer filter = new MeipiCustomer();
            filter.setRegionCode(regionCode);
            filter.setCustCode(mpCustCode);
            filter.setValid(Valid.disable.getValue());
            MeipiCustomer mpCustomer = this.meipiCustomerDAO.findOne(filter);
            if (mpCustomer == null) {
                logger.warn("美批客户信息已启用！美批客户号=" + mpCustCode);
                continue;
            } else {
                int ret = this.meipiCustomerDAO.delete(mpCustomer.getId());
                if (ret <= 0) {
                    continue;
                }
                return mpCustomer;
            }
        }
        throw EC.ERROR.exception("获取美批客户信息失败！地域编号=" + regionCode);
    }

    /**
     * 重建美批客户队列（未启用美批客户）
     *
     * @param regionCode
     * @param force
     * @return
     * @throws BusinessException
     */
    private int rebuildMpCustList(Integer regionCode, boolean force) throws BusinessException {
        String lockKey = this.getKey4Lock(regionCode);
        if (!this.redisTemplate.lock(lockKey, 30)) {
            logger.warn("中止重建美批用户队列 by locked！地域编号=" + regionCode);
            return -1;
        }
        try {
            String listKey = this.getKey4List(regionCode);
            if (!force) {
                long size = this.redisTemplate.lsize(listKey);
                if (size > 0) {
                    logger.warn("中止重建美批用户队列 size(" + size + ") > 0！地域编号=" + regionCode);
                    return 0;
                }
            }
            logger.info("重建美批用户队列开始！地域编号=" + regionCode);
            MeipiCustomer filter = new MeipiCustomer();
            filter.setRegionCode(regionCode);
            filter.setValid(Valid.disable.getValue());
            List<MeipiCustomer> list = this.meipiCustomerDAO.findList(filter);
            if (CollectionUtils.isEmpty(list)) {
                logger.warn("数据库中不存在未其中的美批客户信息！地域编号=" + regionCode);
                String custCode = this.addMpCust(regionCode);
                if (!StringUtils.hasText(custCode)) {
                    logger.error("创建美批用户失败！地域编号=" + regionCode);
                }
                return -1;
            }
            String[] ids = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ids[i] = list.get(i).getCustCode();
            }
            this.redisTemplate.del(listKey);
            this.redisTemplate.rpush(listKey, ids);
            logger.warn("重建美批用户队列结束！地域编号=" + regionCode);
            return ids.length;
        } finally {
            this.redisTemplate.unlock(lockKey);
        }
    }

    private String getKey4Lock(Integer regionCode) throws BusinessException {
        return new StringBuilder(Constants.KEY_MEIPI_CUSTOMER_CREATE_LOCK).append("_").append(regionCode).toString();
    }

    private String getKey4List(Integer regionCode) throws BusinessException {
        return new StringBuilder(Constants.kEY_MEIPI_CUSTOMER_CODES).append("_").append(regionCode).toString();
    }

    protected com.lsh.ofc.proxy.model.MeipiCustomer getMeipiCustomer(MeipiCustomer customer) {
        com.lsh.ofc.proxy.model.MeipiCustomer user = new com.lsh.ofc.proxy.model.MeipiCustomer();
        user.setMarketName(customer.getCustName());
        user.setContactName(customer.getContactName());
        user.setContactPhone(customer.getContactPhone());
        user.setProvince(customer.getProvince());
        user.setCity(customer.getCity());
        user.setDistrict(customer.getDistrict());
        user.setAddress(customer.getAddress());
        user.setRegionCode(customer.getRegionCode());
        user.setSoUserRegion(customer.getCustZone());
        return user;
    }

    protected abstract String commitMeipiCustomer(MeipiCustomer customer) throws BusinessException;

}
