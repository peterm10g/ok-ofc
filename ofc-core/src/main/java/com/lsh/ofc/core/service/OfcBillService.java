package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcBill;
import com.lsh.ofc.core.entity.OfcObdHead;
import com.lsh.ofc.core.entity.OfcReturnHead;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.model.Costs;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * OFC单据Service
 * Created by huangdong on 16/8/28.
 */
public interface OfcBillService {

    /**
     * 查询OFC Bill
     *
     * @param billType
     * @param system
     * @param orderCodes
     * @return
     * @throws BusinessException
     */
    List<OfcBill> findList(String billType, String system, Long... orderCodes) throws BusinessException;

    /**
     * 查询OFC单据
     *
     * @param filter
     * @return
     * @throws BusinessException
     */
    List<OfcBill> findList(OfcBill filter) throws BusinessException;

    /**
     * 插入OFC单据
     *
     * @param so
     * @throws BusinessException
     */
    void insert(OfcSoHead so) throws BusinessException;

    /**
     * 插入OFC单据
     *
     * @param obd
     * @throws BusinessException
     */
    void insert(OfcObdHead obd) throws BusinessException;

    /**
     * 插入OFC单据
     *
     * @param ro
     * @throws BusinessException
     */
    void insert(OfcRoHead ro) throws BusinessException;

    /**
     * 计算订单成本
     *
     * @param orderCode
     * @param items
     * @return
     * @throws BusinessException
     */
    Costs calcCosts(Long orderCode, Map<Long, BigDecimal> items) throws BusinessException;

    /**
     * 创建返仓单据
     *
     * @param model
     * @return
     * @throws BusinessException
     */
    String createBill4Return(OfcReturnHead model) throws BusinessException;

    /**
     * 查询返仓状态
     *
     * @param returnCode
     * @return
     * @throws BusinessException
     */
    Map<String, Integer> queryReturnStatus(Long returnCode) throws BusinessException;
}
