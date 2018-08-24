package com.cepa.wc.springboot.support.handler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.cepa.wc.springboot.support.request.UserRequest;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义填充处理器
 */
@Component
public class MyMetaObjectHandler extends MetaObjectHandler {

    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        Object createUid = getFieldValByName("createUid", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(System.currentTimeMillis()), metaObject);
        }
        if (createUid == null && UserRequest.getCurrentUser() != null) {
//            setFieldValByName("createUid", UserRequest.getCurrentUser().getUid(), metaObject);
            setFieldValByName("createUid", null, metaObject);

        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(System.currentTimeMillis()), metaObject);
        if (UserRequest.getCurrentUser() != null) {
//            setFieldValByName("updateUid", UserRequest.getCurrentUser().getUid(), metaObject);
            setFieldValByName("updateUid", null, metaObject);
        }
    }

}
