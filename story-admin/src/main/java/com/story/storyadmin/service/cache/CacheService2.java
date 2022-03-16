package com.story.storyadmin.service.cache;

import com.story.storyadmin.domain.entity.sysmgr.User;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description:
 */
public interface CacheService2 {

    //存方法
    void setCommonCache(String key,Object value);
    //取方法
    Object getFromCommonCache(String key);
}
