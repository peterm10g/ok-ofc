package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcRoHead;
import com.lsh.ofc.core.enums.RoStatus;

import java.util.List;
import java.util.Set;

/**
 * OFC RO Service
 * Created by huangdong on 16/9/9.
 */
public interface OfcRoService {

    int count(OfcRoHead filter) throws BusinessException;

    /**
     * 查询SO信息R
     *
     * @param filter
     * @param fillDetails
     * @return
     * @throws BusinessException
     */
    List<OfcRoHead> findList(OfcRoHead filter, boolean fillDetails) throws BusinessException;

    /**
     * 插入RO信息
     *
     * @param sos
     * @return
     * @throws BusinessException
     */
    int insert(List<OfcRoHead> sos) throws BusinessException;

    /**
     * 更新RO状态
     *
     * @param filter
     * @param entity
     * @return
     * @throws BusinessException
     */
    int updateStatus(OfcRoHead filter, OfcRoHead entity) throws BusinessException;

    /**
     * 刷新RO状态
     *
     * @param ro
     * @return
     * @throws BusinessException
     */
    Integer refreshRoStatus(OfcRoHead ro) throws BusinessException;

    /**
     * 根据时间戳,状态获取指定数量的OfcRoHead
     *
     * @param roStatusSet
     * @param timeInterval
     * @param offset
     * @param size
     * @return
     */
    List<OfcRoHead> fetchRoByStatusAndTimeStamp(Set<RoStatus> roStatusSet, int timeInterval, long offset, int size);

}
