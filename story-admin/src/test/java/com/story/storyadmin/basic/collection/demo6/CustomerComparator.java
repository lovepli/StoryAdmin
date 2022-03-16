package com.story.storyadmin.basic.collection.demo6;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author: lipan
 * @date: 2019-06-04
 * @description:
 */
public class CustomerComparator implements Comparator<Customer> { //客户化排序 实现Comparator<Object>比较器  必须实现compare(Object o1,Object o2)方法

    //这里只做了名字的比较
    @Override
    public int compare(Customer c1,Customer c2) {
        if (c1.getName().compareTo(c2.getName()) > 0) {
            return -1;
        }
        if (c1.getName().compareTo(c2.getName()) < 0) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Set<Customer> set = new TreeSet<>(new CustomerComparator());

        Customer customer1 = new Customer("Tom", 5);
        Customer customer2 = new Customer("Tom", 9);
        Customer customer3 = new Customer("Tom", 2);
        set.add(customer1);
        set.add(customer2);
        set.add(customer3);
        //迭代器遍历
        Iterator<Customer> it = set.iterator();
        while (it.hasNext()) {
            Customer customer = it.next();
            System.out.println(customer.getName()+" "+customer.getAge());
        }


    }
}
