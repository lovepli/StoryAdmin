package com.story.storyadmin.service.wind.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.wind.WDict;
import com.story.storyadmin.mapper.wind.WDictMapper;
import com.story.storyadmin.service.wind.IDictService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WDictServiceImpl extends ServiceImpl<WDictMapper, WDict> implements IDictService {
    @Override
    public List<WDict> selectDictList() {
        return  baseMapper.selectDictList();
    }

}
