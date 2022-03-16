package com.story.storyadmin.basic.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:
 */
public class StreamMapSample {

    class Person{
        String name;
        char gender;

        public Person(String name, char gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person(" + name + ", " + gender + ")";
        }
    }

    /**
     *Stream.map() 是一个Stream的转化方法
     *  把一个Stream转换为另一个Stream
     *  可以将一种元素类型转化为另一种元素类型
     */
    public static void streamMap() {
        Stream<Integer> s = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> s2 = s.map((n) -> n * n);  //1,4,9,16,25

        String[] array = "Stream API supports functional-style ".split(" ");
        Stream<String> stream = Arrays.stream(array);
        stream.map(String::toUpperCase).forEach(System.out::println);  //注意这里的写法。查看底层map的实现方法，继承了Function接口 @FunctionalInterface  执行apply()方法
        //将字符全部变为大写字母    String::toUpperCase 是实例方法引用
    }

    //将 String 转换为 Person类型
    public  void streamMap1() throws Exception{
        String[] inputs = {"Bob,M", "Alice,F", "Time,M", "Lily,F"};
        Stream<String> names = Arrays.stream(inputs);
        Stream<Person> persons=names.map((s) -> {  //这里s是字符串
            int n = s.indexOf(',');
            String name = s.substring(0, n);
            char gender = s.charAt(n + 1);
            return new Person(name, gender);
        });
        persons.forEach(System.out::println);
//        Person(Bob, M)
//        Person(Alice, F)
//        Person(Time, M)
//        Person(Lily, F)
    }

    public static void main(String[] args) throws Exception {

        streamMap();
        new StreamMapSample().streamMap1();

    }

}
