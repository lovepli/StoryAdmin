package com.story.storyadmin.basic.lambda;

import java.util.Arrays;

/**
 * @author: lipan
 * @date: 2019-06-08
 * @description:
 *
 * 方法引用(Method Reference)分为： 静态方法引用，实例方法引用 构造方法引用，这里我们只写了静态方法引用，视频里面有其他的讲解
 */

class SortedBy{
    //根据name排序
    static int name(String s1,String s2) {
        return s1.compareTo(s2);
    }

    //静态方法引用：    方法签名只看参数类型和返回值类型 这里的 int nameIgnoreCase(String s1, String s2) 与   int compare(String s1, String s2) 的方法签名是一致的，所以SortedBy类的
                      //静态方法nameIgnoreCase 恰好符合 Comparator的接口定义 ，因此在需要传入 Comparator的地方，我们直接传入方法引用 格式为 类名：：方法名  这里可以写成 SortedBy::nameIgnoreCase
    //忽略大小写进行排序
    static int nameIgnoreCase(String s1, String s2) {    //<-->  interface Comparator<String> {
        s1 = s1.toLowerCase();                           //                 int compare(String s1, String s2)
        s2 = s2.toLowerCase();                           //   }
        return s1.compareTo(s2);
    }
    //根据长度进行排序
    static int length(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (n1 == n2) {
            return s1.compareTo(s2);
        }
        return n1 < n2 ? -1 : 1;
    }
}


public class LambdaSort {

    public static void main(String[] args) throws Exception{

        String[] array="Java Apple lambda function OOP".split(" ");

        //方法引用：如果某个签名和接口恰好一致：可以直接传入方法引用 例如String ::compareTo  格式为 类名：：方法名
        Arrays.sort(array,SortedBy::name);  //引用SortedBy的静态方法 name方法
        //Arrays.sort(array,SortedBy::nameIgnoreCase);
       // Arrays.sort(array,String::compareToIgnoreCase);  //引用String类内置的忽略大小写进行排序的实例方法 甚至不需要SortedBy这个类
        System.out.println(Arrays.toString(array));
    }

}
