package com.story.storyadmin.service.bank;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.bank.NewGeneration;

import java.util.List;

/**
 * @author: 59688
 * @date: 2021/5/20
 * @description:
 */
public interface NewGenerationService extends IService<NewGeneration> {

    public Page<NewGeneration> findByPage(JSONObject jsonObject);

    void updateNewGeneration(List<NewGeneration> NewGenerations);
}
