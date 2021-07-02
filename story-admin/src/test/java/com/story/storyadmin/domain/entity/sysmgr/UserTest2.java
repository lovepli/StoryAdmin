package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import com.story.storyadmin.service.sysmgr.UserService;
import junit.framework.TestCase;
import org.apache.ibatis.annotations.MapKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest2 extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(UserTest2.class);

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    @Test
    public void Test1() {
        Map<String, User> map = userMapper.getUserMap();
        for (Map.Entry<String, User> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void Test2() {
        Map<Long, User> map = userMapper.getUserMap2();
        for (Map.Entry<Long, User> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void Test21() {
     User user = userMapper.getUserMap21();
        System.out.println(user);
    }

    @Test
    public void Test211() {
        List<User> userList = userMapper.getUserMap211();
        userList.forEach(System.out::println);
    }

    @Test
    public void Test22() {
        Map<Long, User> map = userMapper.getUserMap22();
        for (Map.Entry<Long, User> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    @Test
    public void Test23() {
        List<Map<Long, User>> maps = userMapper.getUserMap23();
        maps.forEach(System.out::println);
    }

    @Test
    public void Test3() {
        Map<Long,Map<Long,Object>> map = userMapper.getUserMap3();
        for (Map.Entry<Long, Map<Long, Object>> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }





}