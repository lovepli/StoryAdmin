package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.java.Log;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Supplier;

/**
 * @author: lipan
 * @date: 2021年09月08日 9:50 下午
 * @description: SpringBoot - MyBatis-Plus使用详解17（字段数据自动填充）
 * https://www.hangge.com/blog/cache/detail_2927.html
 * 新增，更新时，自动更新字段
 */
@Log
//@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static String CREATE_TIME = "createTime";
    private static String UPDATE_TIME = "updateTime";


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 插入时自动填充
        // 注意第二个参数要填写实体类中的字段名称，而不是表的列名称
        //this.strictInsertFill(metaObject, "operationType", String.class, "新增");
        //this.strictInsertFill(metaObject, "operationTime", Date.class, new Date());
        fillValue(metaObject, CREATE_TIME, () -> getDateValue(metaObject.getSetterType(CREATE_TIME)));
        fillValue(metaObject, UPDATE_TIME, () -> getDateValue(metaObject.getSetterType(UPDATE_TIME)));

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 更新时自动填充
        //this.strictUpdateFill(metaObject, "operationType", String.class, "修改");
        //this.strictUpdateFill(metaObject, "operationTime", Date.class, new Date());
        fillValue(metaObject, "et."+UPDATE_TIME, () -> getDateValue(metaObject.getSetterType("et."+UPDATE_TIME)));

    }
    private void fillValue(MetaObject metaObject, String fieldName, Supplier<Object> valueSupplier) {
        if (!metaObject.hasGetter(fieldName)) {
            return;
        }
        Object sidObj = metaObject.getValue(fieldName);
        if (sidObj == null && metaObject.hasSetter(fieldName) && valueSupplier != null) {
            setFieldValByName(fieldName, valueSupplier.get(), metaObject);
        }
    }

    private Object getDateValue(Class<?> setterType) {
        if (Date.class.equals(setterType)) {
            return new Date();
        } else if (LocalDateTime.class.equals(setterType)) {
            return LocalDateTime.now();
        } else if (Long.class.equals(setterType)){
            return System.currentTimeMillis();
        }
        return null;
    }



}
