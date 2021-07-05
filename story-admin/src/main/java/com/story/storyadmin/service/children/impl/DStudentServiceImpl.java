package com.story.storyadmin.service.children.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.mapper.children.DStudentMapper;
import com.story.storyadmin.service.children.DStudentService;
import com.story.storyadmin.web.children.DStudent;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/7/5
 * @description:
 */
@Service
public class DStudentServiceImpl extends ServiceImpl<DStudentMapper, DStudent> implements DStudentService {
}
