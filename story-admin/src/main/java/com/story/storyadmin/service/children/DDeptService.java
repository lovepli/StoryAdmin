package com.story.storyadmin.service.children;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.children.DDept;


/**
 * @author: 59688
 * @date: 2021/7/9
 * @description:
 */
public interface DDeptService  extends IService<DDept> {

    public Page<DDept> findByPage(JSONObject jsonObject);
}
