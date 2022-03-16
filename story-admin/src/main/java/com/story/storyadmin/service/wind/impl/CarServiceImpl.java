package com.story.storyadmin.service.wind.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.wind.Car;
import com.story.storyadmin.mapper.wind.CarMapper;
import com.story.storyadmin.service.wind.ICarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
}
