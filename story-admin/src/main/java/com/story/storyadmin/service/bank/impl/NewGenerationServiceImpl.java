package com.story.storyadmin.service.bank.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.bank.NewGeneration;
import com.story.storyadmin.mapper.bank.NewGenerationMapper;
import com.story.storyadmin.service.bank.NewGenerationService;
import com.story.storyadmin.utils.bank.DateUtils;
import com.story.storyadmin.utils.bank.PageUtil;
import com.story.storyadmin.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    //@Transactional
    @Override
    public Page<NewGeneration> findByPage(JSONObject jsonObject) {
        Page<NewGeneration> page =new PageUtil<NewGeneration>().getPage(jsonObject);
        // 获取前端页面传过来的日期字符串
        String uploadDateStr=jsonObject.getString("uploadDate"); // 这中字符串日期传递到xml文件中中需要使用函数to_date()格式化一下，否则在service层就做string转date处理，那么到xml就不用处理了
        Date uploadDate = DateUtils.getDate(uploadDateStr); // 字符串转日期
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
        //2、xml文件传单个的字段信息 （日期为String 因为数据库中的日期格式为Date，所以在xml的sql语句中需要做日期格式化处理）
       // page.setRecords(newGenerationMapper.findByPage2(page,uploadDateStr,accountDays,netCodeList));
        // 日期为Date格式
       // page.setRecords(newGenerationMapper.findByPage3(page,uploadDate,accountDays,netCodeList));
        return page;
    }

    @Transactional
    @Override
    public void updateNewGeneration(List<NewGeneration> NewGenerations){
        if(CollectionUtils.isEmpty(NewGenerations)){
            throw new RuntimeException("参数为空！");
        }
        newGenerationMapper.updateNewGeneration(NewGenerations);

    }
}