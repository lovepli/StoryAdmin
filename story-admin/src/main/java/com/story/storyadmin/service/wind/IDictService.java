package com.story.storyadmin.service.wind;


import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.wind.WDict;
import com.story.storyadmin.domain.vo.Result;

import java.util.List;

public interface IDictService extends IService<WDict> {

    List<WDict> selectDictList();

    /**
     * 保存用户
     * @param wDict
     * @return
     */
    Result persist(WDict wDict);
}
