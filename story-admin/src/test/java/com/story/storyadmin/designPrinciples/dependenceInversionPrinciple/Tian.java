package com.story.storyadmin.designPrinciples.dependenceInversionPrinciple;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 依赖倒置原则
 * 定义
 * 依赖倒置原则（Dependence Inversion Principle，DIP）指设计代码结构时，高层模块不应该依赖底层模块，二者都应该依赖其抽象。
 * 抽象不应该依赖细节，细节应该依赖抽象。通过依赖倒置，可以降低类与类之间的耦合性，提高系统的稳定性，提高代码的可读性和可维护性，
 * 并降低修改程序带来的风险。
 * 注意：以抽象为基准比以细节为基准搭建起来的架构要稳定得多，因此大家在拿到需求后，要面向接口编程，按照先顶层再细节的顺序设计代码结构。
 *
 * 我们来看一个案例，还是以课程为例，首先创建一个类Tian。
 */
public class Tian {
    public void studyJavaCourse(){
        System.out.println("老田在学java课程");
    }
    public void studyCCourse(){
        System.out.println("老田在学C课程");
    }

    //然后编写客户端测试代码并调用。
    public static void main(String[] args) {
        Tian tian = new Tian();
        tian.studyJavaCourse();
        tian.studyCCourse();
    }
}
