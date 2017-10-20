package com.lsh.ofc.provider.rest.common;

import com.lsh.base.common.model.CommonResult;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;

/**
 * Created by huangdong on 16/8/28.
 */
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    private final Logger logger = Logger.getLogger(ExceptionMapper.class);

    @Override
    public Response toResponse(Exception e) {
        logger.error(e.getMessage(), e);
        return Response.ok().entity(CommonResult.error(e.getMessage())).build();
    }
}
