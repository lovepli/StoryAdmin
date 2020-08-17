package com.story.storyadmin.service.common;

/**
 * 异步缓存
 * 加锁/释放锁
 */
public interface ISyncCacheService {

	Boolean getLock(String lockName, int expireTime);

	Boolean releaseLock(String lockName);
}
