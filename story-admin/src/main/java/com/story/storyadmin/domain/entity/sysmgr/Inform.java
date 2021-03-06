package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.annotation.TableName;
import com.story.storyadmin.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统通知表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("st_inform")
public class Inform extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否置顶
     */
    private Boolean top;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 状态 0撤销 1正常或过期
     */
    private Short status;

    /**
     * 创建者
     */
    private Integer creator;

    /**
     * 附件列表ID
     */
    private String attchmentList;

    /**
     * 撤销人ID
     */
    private Integer canceler;

    /**
     * 撤销时间
     */
    private Date cancelDate;

    /**
     * 过期操作人
     */
    private Integer outdateOperator;

    /**
     * 过期时间
     */
    private Date outdateDate;

    /**
     * 内容
     */
    private String content;

}