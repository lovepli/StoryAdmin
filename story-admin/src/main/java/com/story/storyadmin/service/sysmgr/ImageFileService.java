package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.ImageFile;

public interface ImageFileService extends IService<ImageFile> {

    public int insertUrl(String name,String lujing,String url);

    public void deleteImage(String url);

}
