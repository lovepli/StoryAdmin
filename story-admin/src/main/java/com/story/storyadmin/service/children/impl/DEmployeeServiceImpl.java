package com.story.storyadmin.service.children.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.children.DEmployee;
import com.story.storyadmin.mapper.children.DEmployeeMapper;
import com.story.storyadmin.service.children.DEmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author: 59688
 * @date: 2021/7/9
 * @description:
 */
@Service
public class DEmployeeServiceImpl extends ServiceImpl<DEmployeeMapper, DEmployee> implements DEmployeeService {
}
