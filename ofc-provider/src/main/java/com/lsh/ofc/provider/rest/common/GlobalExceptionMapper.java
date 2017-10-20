package com.lsh.ofc.provider.rest.common;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by huangdong on 16/8/28.
 */
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private final Logger logger = Logger.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Throwable t) {
        if (t instanceof BusinessException) {
            BusinessException e = (BusinessException) t;
            logger.error("[" + e.getCode() + "]" + e.getMessage(), e);
            return Response.ok().entity(new CommonResult<>(e.getCode(), e.getMessage())).build();
        } else {
            logger.error(t.getMessage(), t);
            return Response.serverError().entity(CommonResult.error(t.getMessage(), ExceptionUtils.getStackTrace(t))).build();
        }
    }
}
