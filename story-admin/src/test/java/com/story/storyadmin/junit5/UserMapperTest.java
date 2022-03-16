package com.story.storyadmin.junit5;

import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: 59688
 * @date: 2021/7/14
 * @description:
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("用户模块单元测试")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private static User user;

    @BeforeAll //在所有测试方法前执行，只执行一次
    public static void setUp() {
        user = new User();
        user.setId(99l);
        user.setName("张三三");
        user.setEmail("test@gamil.com");
        user.setAccount("admin888");
    }

    @Test
    @DisplayName("增加用户")
    public void addUserTest() {
        //支持java8 lambda
        assertAll("添加用户成功.",
                () -> assertNotNull(userMapper.insert(user)),
                () -> assertNotNull(user.getId()));
    }

    @Test
    @DisplayName("删除用户")
    public void deleteUserTest() {
        assertEquals(0, userMapper.deleteById(3l));
    }

    @Test
    @DisplayName("修改用户")
    public void modifyUserTest() {
        user.setId(3l);
        assertEquals(0, userMapper.updateById(user));
    }

    @Test
    @DisplayName("查询用户")
    public void queryUserTest() {
        assertNull(userMapper.selectAll(user));
    }
}
