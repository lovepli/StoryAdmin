package com.story.storyadmin.设计模式.委派模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
//下面假设有三哥员工
public class EmployeeA  implements  IEmployee{
    @Override
    public void doing(String command) {
        System.out.println("我是员工A，擅长做数据库设计，现在开始做" + command);
    }
}
