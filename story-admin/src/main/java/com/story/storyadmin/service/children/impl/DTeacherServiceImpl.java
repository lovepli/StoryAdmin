package com.story.storyadmin.service.children.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.mapper.children.DTeacherMapper;
import com.story.storyadmin.service.children.DTeacherService;
import com.story.storyadmin.domain.entity.children.DTeacher;
import com.story.storyadmin.utils.bank.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Service
public class DTeacherServiceImpl extends ServiceImpl<DTeacherMapper, DTeacher> implements DTeacherService {


    @Autowired
    private DTeacherMapper dTeacherMapper;

    @Override
    public Page<DTeacher> findByPage(JSONObject jsonObject) {
        Page<DTeacher> page =new PageUtil<DTeacher>().getPage(jsonObject);
        page.setRecords(dTeacherMapper.findByPage(page,jsonObject));
        return page;
    }
}
