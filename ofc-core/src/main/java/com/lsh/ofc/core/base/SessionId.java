package com.lsh.ofc.core.base;

import com.lsh.ofc.core.util.IdGenerator;
import org.apache.log4j.MDC;

/**
 * 会话ID
 * Created by huangdong on 16/11/30.
 */
public class SessionId {

    private static final String SESSION_ID = "session_id";

    public static String get() {
        Object value = MDC.get(SESSION_ID);
        if (value == null) {
            return null;
        } else {
            return value.toString();
        }
    }

    public static void set(String value) {
        MDC.put(SESSION_ID, value);
    }

    public static void reset() {
        set(Long.toHexString(IdGenerator.genId()));
    }

    public static void clear() {
        MDC.remove(SESSION_ID);
    }
}
