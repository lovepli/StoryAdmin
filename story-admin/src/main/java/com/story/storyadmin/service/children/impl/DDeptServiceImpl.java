package com.story.storyadmin.service.children.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.children.DDept;
import com.story.storyadmin.mapper.children.DDeptMapper;
import com.story.storyadmin.service.children.DDeptService;
import org.springframework.stereotype.Service;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description:
 */
@Service
public class DDeptServiceImpl  extends ServiceImpl<DDeptMapper, DDept> implements DDeptService {
}
