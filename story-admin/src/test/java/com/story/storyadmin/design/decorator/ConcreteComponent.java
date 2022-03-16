package com.story.storyadmin.design.decorator;

/**
 * Description:
 *
 * @author yuange
 * @version 1.0
 * @date: 2020/1/20 13:47
 * @since JDK 1.8
 */
public class ConcreteComponent implements Component {

    public ConcreteComponent() {
        System.out.println("-------------------");
    }

    @Override
    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}
