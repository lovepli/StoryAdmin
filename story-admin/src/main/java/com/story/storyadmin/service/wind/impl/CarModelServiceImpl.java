package com.story.storyadmin.service.wind.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.wind.CarModel;
import com.story.storyadmin.mapper.wind.CarModelMapper;
import com.story.storyadmin.service.wind.ICarModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("carModelService")
public class CarModelServiceImpl extends ServiceImpl<CarModelMapper, CarModel> implements ICarModelService {

    @Override
    public List<CarModel> selectDictList() {
        return baseMapper.selectDictList();
    }

}
