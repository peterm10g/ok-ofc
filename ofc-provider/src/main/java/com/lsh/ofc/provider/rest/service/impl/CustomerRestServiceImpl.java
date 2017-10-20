package com.lsh.ofc.provider.rest.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.lsh.base.common.exception.BusinessException;
import com.lsh.base.common.model.CommonResult;
import com.lsh.ofc.core.base.AbstractTask;
import com.lsh.ofc.core.handler.MeipiCustomerHandler;
import com.lsh.ofc.core.service.MeipiCustomerService;
import com.lsh.ofc.core.service.OfcCustomerService;
import com.lsh.ofc.provider.rest.service.CustomerRestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service(protocol = "rest", validation = "true")
@Path("/customer")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class CustomerRestServiceImpl implements CustomerRestService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OfcCustomerService ofcCustomerService;

    @Autowired
    private MeipiCustomerHandler meipiCustomerHandler;

    @POST
    @Path("/refresh/{code}")
    @Override
    public CommonResult<Boolean> refresh(@PathParam("code") Long custCode) throws BusinessException {
        ofcCustomerService.refreshCustomer(custCode);
        return CommonResult.success(true);
    }

    @POST
    @Path("/meipi/addbatch")
    @Override
    public CommonResult<List<String>> addMpCust(@QueryParam("region") Integer regionCode, @QueryParam("num") int num) throws BusinessException {
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            futures.add(executor.submit(new MpCustAddTask(regionCode, this.meipiCustomerHandler)));
        }
        List<String> codes = new ArrayList<>();
        for (Future<String> future : futures) {
            try {
                String custCode = future.get();
                codes.add(custCode);
                logger.info("新增美批客户：" + custCode);
            } catch (Throwable e) {
                logger.error("地域[" + regionCode + "]新增美批客户异常：" + e.getMessage(), e);
            }
        }
        return CommonResult.success(codes);
    }

    private static class MpCustAddTask extends AbstractTask<String> {

        final Integer regionCode;

        final MeipiCustomerHandler service;

        public MpCustAddTask(Integer regionCode, MeipiCustomerHandler service) {
            this.regionCode = regionCode;
            this.service = service;
        }

        @Override
        public String execute() throws Exception {
            return this.service.addMpCust(regionCode);
        }
    }
}
