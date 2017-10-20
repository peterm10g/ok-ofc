package com.lsh.ofc.core.util;

import com.lsh.base.common.exception.BusinessException;
import com.lsh.ofc.core.enums.Region;
import com.lsh.ofc.core.exception.EC;
import org.apache.log4j.MDC;

/**
 * Created by huangdong on 16/8/19.
 */
public final class OFCUtils {

    public static int currentTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Integer getSupplierId(Integer regionCode, boolean consign) throws BusinessException {
        if (Region.Beijing.getCode().equals(regionCode)) {
            return consign ? 2 : 1;
        } else if (Region.Tianjin.getCode().equals(regionCode)) {
            return consign ? 4 : 3;
        } else if (Region.Hangzhou.getCode().equals(regionCode)) {
            return consign ? 6 : 5;
        } else {
            throw EC.ERROR.exception("");
        }
    }

    public static <T> String join(final T... elements) {
        if (elements == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (T element : elements) {
            if (element == null) {
                continue;
            }
            builder.append(element);
        }
        return builder.toString();
    }
}
