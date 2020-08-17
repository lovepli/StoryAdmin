package com.story.storyadmin.domain.vo.sysmgr;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 权限节点
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
public class AuthorityNode {
    private Long id;
    private Long parentId;
    private String fullId;
    private String label;
    private String code;
    private Integer showOrder;
    /** 子节点 */
    private List<AuthorityNode> children;

    public AuthorityNode(){}

    public AuthorityNode(Long id, Long parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }

    public List<AuthorityNode> getChildren() {
        if (children == null) {
            children = Lists.newArrayList();
        }
        return children;
    }
}
