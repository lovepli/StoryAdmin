package com.story.storyadmin.service.bank.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import com.story.storyadmin.mapper.bank.NewGenerationMapper;
import com.story.storyadmin.service.bank.NewGenerationService;
import com.story.storyadmin.utils.PageUtil;
import com.story.storyadmin.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 59688
 * @date: 2021/5/20
 * @description:
 */
@Service
public class NewGenerationServiceImpl extends ServiceImpl<NewGenerationMapper, NewGeneration> implements NewGenerationService {

    @Autowired
    private NewGenerationMapper newGenerationMapper;

    @Override
    public Page<NewGeneration> findByPage(JSONObject jsonObject) {
        Page<NewGeneration> page =new PageUtil<NewGeneration>().getPage(jsonObject);
        // 获取前端页面传过来的日期字符串
        String uploadDate=jsonObject.getString("uploadDate"); // 这中字符串日期传递到xml文件中中需要使用函数to_date()格式化一下，否则在service层就做string转date处理，那么到xml就不用处理了
        String businessNumber=jsonObject.getString("businessNumber");
        Integer accountDays =jsonObject.getInteger("accountDays");
        List<String> netCodeList = jsonObject.getJSONArray("netCodeList").toJavaList(String.class);
        List<Integer> fillStatusList = jsonObject.getJSONArray("fillStatusList").toJavaList(Integer.class);
        if(StringUtils.isEmpty(businessNumber)){
            // 判断字符串是否为空
        }
        if (CollectionUtils.isEmpty(fillStatusList)){
            // 判断集合是否为空
        }

        // 1、xml文件中直接传递jsonObject对象
        page.setRecords(newGenerationMapper.findByPage(page,jsonObject));
        //2、xml文件传单个的字段信息
       // page.setRecords(newGenerationMapper.findByPage2(page,uploadDate,accountDays,netCodeList));
        return page;
    }
}
