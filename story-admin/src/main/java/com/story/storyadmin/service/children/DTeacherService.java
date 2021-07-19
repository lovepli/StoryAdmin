package com.story.storyadmin.service.children;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.children.DTeacher;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */

public interface DTeacherService extends IService<DTeacher> {
    public Page<DTeacher> findByPage(JSONObject jsonObject);
}
