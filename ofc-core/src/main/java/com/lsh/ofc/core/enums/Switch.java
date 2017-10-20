package com.lsh.ofc.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * Created by panxudong on 17/4/20.
 */
@AllArgsConstructor
@Getter
public enum Switch {

    OFF("0"), ON("1");

    private String value;

    public static Switch valuesOf(String value) {
        Switch[] array = Switch.values();
        if (!StringUtils.hasText(value)) {
            for (int i = 0; i < array.length; i++) {
                Switch sw = array[i];
                if (sw.getValue() == null) {
                    return sw;
                }
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                Switch sw = array[i];
                if (value.equals(sw.getValue())) {
                    return sw;
                }
            }
        }
        return null;
    }
}
