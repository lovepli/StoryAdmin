package com.story.storyadmin.comparator;

import java.util.Comparator;

/**
 * @author: 59688
 * @date: 2021/7/19
 * @description:  // 自定义比较器
 */
public class PeopleComparator implements Comparator<ComparePeople2> {


    @Override
    public int compare(ComparePeople2 people1, ComparePeople2 people2) {
        if(people1.age-people2.age==0) {
            return people1.name.compareTo(people2.name) ;
        }
        return people1.age-people2.age;
    }

    public static void main(String[] args) {
        //创建一个集合
        ComparePeople2 arrayList[] = {
                new ComparePeople2(22, "哼哼"),
                new ComparePeople2(22, "小哈"),
                new ComparePeople2(25, "哼哼的博客"),
                new ComparePeople2(23, "哼哼"),
                new ComparePeople2(30, "奋斗的哼哼")
        } ;

        System.out.println("排序之前********************");
        for (ComparePeople2 people : arrayList) {
            System.out.println(people);
        }
        //调用排序的方法
        System.out.println("排序之后********************");
        java.util.Arrays.sort(arrayList,new PeopleComparator()) ;    // 进行排序操作
        for(int i=0;i<arrayList.length;i++){    // 循环输出数组中的内容
            System.out.println(arrayList[i]) ;
        }

    }

}
