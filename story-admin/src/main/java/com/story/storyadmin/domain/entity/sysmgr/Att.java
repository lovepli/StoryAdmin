package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 附件表
 * </p>
 *
 * @author sunnj
 * @since 2019-07-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_att")
public class Att extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 文件名
     */
    private String name;
    /**
     * 批次
     */
    private String slotId;
    /**
     * 分类
     */
    private String fileCate;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 源文件名
     */
    private String originName;
    /**
     * 路径
     */
    private String path;
    /**
     * 描述
     */
    private String description;

    /**
     * 有效标志
     */
    private String ynFlag;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

}
