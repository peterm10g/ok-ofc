package com.lsh.ofc.provider.common.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.lsh.ofc.core.base.SessionId;
import org.apache.log4j.Logger;

/**
 * Created by huangdong on 16/11/30.
 */
public class CustomFilter implements Filter {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        long start = System.currentTimeMillis();
        try {
            SessionId.reset();
            Result result = invoker.invoke(invocation);
            long end = System.currentTimeMillis();
            logger.info("RPC日志==> 类名：" + invoker.getInterface().getName() + " 方法名：" + invocation.getMethodName() + " 耗时：" + (end - start) + "ms");
            return result;
        } catch (Throwable t) {
            long end = System.currentTimeMillis();
            logger.error("RPC日志==> 类名：" + invoker.getInterface().getName() + " 方法名：" + invocation.getMethodName() + " 耗时：" + (end - start) + "ms", t);
            throw t;
        } finally {
            SessionId.clear();
        }
    }
}
