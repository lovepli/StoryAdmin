package com.story.storyadmin.utils.bank;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/5/24
 * @description:
 */
public class JsonObjectUtils {

    /**
     * 从前端传递给后端的json对象中取出时间范围数组，然后将时间范围转换为两个时间设置到json中
     * @param jsonObject
     */
    public static void makeDateRangeToJson(JSONObject jsonObject){
        String startTime ="";
        String endTime = "";
        if(jsonObject.getJSONArray("dateRange")!=null && jsonObject.getJSONArray("dateRange").size()>0){
            startTime = jsonObject.getJSONArray("dateRange").getString(0);
            endTime = jsonObject.getJSONArray("dateRange").getString(1);
        }
        jsonObject.put("startTime",startTime);
        jsonObject.put("endTime",endTime);
    }

    /**
     * 将实体类转换为Map
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String,Object> objectToMap(Object obj) throws IllegalAccessException{
        Map<String,Object> map =new HashMap<>();
        Class<?> clazz =obj.getClass();
        for (Field field:clazz.getDeclaredFields()){
            field.setAccessible(true);
            String fieldName=field.getName();
            Object value=field.get(obj);
            map.put(fieldName,value);
        }
        return map;
    }


}
