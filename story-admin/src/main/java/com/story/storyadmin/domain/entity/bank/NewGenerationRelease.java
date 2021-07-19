package com.story.storyadmin.domain.entity.bank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: 59688
 * @date: 2021/6/7
 * @description: 发布表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("JTKF_NEW_RELEASE")
public class NewGenerationRelease extends Model<NewGenerationRelease> {


    private static final  long serialVersionUID =1L;

    /**
     * 主键 取值为新一代表的id
     * 对应Oracle数据库字段：VARCHAR2(50)
     */
    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    /**
     * 经营单位机构号
     */
    @TableField("BUSINESS_NUMBER")
    private String businessNumber;

    /**
     * 网点编号
     */
    @TableField("NET_NUMBER")
    private String netNumber;

    /**
     * 经营部门
     */
    private String businessName;
    /**
     * 发布人
     */
    private String pulisher;

    /**
     * 发布状态
     */
    private Integer pulishStatus;

    /**
     * 发布日期
     */
    private Date publishDate;

    /**
     * 填报人
     */
    private String filler;

    /**
     * 填报状态
     */
    private Integer fillStatus;

    /**
     * 填报日期
     */
    private Date fillDate;

    /**
     * 审核人
     */
    private String reviewer;

    /**
     * 审核日期
     */
    private Date reviewDate;

    /**
     * 审核意见
     */
    private String remark;

    /**
     * 发布季度
     */
    private String quarterly;




    /**
     * 是否有效（0：否，1:是） 对应Oracle数据库字段：NUMBER(1)
     *
     */
    @TableField("DELETED")
    private Integer deleted;

    /**
     * 创建者
     */
    @TableField("CREATOR")
    private String creator;

    /**
     * 修改者
     */
    @TableField("UPDATOR")
    private String updator;

    /**
     * 删除者
     */
    @TableField("DELETOR")
    private  String deletor;

    /**
     * 创建日期
     *  new Date() 存贮日期，当前系统日期
     */
    @TableField("CREATE_DATE")
    private  Date createDate;

    /**
     *  修改日期
     */
    @TableField("UPDATE_DATE")
    private Date updateDate;

    /**
     * 删除日期
     */
    @TableField("DELETE_DATE")
    private Date deleteDate;


    @Override
    protected Serializable pkVal(){
        return this.id;
    }



}
