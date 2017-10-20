package com.lsh.ofc.provider.rest.common;

import com.alibaba.dubbo.rpc.protocol.rest.support.LoggingFilter;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by huangdong on 16/7/15.
 */
public class CustomRestFilter extends LoggingFilter {

    public void filter(ContainerRequestContext context) throws IOException {
        super.filter(context);
        MultivaluedMap<String, String> map = context.getHeaders();
        String apiversion = map.getFirst("api-version");
        String random = map.getFirst("random");
        String platform = map.getFirst("platform");
//      String secretType = map.getFirst("secret-type");
//      String sign = map.getFirst("sign");
        if (StringUtils.isAnyBlank(apiversion, random, platform)) {
            throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity("request forbidden!").build());
        }
    }
}
