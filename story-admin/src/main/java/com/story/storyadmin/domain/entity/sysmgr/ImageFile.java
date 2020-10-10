package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 图片上传表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_image_file")
public class ImageFile extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 文件名
     */
    private String name;

    /**
     * 路径
     */
    private String lujing;

    /**
     * 访问URL地址
     */
    private String url;
}
