package com.lsh.ofc.provider.rest.common;

import com.alibaba.dubbo.rpc.RpcException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.exception.EC;
import org.apache.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by huangdong on 16/8/28.
 */
public class ValidationExceptionMapper implements ExceptionMapper<RpcException> {

    private static final Logger logger = Logger.getLogger(ValidationExceptionMapper.class);

    @Override
    public Response toResponse(RpcException e) {
        return this.handleConstraintViolationException((ConstraintViolationException) e.getCause());
    }

    protected Response handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder builder = new StringBuilder(EC.ERROR_PARAMS.getMessage());
        for (ConstraintViolation cv : e.getConstraintViolations()) {
            builder.append("[").append(cv.getPropertyPath().toString()).append("] ").append(cv.getMessage()).append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        String message = builder.toString();
        logger.error("request param is error! " + message);
        return Response.status(Response.Status.OK).entity(new CommonResult(EC.ERROR_PARAMS.getCode(), message)).build();
    }
}
