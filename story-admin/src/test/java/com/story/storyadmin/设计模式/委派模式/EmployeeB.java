package com.story.storyadmin.设计模式.委派模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
public class EmployeeB implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工B，擅长做架构，现在开始做" + command);
    }
}
