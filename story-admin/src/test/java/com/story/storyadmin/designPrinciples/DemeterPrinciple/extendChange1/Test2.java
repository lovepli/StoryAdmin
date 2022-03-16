package com.story.storyadmin.designPrinciples.DemeterPrinciple.extendChange1;


/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Test2 {

    public static void main(String [] args){
        TeamLeader2 teamLeader = new TeamLeader2();
        Employee2 employee = new Employee2();
        teamLeader.commandCheckNumber(employee);
    }

    //写到这里，其实功能都已经实现，代码看上去也没什么问题。根据迪米特法则，TeamLeader只想要结果，不需要跟Course产生直接交流。而Employee统计需要引用Course对象，TeamLeader和Course并不是朋友，从如下图所示的类图就可以看出来。

}
