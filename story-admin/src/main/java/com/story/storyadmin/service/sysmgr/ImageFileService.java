package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.ImageFile;
import org.apache.ibatis.annotations.Param;

public interface ImageFileService extends IService<ImageFile> {

    public int insertUrl(String name,String lujing,String url);

    public void deleteImage(String url);

    public ImageFile selectImageByUrl(@Param("url")String url);


}
