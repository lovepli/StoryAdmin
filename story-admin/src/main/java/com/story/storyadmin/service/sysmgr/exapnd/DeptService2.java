package com.story.storyadmin.service.sysmgr.exapnd;

import com.baomidou.mybatisplus.extension.service.IService;
import com.story.storyadmin.domain.entity.sysmgr.expand.Dept2;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.DeptNode;

import java.util.List;

/**
 * @author: lipan
 * @date: 2020/7/31
 * @description:
 */
public interface DeptService2 extends IService<Dept2> {

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
    Result persist(Dept2 dept);
}
