package com.story.storyadmin.stream;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: 59688
 * @date: 2021/7/13
 * @description: Java8 stream复杂使用场景，你遇到过吗？
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamTest {

    static  class User{
        private Integer id;
        private  String name;

        public User(int id, String name) {
            this.id =id;
            this.name=name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    /**
     * 1. 按照属性ID去重对象
     * 虽然distinct()方法也可以进行去重，但是只能比较整个对象，不能比较对象里属性。
     */
    @Test
    public void test1() {
        List<Integer> collect= Arrays.asList(1,1,2).stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 我们可以这样去重，新建一个方法，利用HashMap的key不能重复的特性，进行对象去重。
     */
    @Test
    public void test2(){
        List<User> users = Arrays.asList(new User(1,"Tom"),new User(1,"Tony"),new User(2,"Jerry"));
        // 根据Id去重
        users =users.stream().filter(distinctByKey(User::getId)).collect(Collectors.toList());
        //System.out.println(users);
        users.forEach(System.out::println);

    }


    // 利用hashMap的key不能重复特性，进行去重
    public <T> Predicate<T> distinctByKey(Function<? super T,?> keyExtractor){
        Map<Object,Boolean> map = new HashMap<>();
        return t ->map.putIfAbsent(keyExtractor.apply(t),Boolean.TRUE) == null;
    }

    /**
     * 2. List转换成Map时遇到重复主键
     */
    @Test
    public void test3(){
        List<User> users = Arrays.asList(new User(1,"Tom"),new User(1,"Tony"),new User(2,"Jerry"));
        //这样转换会报错，因为ID重复。
        Map<Integer,User> map=users.stream().collect(Collectors.toMap(User::getId,user -> user));
        System.out.println(map);
    }

    /**
     * 可以这样做
     */
    @Test
    public void test3_1(){
        List<User> users = Arrays.asList(new User(1,"Tom"),new User(1,"Tony"),new User(2,"Jerry"));
        //toMap()方法提供了解决key冲突的方法，我们直接保留第一个对象user1就行
        Map<Integer,User> map=users.stream().collect(Collectors.toMap(User::getId,user -> user,(user1,user2)->user1));
        System.out.println(map);
    }

    /**
     * 3. List集合转换
     * 怎么把List<List<User>>类型的集合转换List<User>类型
     */
    @Test
    public void test4(){
        List<User> users1 = Arrays.asList(new User(1,"Tom"),new User(2,"Jerry"));
        List<User> users2 = Arrays.asList(new User(1,"Tom"),new User(2,"Jerry"));
        List<List<User>> lists=new ArrayList<>();
        lists.add(users1);
        lists.add(users2);

        //必须使用flatMap()方法，不能用map方法，map()方法转换出来的是List<Stream<User>>
        //里面包含了Stream流，而flatMap()方法会把流里面的数据提取出来
        //List<Stream<User>> collect=lists.stream().map(Collection::stream).collect(Collectors.toList());
        List<User> collect=lists.stream().flatMap(Collection::stream).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     *4. BigDecimal求和
     */
    @Test
    public void test5(){
        List<BigDecimal> list =Arrays.asList(BigDecimal.ONE,BigDecimal.ONE,BigDecimal.ONE);
        // 使用reduce()方法就可以，第一个参数是初始值
        BigDecimal  sum =list.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println(sum); //输出 3

    }

}
