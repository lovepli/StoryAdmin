package com.story.storyadmin.mapper.sysmgr;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.story.storyadmin.domain.entity.sysmgr.ImageFile;
import org.apache.ibatis.annotations.Param;


public interface ImageFileMapper  extends BaseMapper<ImageFile> {

    public int insertUrl(@Param("name")String name, @Param("lujing")String lujing, @Param("url")String url);

    public void deleteImage(@Param("url")String url);

    public ImageFile selectImageByUrl(@Param("url")String url);
}
