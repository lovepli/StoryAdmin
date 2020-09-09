package com.story.storyadmin.service.common;

/**
 *
 * 引入Redis锁机制
 * 为避免多个请求同一时间分别生成不同的Token，我们引入redis锁机制。即我们的目的是同一个用户同一时间的不同请求，只允许获得锁的请求进行令牌刷新，其他的请求因为是在令牌有效期因此直接放行
 * 加锁/释放锁
 */
public interface ISyncCacheService {

	Boolean getLock(String lockName, int expireTime);

	Boolean releaseLock(String lockName);
}
