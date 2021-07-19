package com.story.storyadmin.hash;

import com.alibaba.fastjson.JSON;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/7/13
 * @description: 你不知道的HashMap高阶用法，开发效率提升一倍
 * HashMap在工作中使用非常频繁，其实在JDK1.8的时候新增一些更高阶的用法，熟练使用这些方法可以大大提升开发效率，写出更简洁优美的代码。
 */
@DisplayName("HashMap高阶用法")
public class HashTest {

    /**
     * 1. get方法指定返回默认值（getOrDefault）
     */
    @Test
    @DisplayName("get方法指定返回默认值（getOrDefault）")
    public void test1(){
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        String value1 = map.getOrDefault("key1", "defaultValue");
        System.out.println(value1); // 输出 value1
        // 当不存在key时，返回指定默认值
        String value2 = map.getOrDefault("key2", "defaultValue");
        System.out.println(value2); // 输出 defaultValue
    }

    /**
     * 2. 当key不存在时才执行put方法（putIfAbsent）
     */
    @Test
    public void test2(){
     Map<String,String> map =new HashMap<>();
     map.put("key1","value1");
     map.putIfAbsent("key1","newValue");
     String value=map.get("key1");
        System.out.println(value);
        String value2=map.get("key2"); //value1
        System.out.println(value2); //null
    }

    /**
     * 当key存在时才执行put方法（replace）
     */
    @Test
    public void test3(){
        Map<String,String> map =new HashMap<>();
        map.put("key1","value1");
        map.replace("key1","newValue");
        map.replace("key2","value2");
        System.out.println(JSON.toJSONString(map)); //{"key1":"newValue"}
    }

    /**
     * 4. 当value不存在时重新计算（computeIfAbsent）
     * 有这样一个常见的应用场景，当一批用户按年龄分组之后，新用户怎么加入到分组中？
     *
     * 通常我们这样做的：
     */

    static class User {
        // 年龄
        private Integer age;
        // 姓名
        private String name;

        public User(){}

        public User(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void test4(){
        // key是年龄，value是用户集合
        Map<Integer, List<User>> map = new HashMap<>();
        // 来一个新用户
        User user = new User(18, "yideng");
        List<User> users = map.get(user.getAge());
        // 如果没有这个年龄段的用户，就需要创建一个集合
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        map.put(user.getAge(), users);
    }

    /**
     * 还是老套解决办法，一点都不优雅吧？JDK1.8后你可以这样做了
     */
    public void test4_1(){
        // key是年龄，value是用户集合
        Map<Integer, List<User>> map = new HashMap<>();
        // 来一个新用户
        User user = new User(18, "Yideng");
        // 如果没有这个年龄段的用户，直接创建一个集合
        List<User> users = map.computeIfAbsent(user.getAge(), k -> new ArrayList<>());
        users.add(user);
    }

    /**
     * 5. 当value存在时重新计算（computeIfPresent）
     */
    @Test
    public void test5(){
        // key是年龄，value是用户
        Map<Integer, User> map = new HashMap<>();
        map.put(18, new User(18,"Yideng"));
        // 当存在key时，就重新计算value并赋值
        map.computeIfPresent(18, (k, v) -> {
            v.setName("一灯");
            return v;
        });
        System.out.println(map); // 输出 {18:{"age":18,"name":"一灯"}}
    }

    /**
     * 6. 合并新旧两个值（merge）
     * 又有这样一个常见的应用场景，当一批用户按年龄分组之后，一批新用户怎么加入到分组中？
     */
    @Test
    public void test7(){
        // key是年龄，value是用户集合
        Map<Integer, List<User>> map = new HashMap<>();
        List<User> users1 = new ArrayList<>();
        users1.add(new User(18, "yideng"));
        map.put(18, users1);

        List<User> users2 = new ArrayList<>();
        users2.add(new User(18, "一灯"));

        // 如果key存在，就合并两个集合
        map.merge(18, users2, (oldV, v) -> {
            oldV.addAll(v);
            return oldV;
        });
        System.out.println(map);
        // 输出 {18:[{"age":18,"name":"yideng"},{"age":18,"name":"一灯"}]}
    }

}
