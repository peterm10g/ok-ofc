package com.lsh.ofc.core.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.entity.OfcReturnHead;
import com.lsh.ofc.core.entity.OfcRoHead;

import java.util.List;

/**
 * OFC返仓拆分Service
 * Created by huangdong on 16/9/9.
 */
public interface OfcRoSplitService {

    /**
     * 返仓拆分
     *
     * @param order
     * @return
     * @throws BusinessException
     */
    List<OfcRoHead> split(OfcReturnHead order) throws BusinessException;
}
