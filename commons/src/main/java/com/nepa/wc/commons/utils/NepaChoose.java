package com.nepa.wc.commons.utils;

import org.apache.commons.lang3.ArrayUtils;

public final class NepaChoose {
    /**
     * 获取第一个非空的值
     *
     * @param vals
     * @param <T>
     * @return
     */
    public static <T> T first(T... vals) {
        if (ArrayUtils.isEmpty(vals)) return null;
        for (T val : vals) {
            if (val != null) return val;
        }
        return null;
    }
}
