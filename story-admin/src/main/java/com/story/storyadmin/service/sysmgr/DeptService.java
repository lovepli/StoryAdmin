package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.Dept;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.DeptNode;


import java.util.List;

/**
 * @author: lipan
 * @date: 2020/7/31
 * @description:
 */
public interface DeptService extends IService<Dept> {

    /**
     * 查询列表 菜单树
     * @return
     */
    List<DeptNode> findAll();

    /**
     * 根据用户查询
     * @param userId
     * @return
     */
    List<DeptNode> findByUserId(Long userId);


    /**
     * 保存
     * @param dept
     * @return
     */
    Result persist(Dept dept);
}
