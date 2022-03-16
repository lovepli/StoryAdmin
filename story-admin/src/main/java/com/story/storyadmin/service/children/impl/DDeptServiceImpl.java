package com.story.storyadmin.service.children.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.children.DDept;
import com.story.storyadmin.mapper.children.DDeptMapper;
import com.story.storyadmin.service.children.DDeptService;
import com.story.storyadmin.utils.bank.DateUtils;
import com.story.storyadmin.utils.bank.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description:
 */
@Service
public class DDeptServiceImpl  extends ServiceImpl<DDeptMapper, DDept> implements DDeptService {

    @Autowired
    private DDeptMapper dDeptMapper;

    @Override
    public Page<DDept> findByPage(JSONObject jsonObject) {
        Page<DDept> page =new PageUtil<DDept>().getPage(jsonObject);
        // 获取前端页面传过来的日期字符串
        String uploadDateStr=jsonObject.getString("uploadDate"); // 这中字符串日期传递到xml文件中中需要使用函数to_date()格式化一下，否则在service层就做string转date处理，那么到xml就不用处理了
        Date uploadDate = DateUtils.getDate(uploadDateStr); // 字符串转日期

        // 1、xml文件中直接传递jsonObject对象
        page.setRecords(dDeptMapper.findByPage(page,jsonObject));
        //2、xml文件传单个的字段信息 （日期为String 因为数据库中的日期格式为Date，所以在xml的sql语句中需要做日期格式化处理）
        // page.setRecords(newGenerationMapper.findByPage2(page,uploadDateStr,accountDays,netCodeList));
        // 日期为Date格式
        // page.setRecords(newGenerationMapper.findByPage3(page,uploadDate,accountDays,netCodeList));
        return page;
    }
}
