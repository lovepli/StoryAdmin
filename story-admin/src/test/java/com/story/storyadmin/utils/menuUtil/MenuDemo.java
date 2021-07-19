package com.story.storyadmin.utils.menuUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lipan
 * @date: 2020/5/14
 * @description: 部门/菜单等树型结构转换
 */
@Data
public class MenuDemo implements Serializable {
    private static final long serialVersionUID = 5561561457068906366L;

    private Integer menuId;

    private Integer parentId;

    private String menuName;

    private String url;

    private List<MenuDemo> children;

    public MenuDemo(){}

    public MenuDemo(Integer menuId, Integer parentId, String menuName) {
        this.menuId = menuId;
        this.parentId = parentId;
        this.menuName = menuName;
    }
}