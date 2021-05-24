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
 * @date: 2021/5/20
 * @description: 上传文件表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("JTKF_EXCEL_INFO")
public class ExcelInfo extends Model<ExcelInfo> {

    private static final  long serialVersionUID =1L;

    /**
     * 主键
     * 对应Oracle数据库字段：VARCHAR2(50)
     */
    @TableId(value = "ID",type = IdType.INPUT)
    private String id;

    /**
     * 业务id (值取新一代表的id)
     */
    @TableField("BUSINESS_ID")
    private String businessId;

    /**
     * 附件名称
     */
    @TableField("FILE_NAME")
    private String fileName;

    /**
     * 附件路径
     */
    @TableField("FILE_PATH")
    private String filePath;

    /**
     * 附件格式
     */
    @TableField("FILE_Format")
    private String fileFormat;



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

    /**
     *  发布季度
     */
    @TableField("UPLOAD_JD")
    private String uploadJd;

    /**
     * 发布状态
     */
    @TableField("STATUS")
    private Integer status;


    @Override
    protected Serializable pkVal(){
        return this.id;
    }


}
