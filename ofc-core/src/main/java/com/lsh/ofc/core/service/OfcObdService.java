package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcObdDetail;
import com.lsh.ofc.core.entity.OfcObdHead;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * OFC OBD Service
 * Created by huangdong on 16/9/12.
 */
public interface OfcObdService {

    /**
     * 查询OBD信息
     *
     * @param filter
     * @param fillDetails
     * @return
     * @throws BusinessException
     */
    OfcObdHead findOne(OfcObdHead filter, boolean fillDetails) throws BusinessException;

    /**
     * 查询OBD信息列表
     *
     * @param filter
     * @param fillDetails
     * @return
     * @throws BusinessException
     */
    List<OfcObdHead> findList(OfcObdHead filter, boolean fillDetails) throws BusinessException;

    /**
     * 根据SO单据号查询OBD明细列表
     *
     * @param soBillCode
     * @return
     * @throws BusinessException
     */
    List<OfcObdDetail> findDtails(String soBillCode) throws BusinessException;

    /**
     * 创建OBD信息
     *
     * @param order
     * @throws BusinessException
     */
    void create(OfcObdHead order) throws BusinessException;

    /**
     * 合并OBD信息
     *
     * @param orderCode
     * @return
     * @throws BusinessException
     */
    int merge(Long orderCode) throws BusinessException;

    /**
     * 更新OBD信息(返仓)
     *
     * @param billCode
     * @param returnQtyMap
     * @return
     * @throws BusinessException
     */
    BigDecimal update4Return(String billCode, Map<Long, BigDecimal> returnQtyMap) throws BusinessException;
}
