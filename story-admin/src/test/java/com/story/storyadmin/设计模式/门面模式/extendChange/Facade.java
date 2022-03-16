package com.story.storyadmin.设计模式.门面模式.extendChange;

import com.story.storyadmin.设计模式.门面模式.ServiceA;
import com.story.storyadmin.设计模式.门面模式.ServiceB;
import com.story.storyadmin.设计模式.门面模式.ServiceC;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description:
 */
public class Facade {
    //是不是很像我们controller里注入各种service?
    private ServiceA serviceA = new ServiceA();
    private ServiceB serviceB = new ServiceB();
    private ServiceC serviceC = new ServiceC();

    public void doA() {
        serviceA.doA();
    }

    public void doB() {
        serviceB.doB();
    }

    public void doC() {
        serviceC.doC();
    }
}
