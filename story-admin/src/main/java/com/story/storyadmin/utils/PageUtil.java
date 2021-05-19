package com.story.storyadmin.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.security.spec.KeySpec;

/**
 * @author: 59688
 * @date: 2021/4/26
 * @description: 分页工具类和日期格式化
 */
public class PageUtil<T> {

    /**
     * 自定义获取mybatis-plus分页组件对象
     * controller接口接收JSONObject对象，既json数据
     * @param jsonObject
     * @return
     */
    public Page<T> getPage(JSONObject jsonObject){
        String pageNumberStr =jsonObject.getString("pageNumber");
        String pageSizeStr =jsonObject.getString("pageSize");
        // 当前页数默认pageNumber1，pageSize默认10
        Integer pageNumber =(StringUtils.isEmpty(pageNumberStr))?1:Integer.valueOf(pageNumberStr);
        Integer pageSize =(StringUtils.isEmpty(pageSizeStr))?10:Integer.valueOf(pageSizeStr);
        Page<T> page = new Page<>(pageNumber,pageSize);
        return page;
    }

    public Page<T> getPage(JSONObject jsonObject,long total){
        String pageNumberStr =jsonObject.getString("pageNumber");
        String pageSizeStr =jsonObject.getString("pageSize");
        // 当前页数默认pageNumber1，pageSize默认10
        Integer pageNumber =(StringUtils.isEmpty(pageNumberStr))?1:Integer.valueOf(pageNumberStr);
        Integer pageSize =(StringUtils.isEmpty(pageSizeStr))?10:Integer.valueOf(pageSizeStr);
        Page<T> page = new Page<>(pageNumber,pageSize);
        page.setTotal(total);
        return page;
    }

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


}
