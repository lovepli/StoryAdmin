package com.story.storyadmin.designPrinciples.LiskovSubstitutionPrinciple.demo4;

import java.util.HashMap;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class Child2 extends Base2 {
    //当子类的方法实现父类的方法时(重写/重载或实现抽象方法)，方法的后置条件(即方法的输出/返回值)要比父类更严格或相等。
    @Override
    public HashMap method() {
        HashMap hashMap = new HashMap();
        System.out.println("子类method被执行");
        hashMap.put("message","子类method被执行");
        return hashMap;
    }
}
