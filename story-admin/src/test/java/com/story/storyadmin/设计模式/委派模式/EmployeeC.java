package com.story.storyadmin.设计模式.委派模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
public class EmployeeC implements IEmployee {
    @Override
    public void doing(String command) {
        System.out.println("我是员工C，擅长做业务，现在开始做" + command);
    }
}
