package com.lsh.ofc.core.redis;

import redis.clients.util.Pool;

import java.io.Closeable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by huangdong on 16/8/28.
 */
public class RedisProxyHandler<T extends Closeable> implements InvocationHandler {

    private final Pool<T> pool;

    public RedisProxyHandler(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        T resource = this.pool.getResource();
        try {
            return method.invoke(resource, args);
        } finally {
            resource.close();
        }
    }
}
