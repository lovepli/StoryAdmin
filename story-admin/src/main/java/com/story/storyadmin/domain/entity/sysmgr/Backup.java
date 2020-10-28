package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * DB备份表
 * </p>
 *
 * @author sunnj
 * @since 2019-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_backup")
public class Backup extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备份日期
     */
    private Date backupDate;

    /**
     * 备份名称
     */
    private String backupName;

    /**
     * 备份路径
     */
    private String backupPath;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 文件大小
     */
    private Long fileSize;

    private String remark;

    /**
     * 运行时间
     */
    private Long runtime;
    /**
     * 状态
     */
    private Integer status;

}
