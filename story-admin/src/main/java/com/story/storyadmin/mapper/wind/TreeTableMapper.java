package com.story.storyadmin.mapper.wind;


import com.story.storyadmin.domain.entity.wind.tree.TreeTable;
import com.story.storyadmin.mapper.wind.tree.BaseTreeMapper;
import org.apache.ibatis.annotations.Mapper;

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
@Mapper
public interface TreeTableMapper extends BaseTreeMapper<TreeTable> {

}
