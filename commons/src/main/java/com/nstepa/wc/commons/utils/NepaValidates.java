package com.nstepa.wc.commons.utils;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Set;

/**
 * 校验用的工具类.
 */
public final class NepaValidates {

    /**
     * 判断两个值是否相等
     * @param a
     * @param b
     * @return
     */
    public static boolean eq(Object a, Object b){
        return a  == b || (a != null && a.equals(b));
    }

    /**
     * 判断一个值是否在一个数组中出现
     * @param source 源值
     * @param collections 值的集合
     * @param <T>
     * @return
     */
    public static <T> boolean in(T source, T... collections){
        if(ArrayUtils.isEmpty(collections)) return false;
        for(T item : collections){
            if(eq(source,item)) return true;
        }
        return false;

    }

    /**
     * 检查一个数组中的值中是否有任何一个后续的条件值
     * @param vals
     * @param conditions
     * @return
     */
    public static <T> boolean containsAny(T[] vals, T... conditions) {
        for(T val : vals){
            if(ArrayUtils.contains(conditions,val)) return true;
        }
        return false;
    }

    /**
     * 检查一个数组中的值中是否有任何一个后续的条件值
     * @param vals
     * @param conditions
     * @return
     */
    public static <T> boolean containsAny(Set<T> vals, T... conditions) {
        if(CollectionUtils.isEmpty(vals)) return false;
        Set<T> copy = Sets.newHashSet(vals);
        copy.removeAll(Sets.newHashSet(conditions));
        return copy.size() > 0;
    }

    /**
     * 判断一个对象是否指定类的子类
     * @param obj
     * @param types
     * @return
     */
    public static boolean instanceOf(Object obj, Class<?>... types) {
        for(Class<?> type : types){
            if(type.isInstance(obj)) return true;
        }
        return false;
    }

    /**
     * 判断数值是否大于指定整形
     * @param base
     * @param toCompare
     * @return
     */
    public static boolean gt(Number base, Number toCompare) {
        if(base == null ) return false;
        if(toCompare == null) return true;
        return base.doubleValue() > toCompare.doubleValue();
    }
}
