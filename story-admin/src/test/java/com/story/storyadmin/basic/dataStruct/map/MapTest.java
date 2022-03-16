package com.story.storyadmin.basic.dataStruct.map;



import com.story.storyadmin.basic.dataStruct.list.Student;

import java.util.*;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * map的key其实就是集合set，不能重复；value其实就是list集合，可以存储重复的元素
 *
 * HashMap内部存储不保证有序：
 * 遍历时的顺序不一定是put放入的顺序，也不一定是key的排序顺序
 *
 * SortedMap保证遍历时以key的顺序排序：即key的字母顺序 a, b, c...排序 注意：是原素排序顺序而不是添加时的顺序
 * SortedMap的实现类是TreeMap
 *
 *
 */
public class MapTest {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(new Student("Ming", 12), new Student("Jun", 14), new Student("Mark", 15));
     //  Map<String, Student> map = new HashMap<>();   //元素是无序的  ////这里我们把name作为key Student作为value !!!
     //  Map<String, Student> map = new TreeMap<>();  //支持排序
        Map<String, Student> map = new TreeMap<>(new Comparator<String>() {  //自定义排序
            @Override
            public int compare(String o1, String o2) {
                return - o1.compareTo(o2);  //倒序
            }
        });

        //遍历集合
        for (Student s : list) {
            map.put(s.getName(), s);  //key =学生姓名 value=学生
        }
        System.out.println(map.get("Jun"));//(Person: Jun, 14)
        System.out.println(map.get("Min")); //null
        System.out.println("===========");
        //遍历key keySet()
        for (String key : map.keySet()) {
            System.out.println(key + "->" + map.get(key));
        }
        System.out.println("===========");
        //同时遍历key和value可以 entrySet()
        for (Map.Entry<String, Student> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }


        //  Map<String, Student> map = new HashMap<>()

//        Ming->(Person: Ming, 12)
//        Jun->(Person: Jun, 14)
//        Mark->(Person: Mark, 15)



        // Map<String, Student> map = new TreeMap<>();
//        Jun->(Person: Jun, 14)
//        Mark->(Person: Mark, 15)
//        Ming->(Person: Ming, 12)

        //自定义倒序
//        Ming->(Person: Ming, 12)
//        Mark->(Person: Mark, 15)
//        Jun->(Person: Jun, 14)
    }
}
