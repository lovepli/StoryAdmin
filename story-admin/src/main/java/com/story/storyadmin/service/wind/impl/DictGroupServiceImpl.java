package com.story.storyadmin.service.wind.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.wind.DictGroup;
import com.story.storyadmin.mapper.wind.DictGroupMapper;
import com.story.storyadmin.service.wind.IDictGroupService;
import org.springframework.stereotype.Service;


@Service
public class DictGroupServiceImpl extends ServiceImpl<DictGroupMapper, DictGroup> implements IDictGroupService {
}
