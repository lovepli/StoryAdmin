package com.story.storyadmin.designPatterns.payService4;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description: 模板方法判断
 *
 * 当然，除了上面介绍的两种方法，Spring的源码实现还告诉我们另一种解决if...else问题的方法。
 *
 * 其实我们可以通过定义一个接口或者抽象类来做到这一点，其中有一个支持方法来判断参数传递的代码是否可以自己处理，如果可以处理，支付逻辑 用来。
 * 每个支付类都有一个 support 方法，判断传过来的 code 是否和自己定义的相等
 */
public interface IPay4 {

    public void pay();

    boolean support(String code);
}
