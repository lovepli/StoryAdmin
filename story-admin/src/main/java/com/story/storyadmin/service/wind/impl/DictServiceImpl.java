package com.story.storyadmin.service.wind.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.wind.WDict;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.mapper.wind.WDictMapper;
import com.story.storyadmin.service.wind.IDictService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<WDictMapper, WDict> implements IDictService {
    @Override
    public List<WDict> selectDictList() {
        return  baseMapper.selectDictList();
    }


    /**
     * 保存/修改
     *
     * @param wDict
     * @return
     */
    @Override
    public Result persist(WDict wDict) {
        Result result = null;

        return result;
    }
}
