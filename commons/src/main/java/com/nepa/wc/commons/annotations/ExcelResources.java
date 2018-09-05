package com.nepa.wc.commons.annotations;

import java.lang.annotation.*;

/**
 * 用来在对象的get方法上加入的annotation，
 * 通过该annotation说明某个属性所对应的标题
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE,ElementType.METHOD})
public @interface ExcelResources {
    /**
     * 属性的标题名称
     * @return
     */
    String title();
    /**
     * 在excel的顺序
     * @return
     */
    int order() default 9999;
}
