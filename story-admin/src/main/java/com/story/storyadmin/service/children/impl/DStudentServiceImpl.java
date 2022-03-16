package com.story.storyadmin.service.children.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.mapper.children.DStudentMapper;
import com.story.storyadmin.service.children.DStudentService;
import com.story.storyadmin.domain.entity.children.DStudent;
import com.story.storyadmin.utils.bank.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Service
public class DStudentServiceImpl extends ServiceImpl<DStudentMapper, DStudent> implements DStudentService {

    @Autowired
    private DStudentMapper dStudentMapper;

    @Override
    public Page<DStudent> findByPage(JSONObject jsonObject) {
        Page<DStudent> page =new PageUtil<DStudent>().getPage(jsonObject);
        page.setRecords(dStudentMapper.findByPage(page,jsonObject));
        return page;
    }
}
