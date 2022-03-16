package com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO.demo;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author: lipan
 * @date: 2021年09月09日 7:00 下午  Mybatis-Plus MetaObjectHandler实现创建人创建时间等字段自动填充遇到的坑
 * @description: https://blog.csdn.net/weixin_39151984/article/details/113437805?utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link
 */
@Slf4j
//@Component
public class MyMetaObjectHandler2 implements MetaObjectHandler {

    private static String CREATE_DATE = "createDate";
    private static String MODIFIED_DATE = "et.modifiedDate";
    private static String CREATE_BY = "createBy";
    private static String MODIFIED_BY = "modifiedBy";
    private static String IS_DELETED = "isDeleted";

    @Override
    public void insertFill(MetaObject metaObject) {
        fillValue(metaObject, CREATE_DATE, () -> getDateValue(metaObject.getSetterType(CREATE_DATE)));
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
           // Map<String, String> info = AuthUtil.getInfoFromHeader(request);
            Map<String, String> info =new HashMap<>();
            if (info != null) {
                Long userid = MapUtils.getLong(info, "id", 0L);
                this.setFieldValByName(CREATE_BY, userid, metaObject);
            }
        } catch (Exception e) {
            log.error("e=" + e.getMessage());
        }
        this.setFieldValByName(IS_DELETED, 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        fillValue(metaObject, MODIFIED_DATE, () -> getDateValue(metaObject.getSetterType(MODIFIED_DATE)));
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //Map<String, String> info = AuthUtil.getInfoFromHeader(request);
            Map<String, String> info =new HashMap<>();
            if (info != null) {
                Long userid = MapUtils.getLong(info, "id", 0L);
                this.setFieldValByName(MODIFIED_BY, userid, metaObject);
            }
        } catch (Exception e) {
            log.error("e=" + e.getMessage());
        }
    }

    private void fillValue(MetaObject metaObject, String fieldName, Supplier<Object> valueSupplier) {
        log.debug("---------------------fieldName=" + fieldName);
        log.debug("---------------------metaObject.hasGetter(fieldName)=" + metaObject.hasGetter(fieldName));
        log.debug("---------------------valueSupplier=" + valueSupplier.get());
        if (!metaObject.hasGetter(fieldName)) {
            return;
        }
        Object sidObj = metaObject.getValue(fieldName);
        if (sidObj == null && metaObject.hasSetter(fieldName) && valueSupplier != null) {
            setFieldValByName(fieldName, valueSupplier.get(), metaObject);
        }
    }

    private Object getDateValue(Class<?> setterType) {
        log.debug("---------------------setterType=" + setterType);
        if (Date.class.equals(setterType)) {
            return new Date();
        } else if (LocalDateTime.class.equals(setterType)) {
            return LocalDateTime.now();
        } else if (Long.class.equals(setterType)) {
            return System.currentTimeMillis();
        }
        return null;
    }
}

