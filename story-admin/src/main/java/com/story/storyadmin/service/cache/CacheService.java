package com.story.storyadmin.service.cache;

import com.story.storyadmin.domain.entity.sysmgr.User;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description:
 */
public interface CacheService {

    /**
     * 保存或修改用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveOrUpdate(User user);

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    User get(Long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);
}
