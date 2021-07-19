package com.story.storyadmin.domain.vo.sysmgr;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 菜单节点
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Data
public class ResourceNode {
  private Long id;
  /** 父id */
  private Long parentId;
  /** 菜单编号路径 */
  private String fullId;
  /**菜单名字*/
  private String label;
  /** 图标样式类 */
  private String iconClass;
  /** 链接 */
  private String url;
  /** 页面路径 */
  private String component;
  /** 权限id */
  private Long authorityId;
  /** 排序 */
  private Integer showOrder;
  /** 子节点 */
  private List<ResourceNode> children;

  public ResourceNode() {}

  /** 构造函数 */
  public ResourceNode(Long id, Long parentId, String label) {
    this.id = id;
    this.parentId = parentId;
    this.label = label;
  }

  public List<ResourceNode> getChildren() {
    if (children == null) {
      children = Lists.newArrayList();
    }
    return children;
  }
}
