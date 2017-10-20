package com.lsh.ofc.core.util;

import com.relops.snowflake.Snowflake;

import java.util.UUID;

/**
 * ID生成器
 * Created by huangdong on 16/8/8.
 */
public class IdGenerator {

    private static final Snowflake snowflake;

    static {
        snowflake = new Snowflake(Integer.parseInt(System.getProperty("snowflake.node")));
    }

    private IdGenerator() {
    }

    public static String uuid() {
        char[] chars = UUID.randomUUID().toString().toCharArray();
        char[] uuid = new char[32];
        for (int i = 0, j = 0; i < 36; i++) {
            if (i == 8 || i == 13 || i == 18 || i == 23) {
                continue;
            }
            uuid[j++] = chars[i];
        }
        return new String(uuid);
    }

    public static long genId() {
        return snowflake.next();
    }

    public static String genCode(String prefix) {
        return new StringBuilder(prefix).append(genId()).toString();
    }
}
