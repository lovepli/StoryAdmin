package com.story.storyadmin.stream;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Stream基础认识
 */
@DisplayName("Stream基础认识")
public class DemoStreamTests {

	@Test
	public void contextLoads() {
	}


    /**
     * 什么是Stream？
     * Stream将要处理的元素集合看作一种流，在流的过程中，借助Stream API对流中的元素进行操作，比如：筛选、排序、聚合等。
     * Stream可以由数组或集合创建，对流的操作分为两种：
     * 1. 中间操作，每次返回一个新的流，可以有多个。
     *
     * 2. 终端操作，每个流只能进行一次终端操作，终端操作结束后流无法再次使用。终端操作会产生一个新的集合或值。
     *
     * 另外，Stream有几个特性：
     * 1. stream不存储数据，而是按照特定的规则对数据进行计算，一般会输出结果。
     *
     * 2. stream不会改变数据源，通常情况下会产生一个新的集合或一个值。
     *
     * 3. stream具有延迟执行特性，只有调用终端操作时，中间操作才会执行。
     */

    /**
     * Stream可以通过集合数组创建。3中方式创建流
     */

    /**
     * 集合自带Stream流方法
     * 1、通过 java.util.Collection.stream() 方法用集合创建流
     */
    @Test
    @DisplayName("集合自带Stream流方法")
    public void test1() {
        List<String> list = Arrays.asList("a", "b", "c");
        // 创建一个顺序流
        Stream<String> stream = list.stream();
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();

        //stream和parallelStream的简单区分： stream是顺序流，由主线程按顺序对流执行操作，
        // 而parallelStream是并行流，内部以多线程并行执行的方式对流进行操作，但前提是流中的数据处理没有顺序要求。

        //如果流中的数据量足够大，并行流可以加快处速度。除了直接创建并行流，还可以通过parallel()把顺序流转换成并行流：
        //Optional<Integer> findFirst = list.stream().parallel().filter(x->x>3).findFirst();

        // Optional类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
    }

    /**
     * 通过Array数组创建
     * 2、使用java.util.Arrays.stream(T[] array)方法用数组创建流
     */
    @Test
    public void test2() {
        int[] array={1,3,5,6,8};
        IntStream stream = Arrays.stream(array);
    }

    /**
     * 3、使用Stream的静态方法创建：of()、iterate()、generate()
     */
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
        stream2.forEach(System.out::println);

        Stream<String> stream4 = Stream.generate(() -> "Hello").limit(3);
        stream4.forEach(System.out::println);// 输出 Hello,Hello,Hello

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
        stream3.forEach(System.out::println);

        //输出结果：
        //0 3 6 9
        //0.6796156909271994
        //0.1914314208854283
        //0.8116932592396652
    }

    /**
     * 数值流
     * 另外还有LongStream、DoubleStream都有这几个方法。
     */
    @Test
    public void test4(){
        // 生成有限的常量流
        IntStream intStream1 = IntStream.range(1, 3); // 输出 1,2
        IntStream intStream2 = IntStream.rangeClosed(1, 3); // 输出 1,2,3
        // 生成一个等差数列
        IntStream.iterate(1, i -> i + 3).limit(5).forEach(System.out::println); // 输出 1,4,7,10,13
        // 生成无限常量数据流
        IntStream generate = IntStream.generate(() -> 10).limit(3); // 输出 10,10,10
    }


    //####################################实战使用##############################################################

    static class User{
        private String name;

        private Integer age;

        public User(){}

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    /**
     * 1 遍历 forEach
     */
    @Test
    public void demo1(){
        List<User> users = new ArrayList<>();
        users.add(new User("Tom", 1));
        users.add(new User("Jerry", 2));
        // 循环输出user对象
        users.stream().forEach(user -> System.out.println(user));
    }

    /**
     * 2 查找 find
     */
    @Test
    public void demo2(){
        List<User> users = Arrays.asList(
        new User("Tom", 1),
        new User("Jerry", 2));
        // 取出第一个对象
        User user=users.stream().findFirst().orElse(null);
        System.out.println(user);
        // 随机取出任意一个对象
        User user2=users.stream().findAny().orElse(null);
        System.out.println(user2);
    }

    /**
     * 3 匹配 match
     */
    @Test
    public void demo3(){
        List<User> users = Arrays.asList(
                new User("Tom", 1),
                new User("Jerry", 2));
        // 判断是否存在name是Tom的用户
       // boolean existTom= users.stream().anyMatch(user -> "Tom".equals(user.getName()));
        boolean existTom= users.stream().anyMatch(user -> Objects.equals("Tom",user.getName()));
        // 判断所有用户的年龄是否都小于5
        boolean checkAge= users.stream().allMatch(user -> user.getAge()<5);
    }

    /**
     * 4 筛选 filter
     */
    @Test
    public void demo4(){
        List<User> users = Arrays.asList(
                new User("Tom", 1),
                new User("Jerry", 2));
        //筛选name是Tom的用户
        users.stream().filter(user -> Objects.equals("Tom",user.name)).forEach(System.out::println);
    }

    /**
     *映射 map/flatMap
     */
    @Test
    public void demo5(){
        List<User> users = Arrays.asList(
                new User("Tom", 1),
                new User("Jerry", 2));
        // 打印users里的name
        users.stream().map(User::getName).forEach(System.out::println);

        // List<List<User>> 转 List<User>
        List<List<User>> userList=new ArrayList<>();
        userList.add(users);
        List<User> users2=userList.stream().flatMap(Collection::stream).collect(Collectors.toList());
        users2.forEach(System.out::println);
    }

    /**
     * 6 归约 reduce
     */
    @Test
    public void  demo6(){
        List<User> users = Arrays.asList(
                new User("Tom", 1),
                new User("Jerry", 2));
        // 求用户年龄之和
        Integer sum =users.stream().map(User::getAge ).reduce(Integer::sum).orElse(0);
        System.out.println(sum);
        // 求用户年龄的乘积
        Integer product = users.stream().map(User::getAge).reduce((x,y) ->x*y).orElse(0);
        System.out.println(product);

    }

    /**
     * 7 排序 sorted
     */
    @Test
    public void demo7(){
        List<User> users = Arrays.asList(
                new User("Tom", 1),
                new User("Jerry", 2));
        // 按年龄倒序排
        List<User> collect= users.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * 8 收集 collect
     */
    @Test
    public void demo8(){
        List<User> users = Arrays.asList(
                new User("Tom", 1),
                new User("Jerry", 2));
        // list转换成map
        Map<Integer,User> map=users.stream()
                .collect(Collectors.toMap(User::getAge, Function.identity()));
        System.out.println(JSON.toJSONString(map));

        // 按年龄分组
        Map<Integer,List<User>> userMap =users.stream().
                collect(Collectors.groupingBy(User::getAge));
        System.out.println(JSON.toJSONString(userMap));

        // 求平均年龄
        Double ageAvg = users.stream().
                collect(Collectors.averagingInt(User::getAge)); // 输出 1.5

        // 求年龄之和
        Integer ageSum = users.stream().
                collect(Collectors.summingInt(User::getAge));

        // 求年龄最大的用户
        User user = users.stream().
                collect(Collectors.maxBy(Comparator.comparing(User::getAge))).orElse(null);

        // 把用户姓名拼接成逗号分隔的字符串输出
        String names = users.stream()
                .map(User::getName)
                .collect(Collectors.joining(",")); // 输出 Tom,Jerry
    }



}
