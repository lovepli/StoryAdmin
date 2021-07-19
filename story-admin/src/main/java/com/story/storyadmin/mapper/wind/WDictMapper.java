package com.story.storyadmin.mapper.wind;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.wind.WDict;

import java.util.List;

public interface WDictMapper extends BaseMapper<WDict> {

    List<WDict> selectDictList();

}
