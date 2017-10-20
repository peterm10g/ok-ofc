package com.lsh.ofc.core.redis;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;
import redis.clients.util.Sharded;

import java.io.Closeable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangdong on 16/8/28.
 */
public class RedisTemplate implements InitializingBean, DisposableBean {

    private final Logger logger = Logger.getLogger(this.getClass());

    private JedisPoolConfig config;

    private JedisShardInfo[] shards;

    private Pool<? extends Closeable> pool;

    private RedisCommands commands;

    public void setConfig(JedisPoolConfig config) {
        this.config = config;
    }

    public void setShards(JedisShardInfo... shards) {
        this.shards = shards;
    }

    protected RedisCommands getResource() {
        return this.commands;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notEmpty(this.shards);
        for (JedisShardInfo info : this.shards) {
            if (!StringUtils.hasText(info.getPassword())) {
                info.setPassword(null);
            }
        }
        logger.info("Redis Nodes:" + JSON.toJSONString(this.shards));
        if (this.shards.length == 1) {
            JedisShardInfo info = this.shards[0];
            this.pool = new JedisPool(this.config, info.getHost(), info.getPort(), info.getConnectionTimeout(), info.getSoTimeout(), info.getPassword(), info.getDb(), info.getName());
        } else {
            this.pool = new ShardedJedisPool(this.config, Arrays.asList(shards), Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
        }
        ClassLoader loader = this.getClass().getClassLoader();
        Class<?>[] interfaces = {RedisCommands.class};
        InvocationHandler handler = new RedisProxyHandler<>(this.pool);
        this.commands = (RedisCommands) Proxy.newProxyInstance(loader, interfaces, handler);
        logger.info("redis test....." + this.get("test"));
    }

    @Override
    public void destroy() throws Exception {
        if (this.pool != null && !this.pool.isClosed()) {
            this.pool.close();
        }
    }

    /*----------Key（键）----------*/
    public long del(String key) {
        return this.getResource().del(key);
    }

    public boolean exists(String key) {
        return this.getResource().exists(key);
    }

    public long expire(String key, long seconds) {
        return this.expire(key, seconds, TimeUnit.SECONDS);
    }

    public long expire(String key, long duration, TimeUnit unit) {
        return this.getResource().pexpire(key, unit.toMillis(duration));
    }

    public long expireAt(String key, Date timestamp) {
        return this.pexpireAt(key, timestamp.getTime());
    }

    public long pexpireAt(String key, long timestamp) {
        return this.getResource().pexpireAt(key, timestamp);
    }

    public long persist(String key) {
        return this.getResource().persist(key);
    }

    public long ttl(final String key) {
        return this.getResource().ttl(key);
    }

    public long ttl(final String key, TimeUnit unit) {
        return unit.convert(this.getResource().pttl(key), TimeUnit.MILLISECONDS);
    }

    public String type(String key) {
        return this.getResource().type(key);
    }

    /*----------String（字符串）----------*/
    public long incr(String key) {
        return this.getResource().incr(key);
    }

    public long incr(String key, long offset) {
        return this.getResource().incrBy(key, offset);
    }

    public double incr(String key, double offset) {
        return this.getResource().incrByFloat(key, offset);
    }

    public long decr(final String key) {
        return this.getResource().decr(key);
    }

    public long decr(String key, long offset) {
        return this.getResource().decrBy(key, offset);
    }

    public double decr(String key, double offset) {
        return this.getResource().incrByFloat(key, BigDecimal.valueOf(offset).multiply(BigDecimal.valueOf(-1)).doubleValue());
    }

    public String set(final String key, final String value) {
        return this.getResource().set(key, value);
    }

    public String set(final String key, final String value, final long timeout) {
        return this.set(key, value, timeout, TimeUnit.SECONDS);
    }

    public String set(final String key, final String value, final long timeout, final TimeUnit unit) {
        return this.getResource().psetex(key, unit.toMillis(timeout), value);
    }

    public String set(String key, String value, boolean exists) {
        return this.getResource().set(key, value, (exists ? "xx" : "nx"));
    }

    public String set(String key, String value, boolean exists, long timeout, final TimeUnit unit) {
        return this.getResource().set(key, value, (exists ? "xx" : "nx"), "px", unit.toMillis(timeout));
    }

    public String get(String key) {
        return this.getResource().get(key);
    }

    public String getSet(String key, String value) {
        return this.getResource().getSet(key, value);
    }

    public long append(String key, String value) {
        return this.getResource().append(key, value);
    }

    public String getRange(String key, long start, long end) {
        return this.getResource().getrange(key, start, end);
    }

    public long setRange(String key, long offset, String value) {
        return this.getResource().setrange(key, offset, value);
    }

    public long length(String key) {
        return this.getResource().strlen(key);
    }

    public String substr(String key, int start, int end) {
        return this.getResource().substr(key, start, end);
    }

    /*----------Hash（哈希表）----------*/
    public long hincr(final String key, final String field) {
        return this.hincr(key, field, 1);
    }

    public long hincr(final String key, final String field, final long offset) {
        return this.getResource().hincrBy(key, field, offset);
    }

    public double hincr(final String key, final String field, final double offset) {
        return this.getResource().hincrByFloat(key, field, offset);
    }

    public long hdecr(final String key, final String field) {
        return this.hincr(key, field, -1);
    }

    public long hdecr(final String key, final String field, final long offset) {
        return this.hincr(key, field, offset * -1);
    }

    public double hdecr(final String key, final String field, final double offset) {
        return this.hincr(key, field, BigDecimal.valueOf(offset).multiply(BigDecimal.valueOf(-1)).doubleValue());
    }

    public long hdel(final String key, final String... fields) {
        return this.getResource().hdel(key, fields);
    }

    public boolean hexists(final String key, final String field) {
        return this.getResource().hexists(key, field);
    }

    public String hget(String key, String field) {
        return this.getResource().hget(key, field);
    }

    public long hset(String key, String field, String value) {
        return this.getResource().hset(key, field, value);
    }

    public long hsetnx(String key, String field, String value) {
        return this.getResource().hsetnx(key, field, value);
    }

    public String hmset(String key, Map<String, String> hash) {
        return this.getResource().hmset(key, hash);
    }

    public List<String> hmget(String key, String... fields) {
        return this.getResource().hmget(key, fields);
    }

    public Map<String, String> hgetAll(String key) {
        return this.getResource().hgetAll(key);
    }

    public Set<String> hkeys(String key) {
        return this.getResource().hkeys(key);
    }

    public List<String> hvals(String key) {
        return this.getResource().hvals(key);
    }

    public long hsize(String key) {
        return this.getResource().hlen(key);
    }

    /*----------List（列表）----------*/
    public List<String> blpop(String key, int timeout) {
        return this.getResource().blpop(timeout, key);
    }

    public List<String> brpop(String key, int timeout) {
        return this.getResource().brpop(timeout, key);
    }

    public String lindex(String key, long index) {
        return this.getResource().lindex(key, index);
    }

    public long lsize(String key) {
        return this.getResource().llen(key);
    }

    public String lpop(String key) {
        return this.getResource().lpop(key);
    }

    public long lpush(String key, String... values) {
        return this.getResource().lpush(key, values);
    }

    public long lpushx(String key, String... values) {
        return this.getResource().lpushx(key, values);
    }

    public String rpop(String key) {
        return this.getResource().rpop(key);
    }

    public long rpush(String key, String... values) {
        return this.getResource().rpush(key, values);
    }

    public long rpushx(String key, String... values) {
        return this.getResource().rpushx(key, values);
    }

    public List<String> lrange(String key, long start, long end) {
        return this.getResource().lrange(key, start, end);
    }

    public String lset(String key, long index, String value) {
        return this.getResource().lset(key, index, value);
    }

    public String ltrim(String key, long start, long end) {
        return this.getResource().ltrim(key, start, end);
    }

    public long lremove(String key, long count, String value) {
        return this.getResource().lrem(key, count, value);
    }

    /*----------Set（集合）----------*/
    public long sadd(String key, String... members) {
        return this.getResource().sadd(key, members);
    }

    public long ssize(String key) {
        return this.getResource().scard(key);
    }

    public boolean sexists(String key, String member) {
        return this.getResource().sismember(key, member);
    }

    public Set<String> smembers(String key) {
        return this.getResource().smembers(key);
    }

    public String spop(String key) {
        return this.getResource().spop(key);
    }

    public Set<String> spop(String key, long count) {
        return this.getResource().spop(key, count);
    }

    public String srand(String key) {
        return this.getResource().srandmember(key);
    }

    public List<String> srand(String key, int count) {
        return this.getResource().srandmember(key, count);
    }

    public long sremove(String key, String... members) {
        return this.getResource().srem(key, members);
    }

    /*----------SortedSet（有序集合）----------*/
    public long zadd(String key, double score, String member) {
        return this.getResource().zadd(key, score, member);
    }

    public long zadd(String key, Map<String, Double> members) {
        return this.getResource().zadd(key, members);
    }

    public double zscore(String key, String member) {
        return this.getResource().zscore(key, member);
    }

    public long zsize(String key) {
        return this.getResource().zcard(key);
    }

    public long zcount(String key, double min, double max) {
        return this.getResource().zcount(key, min, max);
    }

    public double zincr(String key, double score, String member) {
        return this.getResource().zincrby(key, score, member);
    }

    public Set<String> zrange(String key, long start, long end) {
        return this.getResource().zrange(key, start, end);
    }

    public Set<String> zrange(String key, double min, double max) {
        return this.getResource().zrangeByScore(key, min, max);
    }

    public long zrank(String key, String member) {
        return this.getResource().zrank(key, member);
    }

    public long zremove(String key, String... members) {
        return this.getResource().zrem(key, members);
    }

    public long zremove(String key, long start, long end) {
        return this.getResource().zremrangeByRank(key, start, end);
    }

    public long zremove(String key, double start, double end) {
        return this.getResource().zremrangeByScore(key, start, end);
    }

    public long zrevrank(String key, String member) {
        return this.getResource().zrevrank(key, member);
    }

    public Set<String> zrevrange(String key, long start, long end) {
        return this.getResource().zrevrange(key, start, end);
    }

    public Set<String> zrevrange(String key, double max, double min) {
        return this.getResource().zrevrangeByScore(key, max, min);
    }

    public boolean lock(final String key) {
        return this.set(key, "1", false) != null;
    }

    public boolean lock(final String key, final long timeout) {
        return this.lock(key, timeout, TimeUnit.SECONDS);
    }

    public boolean lock(final String key, final long timeout, final TimeUnit unit) {
        return this.set(key, "1", false, timeout, unit) != null;
    }

    public boolean unlock(final String key) {
        return this.del(key) > 0;
    }
}