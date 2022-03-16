package com.story.storyadmin.cacheTest;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.service.cache.CacheService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

/**
 * @author: lipan
 * @date: 4:15 下午
 * @description:
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("用户缓存模块单元测试")
public class GurvaCacheTest {

    @Resource
    private CacheService cacheService;

    @Test
    @DisplayName("测试添加保存")
    public void saveOrUpdate(){
        cacheService.saveOrUpdate(new User(4L, "user4"));
        System.out.println("添加保存缓存成功！");
    }


    @Test
    @DisplayName("测试查询")
    public void get(){
      User user= cacheService.get(4L);
        System.out.println("查询用户缓存："+user.toString());
    }


    @Test
    @DisplayName("测试删除")
    public void delete(Long id){
        cacheService.delete(id);
        System.out.println("删除用户缓存成功");
    }


    /**
     * 测试通过
     */
    @Test
    @DisplayName("测试所有")
    public void testAll(){
        cacheService.saveOrUpdate(new User(4L, "user4"));
        System.out.println("添加保存缓存成功！");
        User user= cacheService.get(4L);
        System.out.println("查询用户缓存："+user.toString());
        cacheService.delete(4L);
        System.out.println("删除用户缓存成功");
    }

}
