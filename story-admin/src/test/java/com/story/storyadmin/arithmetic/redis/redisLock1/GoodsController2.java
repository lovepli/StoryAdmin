package com.story.storyadmin.arithmetic.redis.redisLock1;

import cn.hutool.http.HttpUtil;
import com.story.storyadmin.arithmetic.redis.bloomFilter.RedisBloomFilter;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description: Redis分布式锁防止缓存击穿 https://mp.weixin.qq.com/s/TTDgAlkPd9VXSV7WV_j5Yw
 */
@RestController
@RequestMapping("goods2")
public class GoodsController2 {
    @Autowired
    private UserMapper goodsMapper;
    @Autowired
    private RedisBloomFilter redisBloomFilter;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //根据ID查询商品
    // 查询DB未加分布式锁
    @GetMapping("/{id}")
    public Result id(@PathVariable String id){
       // 先查询Redis缓存
        Object o= redisTemplate.opsForValue().get(id);
        if (o!=null){
            // 命中缓存
            System.err.println("id"+id+",命中redis缓存");
            return Result.success(o);
        }

        //缓存未命中，查询数据库
        System.err.println("id:"+id+",查询DB...");
        User goods = goodsMapper.selectById(id);
        //结果存入Redis
        redisTemplate.opsForValue().set(id, goods);
        return Result.success(goods);
    }


    /**
     * 开启5个线程，并发访问测试
     * 我们预期希望 DB 只查询一次，后面 4 个查询从 Redis 缓存中取就行，但是结果：
     * 5 个请求并发的都查询了DB，和预期不一致，并非1个查询DB，4个查询缓存
     * 没有加分布式锁，结果也在意料之中，但是这样容器给 DB 造成很大压力。
     * @param args
     */
    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            System.out.println(i);
            new Thread(() ->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               String str= HttpUtil.get("http://localhost:9430/goods/1");
                System.out.println(str);
            }).start();
        }
    }

    /**
     * 如果是单台服务器，直接使用 Java 的同步锁即可
     * 遗憾的是，通常后端是会部署集群的，Java 的同步锁可没办法实现分布式锁。
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result id2(@PathVariable String id){
        // 先查询Redis缓存
        Object o= redisTemplate.opsForValue().get(id);
        if (o!=null){
            // 命中缓存
            System.err.println("id"+id+",命中redis缓存");
            return Result.success(o);
        }

        //缓存未命中，查询数据库
        synchronized (this){
            // 再次检查
            o=redisTemplate.opsForValue().get(id);
            if (o!=null){
                // 命中缓存
                System.err.println("id"+id+",命中redis缓存");
                return Result.success(o);
            }
            System.err.println("id:"+id+",查询DB...");
            User goods = goodsMapper.selectById(id);
            //结果存入Redis
            redisTemplate.opsForValue().set(id, goods);
            return Result.success(goods);
        }

    }


    /**
     * Java 的内置锁只能应用在单台机器上，无法实现分布式，可以巧用 Redis 来实现分布式锁。
     *
     * 加了分布式锁后的代码
     * @param id
     * @return
     */
    //根据ID查询商品
    @GetMapping("/{id}")
    public Result id3(@PathVariable String id){
        //先查Redis缓存
        Object o = redisTemplate.opsForValue().get(id);
        if (o != null) {
            //命中缓存
            System.err.println("id:"+id+",命中redis缓存...");
            return Result.success(o);
        }

        //缓存未命中 查询数据库
        String lockKey = "lock" + id;
        //加锁，10s后过期
        for (;;) {
            if (redisTemplate.opsForValue().setIfAbsent(lockKey, System.currentTimeMillis(), 10L, TimeUnit.SECONDS)) {
                //加锁成功的线程,再次检查
                o = redisTemplate.opsForValue().get(id);
                if (o != null) {
                    //命中缓存
                    System.err.println("Thread:" + Thread.currentThread().getName() + ",id:"+id+",命中redis缓存...");
                    //释放锁
                    redisTemplate.delete(lockKey);
                    return Result.success(o);
                }

                //仍未命中
                System.err.println("Thread:" + Thread.currentThread().getName() + ",id:" + id + ",查询DB...");
                User goods = goodsMapper.selectById(id);
                //结果存入Redis
                redisTemplate.opsForValue().set(id, goods);
                //释放锁
                redisTemplate.delete(lockKey);
                return Result.success(goods);
            }
            //竞争不到锁，暂时让出CPU资源
            Thread.yield();
        }
    }
}