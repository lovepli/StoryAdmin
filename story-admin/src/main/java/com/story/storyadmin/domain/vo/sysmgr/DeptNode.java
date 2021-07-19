package com.story.storyadmin.domain.vo.sysmgr;

import com.baomidou.mybatisplus.annotation.TableField;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: lipan
 * @date: 2020/7/31
 * @description:
 */
@Data
public class DeptNode {

    private Long id;
    /** 父id */
    private Long parentId;
    /**名字 */
    private String label;
    /** 排序 */
    private Integer showOrder;
    /**描述 */
    private String deptDesc;
    /** 子节点 */
    private List<DeptNode> children;

    public DeptNode(){}

    public DeptNode(Long id, Long parentId, String label,String deptDesc) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
        this.deptDesc = deptDesc;
    }

    public List<DeptNode> getChildren() {
        if (children == null) {
            children = Lists.newArrayList();
        }
        return children;
    }
}
