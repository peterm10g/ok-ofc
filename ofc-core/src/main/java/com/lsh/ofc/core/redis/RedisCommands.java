package com.lsh.ofc.core.redis;

import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.JedisCommands;

/**
 * Created by huangdong on 16/8/28.
 */
public abstract interface RedisCommands extends JedisCommands, BinaryJedisCommands {
}
