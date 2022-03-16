package com.story.storyadmin.basic.dataStruct.queue.priorityqueue.priorityqueue2;




import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: lipan
 * @date: 2019-06-12
 * @description:  优先队列
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        Queue<Person> queue = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return - o1.getName().compareTo(o2.getName());  //采用名字的字典倒序
            }
        });

        queue.offer(new Person("Ming", 11));
        queue.offer(new Person("Hong", 12));
        queue.offer(new Person("Jun", 13));
        System.out.println( queue.poll());
        System.out.println( queue.poll());
        System.out.println( queue.poll());

//        (Person: Ming, 11)
//        (Person: Jun, 13)
//        (Person: Hong, 12)


    }
}
