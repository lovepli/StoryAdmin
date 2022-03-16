package com.story.storyadmin.service.children.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.mapper.children.DCourseMapper;
import com.story.storyadmin.service.children.DCourseService;
import com.story.storyadmin.domain.entity.children.DCourse;
import com.story.storyadmin.utils.bank.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Service
public class DCourseServiceImpl extends ServiceImpl<DCourseMapper, DCourse> implements DCourseService {


    @Autowired
    private DCourseMapper dCourseMapper;

    @Override
    public Page<DCourse> findByPage(JSONObject jsonObject) {
        Page<DCourse> page =new PageUtil<DCourse>().getPage(jsonObject);
        page.setRecords(dCourseMapper.findByPage(page,jsonObject));
        return page;
    }
}
