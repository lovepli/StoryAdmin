package com.story.storyadmin.service.wind.impl;

import com.story.storyadmin.domain.entity.wind.tree.TreeTable;
import com.story.storyadmin.mapper.wind.TreeTableMapper;
import com.story.storyadmin.service.wind.ITreeTableService;
import com.story.storyadmin.service.wind.tree.impl.TreeCommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package test.treetable
 * @title: 树形结构表控制器
 * @description: 树形结构表控制器
 * @author: admin
 * @date: 2019-11-13 21:38:34
 * @copyright: www.sunseagear.com Inc. All rights reserved.
 */
@Transactional
@Service("treeTableService")
public class TreeTableServiceImpl extends TreeCommonServiceImpl<TreeTableMapper, TreeTable, String> implements ITreeTableService {

}
