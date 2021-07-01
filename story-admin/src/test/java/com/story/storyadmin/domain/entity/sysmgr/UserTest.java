package com.story.storyadmin.domain.entity.sysmgr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import com.story.storyadmin.service.sysmgr.UserService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest extends TestCase {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;


    @Test
    public void Test1() {
        // 查询出所有数据
        List<User> list = userMapper.selectList(null);
        assertEquals(5, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void Test2() {
        //
        QueryWrapper<User> wrapper =new QueryWrapper<>();
        wrapper.select("id","name","email").likeRight("name","li");
        List<Map<String,Object>> maps =userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }



}