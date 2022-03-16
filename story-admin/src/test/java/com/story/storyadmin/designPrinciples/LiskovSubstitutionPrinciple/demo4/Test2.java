package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.demo4;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description: 当子类的方法实现父类的方法时(重写/重载或实现抽象方法)，方法的后置条件(即方法的输出/返回值)要比父类更严格或相等。
 */
public class Test2 {

        public static void main(String[] args) {
            Child2 child = new Child2();
            System.out.println(child.method());
        }
        //子类method被执行
       //{message=子类method被执行}

}
