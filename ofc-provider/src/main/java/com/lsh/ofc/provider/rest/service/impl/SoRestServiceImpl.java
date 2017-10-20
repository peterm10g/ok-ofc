package com.lsh.ofc.provider.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.entity.OfcSoDetail;
import com.lsh.ofc.core.entity.OfcSoHead;
import com.lsh.ofc.core.service.OfcSoService;
import com.lsh.ofc.provider.rest.service.SoRestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Service(protocol = "rest", validation = "true")
@Path("/so")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class SoRestServiceImpl implements SoRestService {

    @Autowired
    private OfcSoService ofcSoService;

    @GET
    @Path("/query")
    @Override
    public CommonResult<List<OfcSoHead>> query(@QueryParam("orderCode") Long orderCode) throws BusinessException {
        List<OfcSoHead> list;
        if (orderCode == null) {
            list = Collections.emptyList();
        } else {
            OfcSoHead filter = new OfcSoHead();
            filter.setOrderCode(orderCode);
            list = this.ofcSoService.findList(filter, true);
        }
        return CommonResult.success(list);
    }

    @GET
    @Path("/details")
    @Override
    public CommonResult<List<OfcSoDetail>> details(@QueryParam("soBillCode") String soBillCode) throws BusinessException {
        return CommonResult.success(this.ofcSoService.findDtails(soBillCode));
    }
}
