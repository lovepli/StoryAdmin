package com.story.storyadmin.domain.entity.wind;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


@TableName("sys_dict")
@SuppressWarnings("serial")
public class Dict extends DataEntity<String> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 分组ID
     */
    @TableField(value = "gid")
    private Long gid;
    /**
     * 键值名称
     */
    @TableField(value = "label")
    private String label;
    /**
     * 值
     */
    @TableField(value = "value")
    private String value;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;
    /**
     * 分组code
     */
    @TableField(exist = false)
    private String code;

    /**
     * 获取 gid
     *
     * @return: String 分组ID
     */
    public Long getGid() {
        return this.gid;
    }

    /**
     * 设置 gid
     *
     * @param: gid
     * 分组ID
     */
    public void setGid(Long gid) {
        this.gid = gid;
    }

    /**
     * 获取 id
     *
     * @return: String 主键
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置 id
     *
     * @param: id
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 name
     *
     * @return: String 键值名称
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * 设置 name
     *
     * @param: name
     * 键值名称
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取 value
     *
     * @return: String 值
     */
    public String getValue() {
        return this.value;
    }

    /**
     * 设置 value
     *
     * @param: value
     * 值
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取 sort
     *
     * @return: Integer 排序
     */
    public Integer getSort() {
        return this.sort;
    }

    /**
     * 设置 sort
     *
     * @param: sort
     * 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
