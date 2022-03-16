package com.story.storyadmin.service.children.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.children.myBatisTypeHandlerDO.TypeHandlerDo;
import com.story.storyadmin.mapper.children.TypeHandlerMapper;
import com.story.storyadmin.service.children.TypeHandlerService;
import org.springframework.stereotype.Service;


/**
 * @author: lipan
 * @date: 2021年09月08日 4:18 下午
 * @description:
 */
@Service
public class TypeHandlerServiceImpl extends ServiceImpl<TypeHandlerMapper, TypeHandlerDo> implements TypeHandlerService {
}
