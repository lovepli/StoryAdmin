package com.story.storyadmin.arithmetic.redis.bloomFilter;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 59688
 * @date: 2021/10/13
 * @description:
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private UserMapper goodsMapper;
    @Autowired
    private RedisBloomFilter redisBloomFilter;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    //使用布隆过滤器 根据ID查询商品
    @GetMapping("/{id}")
    public Result id(@PathVariable String id){
        //先查询布隆过滤器，过滤掉不可能存在的数据请求
        if (!redisBloomFilter.isExist(id)) {
            System.err.println("id:"+id+",布隆过滤...");
            return Result.success(null);
        }
        //布隆过滤器认为可能存在，再走流程查询
        return Result.success(noFilter(id));
    }

    //不使用过滤器
    private Object noFilter(String id){
        //先查Redis缓存
        Object o = redisTemplate.opsForValue().get(id);
        if (o != null) {
            //命中缓存
            System.err.println("id:"+id+",命中redis缓存...");
            return o;
        }
        //缓存未命中 查询数据库
        System.err.println("id:"+id+",查询DB...");
        User goods = goodsMapper.selectById(id);
        //结果存入Redis
        redisTemplate.opsForValue().set(id, goods);
        return goods;
    }
}