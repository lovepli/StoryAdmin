package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.domain.entity.sysmgr.Attachment;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.mapper.sysmgr.AttachmentMapper;

import com.story.storyadmin.service.sysmgr.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {

    @Autowired
    AttachmentMapper AttachmentMapper;

    @Override
    public List<Attachment> selectNamesByIds(List<String> ids) {
        return AttachmentMapper.selectNamesByIds(ids);
    }

    @Override
    public Attachment selectBySequence(String sequence) {
        return AttachmentMapper.selectBySequence(sequence);
    }

    @Override
    public Result persist(Attachment attachment) {
        //保存
        baseMapper.insert(attachment);
        // 获取到id
        Long id= AttachmentMapper.selectById(attachment).getId();
        System.out.println("文件上传的数据的id"+id);

        return new Result(true, "文件上成功", id, Constants.TOKEN_CHECK_SUCCESS);

    }

    @Override
    public void persist2(Attachment attachment) {
        baseMapper.updateById(attachment);
    }

    @Override
    public Attachment selectAttachmentById(Long id) {
        return baseMapper.selectById(id);
    }
}
