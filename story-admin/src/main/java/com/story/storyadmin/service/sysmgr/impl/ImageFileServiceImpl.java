package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.domain.entity.sysmgr.ImageFile;
import com.story.storyadmin.mapper.sysmgr.ImageFileMapper;
import com.story.storyadmin.service.sysmgr.ImageFileService;
import org.springframework.stereotype.Service;

@Service
public class ImageFileServiceImpl extends ServiceImpl<ImageFileMapper, ImageFile> implements ImageFileService {

    @Override
    public int insertUrl(String name, String lujing, String url) {
        return baseMapper.insertUrl(name,lujing,url);
    }

    @Override
    public void deleteImage(String url) {
       baseMapper.deleteImage(url);
    }
}
