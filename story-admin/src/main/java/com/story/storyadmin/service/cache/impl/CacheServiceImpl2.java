package com.story.storyadmin.service.cache.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.cache.CacheService;
import com.story.storyadmin.service.cache.CacheService2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description:
 */
@Service
public class CacheServiceImpl2 implements CacheService2 {

    private Cache<String,Object> commonCache=null;

    @PostConstruct
    public void init(){
        commonCache= CacheBuilder.newBuilder()
                //缓存初始容量10
                .initialCapacity(10)
                //最多100个key，超过按LRU策略移除
                .maximumSize(100)
                //写入后多少秒过期
                .expireAfterWrite(60, TimeUnit.SECONDS).build();
    }
    @Override
    public void setCommonCache(String key, Object value) {
        commonCache.put(key,value);
    }

    @Override
    public Object getFromCommonCache(String key) {
        return commonCache.getIfPresent(key);
    }

}
