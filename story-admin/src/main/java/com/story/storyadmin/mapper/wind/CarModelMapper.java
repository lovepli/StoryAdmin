package com.story.storyadmin.mapper.wind;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.wind.CarModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarModelMapper extends BaseMapper<CarModel> {
    List<CarModel> selectDictList();
}
