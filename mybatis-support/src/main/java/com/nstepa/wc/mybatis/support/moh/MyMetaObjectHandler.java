package com.nstepa.wc.mybatis.support.moh;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义填充处理器
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    public void insertFill(MetaObject metaObject) {
        Object fieldType = getFieldValByName("createTime", metaObject);
        if (fieldType == null) {
            setFieldValByName("createTime", new Date(System.currentTimeMillis()), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
            setFieldValByName("updateTime", new Date(System.currentTimeMillis()), metaObject);
    }

}
