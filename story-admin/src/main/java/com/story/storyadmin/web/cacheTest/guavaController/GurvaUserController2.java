package com.story.storyadmin.web.cacheTest.guavaController;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.cache.CacheService;
import com.story.storyadmin.service.cache.CacheService2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: 自定义guava本地缓存实现2 主要看这个处理逻辑思路 https://www.lagou.com/lgeduarticle/16286.html
 */
@RestController
public class GurvaUserController2 {

    @Resource
    private CacheService2 cacheService2;

//    @RequestMapping(value = "/get",method = {RequestMethod.GET})
//    @ResponseBody
//    public CommonReturnType getItem(@RequestParam(name = "id")Integer id){
//        //在本地缓存中查找
//        ItemModel itemModel= (ItemModel) cacheService2.getFromCommonCache("item_"+id);
//
//        if(itemModel==null){
//            //本地缓存没有则到redis缓存中查找
//            itemModel= (ItemModel) redisTemplate.opsForValue().get("item_"+id);
//            if(itemModel==null){
//                //都没有则到数据库查找，找到后放入redis中
//                itemModel = itemService.getItemById(id);
//                redisTemplate.opsForValue().set("item_"+id,itemModel);
//                redisTemplate.expire("item_"+id,10, TimeUnit.MINUTES);
//            }
//            //本地缓存没有时，在redis或数据库找到后再放入本地缓存
//            cacheService2.setCommonCache("item_"+id,itemModel);
//        }
//
//
//        ItemVO itemVO = convertVOFromModel(itemModel);
//
//        return CommonReturnType.create(itemVO);
//
//    }


}
