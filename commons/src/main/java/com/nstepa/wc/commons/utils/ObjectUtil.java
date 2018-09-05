package com.nstepa.wc.commons.utils;

public class ObjectUtil {

    public static boolean valueEqual(Object o1, Object o2) {
        if (o1 != null && o2 != null && o1 == o2) {
            return true;
        }
        return false;
    }

    public static boolean objectEqual(Object o1, Object o2) {
        if (o1 != null && o2 != null && o1.equals(o2)) {
            return true;
        }
        return false;
    }
}
