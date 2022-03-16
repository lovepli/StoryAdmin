package com.story.storyadmin.basic.collection.hashmap.demo2;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author: lipan
 * @date: 2020-04-19
 * @description: Hashmap排序的几个方法 https://blog.csdn.net/jackyrongvip/article/details/89370396
 *    要进行排序，使用的方法有：
 * 1） 使用TreeMap，这个方法最简单了：
 *TreeMap<Integer, Student> sortedMap = new TreeMap<>(map);
 *把map传进去TreeMap中就可以了。
 *
 *2） 如果仅仅是排序map中的key和value，则可以：
 * List<Integer> mapKeys = new ArrayList<>(map.keySet());
 * Collections.sort(mapKeys);
 *
 * List<Student> mapValues = new ArrayList<>(map.values());
 * Collections.sort(mapValues);
 * 但前提是必须POJO实现Comparable接口
 *
 * 3)如果不希望排序的MAP中有KEY,的值的重复，可以用sortedset
 * SortedSet<String> mapKeys = new TreeSet<>(map.keySet());
 *    这个时候要POJO重写hashcode和equals方法：
 *
 *4） JAVA8的方法：
 *     根据KEY排序：
 *Map<Integer, Student> sortedMap = map.entrySet()
 *                                   .stream()
 *                                   .sorted(Map.Entry.comparingByKey())
 *                                   .collect(Collectors
 *                                     .toMap(Map.Entry::getKey,
 *                                            Map.Entry::getValue,
 *                                            (e1, e2) -> e1,
 *                                            LinkedHashMap::new))
 *
 * 也可以根据MAP的值来排序
 * sortedMap = map.entrySet()
 *               .stream()
 *               .sorted(Map.Entry.comparingByValue())
 *                .collect(Collectors
 *                           .toMap(Map.Entry::getKey,
 *                                  Map.Entry::getValue,
 *                                  (e1, e2) -> e1,
 *                                  LinkedHashMap::new));
 *
 * 也可以使用Collections.reverseOrder方法，逆序排序：
 *sortedMapDesc = map.entrySet()
 *                   .stream()
 *                   .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
 *                   .collect(Collectors
 *                    .toMap(Map.Entry::getKey,
 *                           Map.Entry::getValue,
 *                           (e1, e2) -> e1,
 *                            LinkedHashMap::new));
 *
 *  Collections 集合工具类中的排序方法直接调用：
 *
 *
 */
public class HashMapTest2 {

    private static HashMap<Integer, Student> map1;

    private static HashMap<Integer, Student2> map2;

    static {
        map1=new HashMap<>();
        map1.put(1003, new Student(1003, "Sam"));
        map1.put(1005, new Student(1005, "Joseph"));
        map1.put(1001, new Student(1001, "Kate"));
        map1.put(1002, new Student(1002, "Miranda"));
        map1.put(1004, new Student(1004, "Peter"));

        map2=new HashMap<>();
        map2.put(1003, new Student2(1003, "Sam"));
        map2.put(1005, new Student2(1005, "Joseph"));
        map2.put(1001, new Student2(1001, "Kate"));
        map2.put(1002, new Student2(1002, "Miranda"));
        map2.put(1004, new Student2(1004, "Peter"));
    }

    /*
     * @description:
     * @author: lipan
     * @date 2021/9/1 2:59 下午
     */
    public static void fun(){
        TreeMap<Integer, Student> sortedMap = new TreeMap<>(map1);
        sortedMap.forEach((k,v) ->{
            System.out.println("k："+k+"，student："+v.toString());
        });
    }

    /*
     * @description:
     * @author: lipan
     * @date 2021/9/1 3:10 下午
     */
    public static void fun2(){
        // 排序map中的key
        List<Integer> mapKeys = new ArrayList<>(map2.keySet());
        //SortedSet<Integer> mapKeys2 = new TreeSet<>(map2.keySet()); // 使用set对集合去重
        //使用 Collections 集合工具类对 list 进行排序，排序规则使用匿名内部类来实现
        Collections.sort(mapKeys);
        mapKeys.forEach((k) ->{
            System.out.println(k);
        });

       // 排序map中的value
        List<Student2> mapValues = new ArrayList<>(map2.values());
        // 使用 Collections 集合工具类对 list 进行排序，排序规则使用匿名内部类来实现
        Collections.sort(mapValues);
        // 循环输出所有数据方式1
        mapValues.forEach(System.out::println);

        // 循环输出所有数据方式2 使用stream
        //mapValues.stream().forEach(student2 -> {
        //    System.out.println(student2.toString());
        //});

        // 循环输出所有数据方式3 使用lambda
        //mapValues.forEach((k) ->{
        //    System.out.println(k.toString());
        //});
        // 或者这样
        //mapValues.forEach(student2 -> {
        //    System.out.println(student2.toString());
        //});
    }

    /*
     * @description:
     * @author: lipan
     * @date 2021/9/1 3:22 下午
     */
    public static void fun3(){
        Map<Integer, Student> sortedMap = map1.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        sortedMap.forEach((k,v) ->{
            System.out.println("k："+k+"，student："+v.toString());
        });

    }

    /*
     * @description: 
     * @author: lipan
     * @date 2021/9/1 3:26 下午
     */
    public static void fun4(){
        Map<Integer, Student2> sortedMap = map2.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        sortedMap.forEach((k,v) ->{
            System.out.println("k："+k+"，student2："+v.toString());
        });

    }

    /*
     * @description:
     * @author: lipan
     * @date 2021/9/1 3:27 下午
     */
    public static void fun5(){
        Map<Integer, Student>  sortedMapDesc = map1.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new));

        sortedMapDesc.forEach((k,v) ->{
            System.out.println("k："+k+"，student2："+v.toString());
        });
    }

    /**
     * @description:  自定义排序，上机题
     * @author: lipan
     * @date 2021/9/2 9:12 上午
     * @param map
     * @return java.util.HashMap<java.lang.Integer,com.story.storyadmin.basic.collection.demo.hashmapdemo.demo2.User>
     */
    public static HashMap<Integer, Student> sortHashMap(HashMap<Integer, Student> map) {
        // 首先拿到 map 的键值对集合
        Set<Map.Entry<Integer, Student>> entrySet = map.entrySet();

        // 将 set 集合转为 List 集合，为什么，为了使用工具类的排序方法
        List<Map.Entry<Integer, Student>> list = new ArrayList<Map.Entry<Integer, Student>>(entrySet);
        // 使用 Collections 集合工具类对 list 进行排序，排序规则使用匿名内部类来实现
        Collections.sort(list, new Comparator<Map.Entry<Integer, Student>>() {

            @Override
            public int compare(Map.Entry<Integer, Student> o1, Map.Entry<Integer, Student> o2) {
                //按照要求根据 User 的 age 的倒序进行排
                return o2.getValue().getId()-o1.getValue().getId();
            }
        });
        //创建一个新的有序的 HashMap 子类的集合
        LinkedHashMap<Integer, Student> linkedHashMap = new LinkedHashMap<Integer, Student>();
        //将 List 中的数据存储在 LinkedHashMap 中
        for(Map.Entry<Integer, Student> entry : list){
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        //返回结果
        return linkedHashMap;
    }

    public static void main(String[] args) {
        // 调用方法
       //fun();
      // fun2();
       // fun3();
       // fun4();
      //  fun5();
        HashMap<Integer,Student> sortHashMap = sortHashMap(map1);
        System.out.println(sortHashMap);
    }
}


/**
 *
 */


