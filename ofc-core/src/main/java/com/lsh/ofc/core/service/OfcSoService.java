package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.enums.SoStatus;
import com.lsh.ofc.core.model.Costs;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * OFC SO Service
 * Created by huangdong on 16/9/9.
 */
public interface OfcSoService {

    int count(OfcSoHead filter) throws BusinessException;

    /**
     * 查询SO信息
     *
     * @param filter
     * @param fillDetails
     * @return
     * @throws BusinessException
     */
    OfcSoHead findOne(OfcSoHead filter, boolean fillDetails) throws BusinessException;

    /**
     * 查询SO信息列表
     *
     * @param filter
     * @param fillDetails
     * @return
     * @throws BusinessException
     */
    List<OfcSoHead> findList(OfcSoHead filter, boolean fillDetails) throws BusinessException;

    /**
     * 根据SO单据号查询SO明细列表
     *
     * @param soBillCode
     * @return
     * @throws BusinessException
     */
    List<OfcSoDetail> findDtails(String soBillCode) throws BusinessException;

    /**
     * 插入SO信息
     *
     * @param sos
     * @return
     * @throws BusinessException
     */
    int insert(List<OfcSoHead> sos) throws BusinessException;

    /**
     * 更新SO信息(创建)
     *
     * @param so
     * @return
     * @throws BusinessException
     */
    int update4Create(OfcSoHead so, SoStatus expect, SoStatus update) throws BusinessException;

    /**
     * 更新SO信息(发货)
     *
     * @param billCode
     * @param pickSkuQty
     * @return
     * @throws BusinessException
     */
    int update4Deliver(String billCode, BigDecimal pickSkuQty) throws BusinessException;

    /**
     * 更新SO信息(返仓)
     *
     * @param billCode
     * @param returnQtyMap
     * @return
     * @throws BusinessException
     */
    BigDecimal update4Return(String billCode, Map<Long, BigDecimal> returnQtyMap) throws BusinessException;

    /**
     * 根据时间戳,状态获取指定数量的OfcSoHead
     *
     * @param soStatusSet
     * @param timeInterval
     * @param offset
     * @param size
     * @return
     */
    List<OfcSoHead> fetchSoByStatusAndTimeStamp(Set<SoStatus> soStatusSet, int timeInterval, long offset, int size);

    int updateByFilter(OfcSoHead update, OfcSoHead filter) throws BusinessException;

    Costs calcCost(String billCode, Map<String, BigDecimal> items) throws BusinessException;
}
