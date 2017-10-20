package com.lsh.ofc.provider.rest.service;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.provider.rest.dto.ReturnHeadDTO;

import java.util.List;

/**
 * 返仓单REST服务
 * Created by huangdong on 16/8/28.
 */

public interface ReturnRestService {

    /**
     * 创建返仓RO
     *
     * @param dto
     * @return
     * @throws BusinessException
     */
    CommonResult<List<String>> createRo(ReturnHeadDTO dto) throws BusinessException;

    /**
     * 查询返仓状态
     *
     * @param returnCode
     * @return
     * @throws BusinessException
     */
    CommonResult<Object> queryRoStatus(Long returnCode) throws BusinessException;
}
