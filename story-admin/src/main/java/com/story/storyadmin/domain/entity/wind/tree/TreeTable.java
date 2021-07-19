package com.story.storyadmin.domain.entity.wind.tree;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package test.treetable
 * @title: 树形结构表控制器
 * @description: 树形结构表控制器
 * @author: admin
 * @date: 2019-11-13 21:38:33
 * @copyright: www.sunseagear.com Inc. All rights reserved.
 */

@TableName("test_tree_table")
@SuppressWarnings("serial")
public class TreeTable extends TreeEntity<String> {

    /**
     * 节点名称，不存在数据库表里面
     */
    @TableField(exist = false)
    private String label;

    @TableField(value = "geocoding")
    private String geocoding;  //地理编码
    @TableField(value = "postal_code")
    private String postalCode;  //邮政编码

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 重写方法
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName(name);
        label = name;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getGeocoding() {
        return geocoding;
    }

    public void setGeocoding(String geocoding) {
        this.geocoding = geocoding;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
