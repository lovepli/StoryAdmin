package com.story.storyadmin.arithmetic.redis.redisLock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.ArrayList;
import java.util.List;

public class ProtoRedisRockUtil implements RedisRockUtil {

    ProtoRedisRockUtil(RedisTemplate redisTemplate, int cacheTime) {
        this.redisTemplate = redisTemplate;
        this.cacheTime = cacheTime;
    }

    private RedisTemplate<String, Object> redisTemplate;
    //缓存存活时间
    private int cacheTime;

    /**
     * @Author
     * @Date 2021/9/27 上午12:11
     * @Description 缓存格式：过期时间的时间戳|请求唯一标识
     * 通过 SETNX 模拟 getAndSet
     * 通过 LUA 脚本保证 "删除过期锁、上锁" 这一对操作的原子性
     */
    @Override
    public boolean lock(String key, String requestId) throws InterruptedException {
        int tryCount = 3;
        while (tryCount > 0) {
            long currentTime = System.currentTimeMillis();
            //缓存存活的最终时间
            Long overdueTime = currentTime + this.cacheTime;
            String val = overdueTime + "|" + requestId;
            //竞争到锁
            if (redisTemplate.opsForValue().setIfAbsent(key, val)) {
                System.out.println("竞争锁成功！");
                return true;
            }
            StringBuilder USER_AIMS_GOLD_LUA = new StringBuilder();
            USER_AIMS_GOLD_LUA.append("local value = redis.call('get',KEYS[1]);");
            USER_AIMS_GOLD_LUA.append("if not value then return '-1'; end;");
            USER_AIMS_GOLD_LUA.append("local position = string.find(value,'|');");
            USER_AIMS_GOLD_LUA.append("local timeStemp = string.sub(value,0,position-1)");
            USER_AIMS_GOLD_LUA.append("timeStemp = tonumber(timeStemp)");
            USER_AIMS_GOLD_LUA.append("local currentTime = tonumber(ARGV[1])");
            USER_AIMS_GOLD_LUA.append("if currentTime>timeStemp then redis.call('del',KEYS[1]);");
            USER_AIMS_GOLD_LUA.append("if redis.call('setnx', KEYS[1], ARGV[2])==1 then return '1'; " +
                    "else return '0';end;");
            USER_AIMS_GOLD_LUA.append("else return '0';end;");
            DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
            defaultRedisScript.setScriptText(USER_AIMS_GOLD_LUA.toString());
            defaultRedisScript.setResultType(String.class);
            List<String> keyList = new ArrayList();
            keyList.add(key);
            String result = (String) redisTemplate.execute(defaultRedisScript, keyList, String.valueOf(currentTime),
                    val);
            //删除过期锁并竞争锁成功
            if ("1".equals(result)) {
                System.out.println("删除过期锁并竞争锁成功!");
                return true;
            }
            //未竞争到锁，检查当前锁是否已到期。防止死锁
            tryCount--;
            Thread.sleep(200);
        }
        System.out.println("竞争锁失败!");
        return false;
    }

    /**
     * @Author
     * @Date 2021/9/26 下午10:48
     * @Description 释放锁
     * 通过 LUA 脚本保证 "核对 uuid 、释放锁" 这一对动作的原子性
     */
    @Override
    public boolean unlock(String key, String requestId) {
        StringBuilder USER_AIMS_GOLD_LUA = new StringBuilder();
        USER_AIMS_GOLD_LUA.append("local value = redis.call('get',KEYS[1]);");
        USER_AIMS_GOLD_LUA.append("if not value then return '-1'; end;");
        USER_AIMS_GOLD_LUA.append("local position = string.find(value,'|');");
        USER_AIMS_GOLD_LUA.append("local requestId = string.sub(value,position+1)");
        USER_AIMS_GOLD_LUA.append("if ARGV[1]==requestId then ");
        USER_AIMS_GOLD_LUA.append("redis.call('del',KEYS[1]);return '1';");
        USER_AIMS_GOLD_LUA.append("else return '0'; end;");
        DefaultRedisScript defaultRedisScript = new DefaultRedisScript();
        defaultRedisScript.setScriptText(USER_AIMS_GOLD_LUA.toString());
        defaultRedisScript.setResultType(String.class);
        List<String> keyList = new ArrayList();
        keyList.add(key);
        Object result = redisTemplate.execute(defaultRedisScript, keyList, requestId);
        if ("1".equals(result)) System.out.println("自行释放锁成功");
        return "1".equals(result);
    }
}