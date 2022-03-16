package com.story.storyadmin.basic.dataStruct.queue.priorityqueue;




import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:
 *
 * PriorityQueue
 * PriorityQueue的出队顺序与元素的优先级有关:
 * 从队首获取元素时，总是获取优先级最高的元素
 * 默认按元素比较的顺序排序(必须实现Comparable接口)
 * 可以通过Comparator自定义排序算法(不必实现Comparable接口)
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        Queue<Person> queue = new PriorityQueue<>();
        queue.offer(new Person("Ming", 11));
        queue.offer(new Person("Hong", 12));
        queue.offer(new Person("Jun", 13));
        System.out.println( queue.poll());
        System.out.println( queue.poll());
        System.out.println( queue.poll());
//
//        (Person: Hong, 12)
//        (Person: Jun, 13)
//        (Person: Ming, 11)


    }
}
