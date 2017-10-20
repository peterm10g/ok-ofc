package com.lsh.ofc.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.lsh.ofc.core.constant.Constants;
import com.lsh.ofc.core.dao.OfcSupplierDAO;
import com.lsh.ofc.core.entity.OfcSupplier;
import com.lsh.ofc.core.handler.WumartBasicHandler;
import com.lsh.ofc.core.redis.RedisTemplate;
import com.lsh.ofc.core.service.OfcSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;

@Service
public class OfcSupplierServiceImpl implements OfcSupplierService {

    @Autowired
    private OfcSupplierDAO ofcSupplierDAO;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WumartBasicHandler wumartBasicHandler;

    @Override
    public OfcSupplier getSupplierByCode(String code, Integer regionCode) {
        OfcSupplier supplier;

        String redisKey = this.buildRedisKey(code, regionCode);
        boolean isExist = this.redisTemplate.hexists(Constants.REDIS_HASH, redisKey);
        if (!isExist) {
            supplier = this.setSupplierToRedis(redisKey, code, regionCode);
        } else {
            String supplierString = this.redisTemplate.hget(Constants.REDIS_HASH, redisKey);
            if (StringUtils.hasText(supplierString)) {
                supplier = JSON.parseObject(supplierString, OfcSupplier.class);
            } else {
                supplier = this.setSupplierToRedis(redisKey, code, regionCode);
            }
        }

        return supplier;
    }

    @Override
    public List<OfcSupplier> findList(OfcSupplier params) {
        return this.ofcSupplierDAO.findList(params);
    }

    private OfcSupplier setSupplierToRedis(String redisKey, String code, Integer regionCode) {
        OfcSupplier supplier = this.getSupplierByCodeForDb(code, regionCode);
        this.redisTemplate.hset(Constants.REDIS_HASH, redisKey, JSON.toJSONString(supplier));

        this.wumartBasicHandler.updateConfig(supplier);
        return supplier;
    }

    private OfcSupplier getSupplierByCodeForDb(String code, Integer regionCode) {
        OfcSupplier param = new OfcSupplier();
        param.setCode(code);
        param.setRegionCode(regionCode);
        return this.ofcSupplierDAO.findOne(param);
    }

    private String buildRedisKey(String code, Integer regionCode) {
        String redisKey = MessageFormat.format(Constants.OFC_SUPPLIER_PREFIX, regionCode, code);
        return redisKey;
    }

}
