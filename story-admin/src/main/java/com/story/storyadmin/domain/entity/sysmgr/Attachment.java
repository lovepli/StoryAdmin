package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 附件
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_attachment")
public class Attachment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String sequence;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 上传时间
     */
    private Date uploadDate;

    /**
     * 上传人Id
     */
    private Long uploader;

    /**
     * 1 正常 0 已被删除
     */
    private Short status;

    /**
     * 删除人Id
     */
    private Long deleter;

    /**
     * 删除时间
     */
    private Date deleteDate;

    /**
     * 文件名
     */
    private String realFileName;

    /**
     * 文件大小
     */
    private Long fileSize;


}