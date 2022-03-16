package com.story.storyadmin.myDatastucture.queue.priorityQueue;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

/**
 * Description: 优先级队列
 *
 * @author Lvshen
 * @version 1.0
 * @date: 2020-12-2 8:48
 * @since JDK 1.8
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(10, (Comparator<Student>) (v1, v2) -> {
            // 设置优先级规则（倒序，分值越高权限越大）
            return v2.getScore() - v1.getScore();
        });
        // 构建实体类
        Student s1 = new Student(80, "Lvshen", null);
        Student s2 = new Student(100, "Zhouzhou", null);
        Student s3 = new Student(60, "Hall", null);
        // 入列
        queue.offer(s1);
        queue.offer(s2);
        queue.offer(s3);
        while (!queue.isEmpty()) {
            // 遍历名称
            Student item = (Student) queue.poll();
            System.out.println("Name：" + item.getName() +
                    " Level：" + item.getScore());
        }
    }
}

@AllArgsConstructor
class Student {
    private Integer score;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    public Student() {
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(Integer score, String name) {
        this.score = score;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }
}
