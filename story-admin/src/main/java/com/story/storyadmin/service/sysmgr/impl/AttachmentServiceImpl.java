package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.mapper.sysmgr.AttachmentMapper;

import com.story.storyadmin.service.sysmgr.AttachmentService;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
}
