package com.story.storyadmin.arithmetic.redis.redisLock;

/**
 * 手写单例 Redis 分布式锁
 * https://www.cnblogs.com/niuyourou/p/15342071.html
 */
public interface RedisRockUtil {
    //加锁
    public boolean lock(String key, String requestId) throws Exception;

    //解锁
    public boolean unlock(String key, String requestId) throws Exception;


    /**
     * 　手写分布式锁，仅适用于单例 Redis。
     *
     * 　　与多线程的加锁解锁机制一样，分解出加锁解锁需要做的动作后，想办法保证动作的原子性即可。
     *
     * 　　X86 架构提供了 getAndSet 原语，保证了锁的检查与上锁这组动作的原子性，操作系统在其基础上提供了非常多的加锁方法。
     *
     * 　　Redis 也提供了类似的 “原语”：SETNX 指令。如果 key 存在则返回 0 ，如果 key 不存在则设置 key 并返回 1 。
     *
     * 　　通过 SETNX 上锁，在持有锁的情况下 DEL 掉 KEY 解锁。加锁、解锁两个动作的原子性都可以保证。
     *
     * 　　但分布式环境下，情况没有这么简单。一方面，需要在锁上维护请求标识，防止一个请求的锁被其它请求解锁；另一方面，需要在锁上维护过期时间，防止发起请求的线程挂掉导致死锁。
     *
     * 　　这样，加锁/解锁 的动作就变的复杂了：
     *
     * 　　加锁：检查锁是否存在，不存在则上锁；存在则检查过期时间，若当前锁已过期则删除当前锁并上锁。
     *
     * 　　解锁：获取锁，检查请求 ID 是否为本次请求对应的 ID，如果是则解锁。
     *
     * 　　检查锁是否存在，不存在则上锁；可以通过 SETNX 指令保证原子性。
     *
     * 　　存在则检查过期时间，若当前锁已过期则删除当前锁并上锁；检查请求 ID 是否为本次请求对应的 ID，如果是则解锁 两个动作是没有指令支持的。需要想办法保证其原子性。
     *
     * 　　这里采用 LUA 脚本的方式保证上述两组动作的原子性。
     */
}