package com.story.storyadmin.jsonUtil.JacksonUtilsTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.story.storyadmin.utils.objectMethordUtil.JacksonUtils;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description:  Spring Boot 中默认使用的 json 解析框架是 jackson。
 */
@Slf4j
public class JacksonUtilsTest {

    @Data
    @ToString
   static class User {
        private Integer id;
        private String email;
    }

    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("chenhaifei@163.com");
        String userJsonstr = JacksonUtils.obj2String(user1);
        String userJsonPretty = JacksonUtils.obj2StringPretty(user1);
        log.info("userJson: {}", userJsonPretty);

        User user2 = JacksonUtils.string2Obj(userJsonstr, User.class);
        user2.setId(2);
        user2.setEmail("tianxiaorui@126.com");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        String userListJson = JacksonUtils.obj2String(userList);
        List<User> userListBean = JacksonUtils.string2Obj(userListJson, new TypeReference<List<User>>() {});
        if (userListBean != null) {
            userListBean.forEach(user -> {
                System.out.println(user.getId() + " : " + user.getEmail());
            });
        }
        List<User> userListBean2 = JacksonUtils.string2Obj(userListJson, List.class, User.class);
        if (userListBean2 != null) {
            userListBean2.forEach(user -> {
                System.out.println(user.getId() + " : " + user.getEmail());
            });
        }
    }
}
