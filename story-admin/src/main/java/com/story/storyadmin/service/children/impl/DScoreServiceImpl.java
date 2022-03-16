package com.story.storyadmin.service.children.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.mapper.children.DScoreMapper;
import com.story.storyadmin.service.children.DScoreService;
import com.story.storyadmin.domain.entity.children.DScore;
import com.story.storyadmin.utils.bank.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Service
public class DScoreServiceImpl extends ServiceImpl<DScoreMapper, DScore> implements DScoreService {

    @Autowired
    private DScoreMapper dScoreMapper;
    @Override
    public Page<DScore> findByPage(JSONObject jsonObject) {
        Page<DScore> page =new PageUtil<DScore>().getPage(jsonObject);
        page.setRecords(dScoreMapper.findByPage(page,jsonObject));
        return page;
    }
}
