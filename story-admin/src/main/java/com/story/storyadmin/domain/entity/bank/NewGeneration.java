package com.story.storyadmin.domain.entity.bank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @author: 59688
 * @date: 2021/5/20
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("JTKF_NEW_GENERATION")
public class NewGeneration extends Model<NewGeneration> {

    private static final  long serialVersionUID =1L;

    /**
     * 主键
     * 对应Oracle数据库字段：VARCHAR2(50)
     */
    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    @TableField("SUBJECT_CODE")
    private String subjectCode;

    @TableField("ORG_NAME")
    private String orgName;

    /**
     * 账面余额 对应Oracle数据库字段：NUMBER(12,2)
     */
    @TableField("BOOK_BALANCE")
    private BigDecimal bookBalance;

    /**
     * 汇率 对应Oracle数据库字段：NUMBER(20,8)
     */
    @TableField("EXCHANGE_RATE")
    private BigDecimal exchangeRate;

    /**
     * 记账日期 对应Oracle数据库字段：DATE
     */
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @TableField("ACCOUNT_DATE")
    private Date accountDate;

    /**
     * 挂账天数 对应Oracle数据库字段：INTEGER
     */
    @TableField("ACCOUNT_DAYS")
    private Integer accountDays;


    /**
     * 上传日期
     */
    @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @TableField("UPLOAD_DATE")
    private Date uploadDate;

    /**
     * 是否有效（0：否，1:是） 对应Oracle数据库字段：NUMBER(1)
     *
     */
    @TableField("DELETED")
    private Integer deleted;



    @Override
    protected Serializable pkVal(){
        return this.id;
    }




}
