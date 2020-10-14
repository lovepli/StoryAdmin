package com.story.storyadmin.config.shiro.cache;

import com.story.storyadmin.config.shiro.security.JwtProperties;
import com.story.storyadmin.utils.JedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 自定义shiro缓存管理器
 *  配置Cache管理器
 *  用于往Redis存储权限和角色标识，JedisUtils使用的是shiro-redis开源插件
 */
@Service
public class ShiroCacheManager implements CacheManager {

    @Autowired
    JedisUtils jedisUtils;

    @Autowired
    JwtProperties jwtProperties;

    /**
     * 返回缓存对象
     * @param s
     * @param <K>
     * @param <V>
     * @return
     * @throws CacheException
     */
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        //返回的ShiroCache 继承了Cache，重写Shiro的Cache缓存保存读取，实现了自定义redis作为缓存
        return new ShiroCache<K,V>(jedisUtils,jwtProperties);
    }
}
