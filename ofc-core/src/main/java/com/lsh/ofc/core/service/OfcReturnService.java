package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcReturnHead;

import java.util.List;

/**
 * OFC单据Service
 * Created by huangdong on 16/9/9.
 */
public interface OfcReturnService {

    /**
     * 查询OFC单据信息列表
     *
     * @param filter
     * @param fillDetails
     * @return
     * @throws BusinessException
     */
    List<OfcReturnHead> findList(OfcReturnHead filter, boolean fillDetails) throws BusinessException;

    /**
     * 统计OFC单据信息数量
     *
     * @param filter
     * @return
     * @throws BusinessException
     */
    int count(OfcReturnHead filter) throws BusinessException;

    /**
     * 插入OFC单据信息
     *
     * @param order
     * @throws BusinessException
     */
    void insert(OfcReturnHead order) throws BusinessException;
}
