package com.story.storyadmin.web.cacheTest.guavaController;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.cache.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: 自定义guava本地缓存实现
 */
@RestController
public class GurvaUserController {

    @Resource
    private CacheService cacheService;

    @GetMapping("saveOrUpdate")
    public String saveOrUpdate(){
        cacheService.saveOrUpdate(new User(4L, "user4"));
        return "ok";
    }

    @GetMapping("get")
    public User get(Long id){
        return cacheService.get(id);
    }

    @GetMapping("delete")
    public String delete(Long id){
        cacheService.delete(id);
        return "d-ok";
    }

}
