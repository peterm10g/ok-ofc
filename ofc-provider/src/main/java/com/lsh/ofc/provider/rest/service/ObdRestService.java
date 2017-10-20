package com.lsh.ofc.provider.rest.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.entity.OfcObdDetail;
import com.lsh.ofc.provider.rest.dto.ObdHeadDTO;

import java.util.List;

/**
 * OBD REST服务
 * Created by huangdong on 16/9/13.
 */
public interface ObdRestService {

    /**
     * 根据SO单据号查询OBD明细列表
     *
     * @param soBillCode
     * @return
     * @throws BusinessException
     */
    CommonResult<List<OfcObdDetail>> details(String soBillCode) throws BusinessException;

    /**
     * 推送OBD
     *
     * @param dto
     * @return
     * @throws BusinessException
     */
    CommonResult<Boolean> push(ObdHeadDTO dto) throws BusinessException;
}
