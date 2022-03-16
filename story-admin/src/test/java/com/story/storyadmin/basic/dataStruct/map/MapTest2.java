package com.story.storyadmin.basic.dataStruct.map;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * equals和hashCode
 * 正确使用Map必须保证:（注：HashMap中查找元素的get()方法调用了Object的equals()方法，没有做覆写！！！）
 * 作为Key的对象必须正确覆写equals()方法，例如：我们通常使用的key为String，Integer,Long....，因为他们已经正确覆写了equals()方法
 * 作为Key的对象必须正确覆写hashCode()方法
 *
 * 作为Key的对象必须正确覆写hashCode:
 * 如果两个对象相等，则两个对象的hashCode()必须相等  即如果a.equals(b) == true, 则a.hashCode() == b.hashCode()
 * 如果两个对象不相等，则两个对象的hashCode()尽量不相等(可以相等，会造成效率下降)  即a.equals(b) == false, 则a和b的hashCode()尽量不要相等 因为不同的=对象，如果返回了相同的hashCode，会造成map内部存储冲突，造成存储的效率会下降
 * hashCode可以通过Objects.hashCode()辅助方法实现
 */
public class MapTest2 {

    public static  void fun1() {
        //Map是一种键-值(key-value)映射表：
        //可以通过key快速查找value（元素）
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("b", 12);
        map2.put("c", 13);
        map2.put("d", 15);

        String key1 = "a";
        map2.put(key1, 10);

        System.out.println(map2.get("d"));//10

        String key2 = new String("a");
        //map2.get(key2);
        System.out.println(map2.get(key2));//10
        // HashMap中查找元素的get()方法调用了Object的equals()方法，没有做覆写！！！
        // 因为这里的Object即key1和kye2是String类型，而String类型equals()方法比较的是内容是否相等，
        // 因为key1和key2的内容相同，所以为同一个对象key,所以能够在map中通过查找key得到返回结果value 10

        System.out.println(key1 == key2); //false  //我们调用put方法传入的key1和我们调用get方法传入的key2是两个不同对象，引用地址不相同
        System.out.println(key1.equals(key2)); //true  //内容相同

        //HashMap通过计算key的hashCode()定位key的存储位置，继而获得value
        // locationOf("a".hashCode()) -> 1  ->查找 array[1] ->10
    }

    public static  void fun2() {

        List<Person> list = Arrays.asList(new Person("Ming", 12), new Person("Jun", 14), new Person("Tom", 15));
        Map<Person, String> map = new HashMap<>();  //这里我们把Student作为key name作为value  !!!
        for (Person p : list) {
            map.put(p, p.getName());
        }
        System.out.println(map.get(new Person("Jun",14)));//null Map中作为key的对象他必须正确实现hashCode()和equals()方法!!!
        //凡是放入Map的key,我们都必须要正确实现equals和hashcode方法  //Jun
    }

    public static void main(String[] args) {

      //  fun1();
      //  fun2();



    }
}
