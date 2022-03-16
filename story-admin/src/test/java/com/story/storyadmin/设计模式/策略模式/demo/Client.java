package com.story.storyadmin.设计模式.策略模式.demo;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 策略模式通用代码
 */
public class Client {

//抽象策略类 Strategy
interface IStrategy {
    void algorithm();
}

//具体策略类 ConcreteStrategy
static class ConcreteStrategyA implements IStrategy {

    @Override
    public void algorithm() {
        System.out.println("Strategy A");
    }
}

//具体策略类 ConcreteStrategy
static class ConcreteStrategyB implements IStrategy {

    @Override
    public void algorithm() {
        System.out.println("Strategy B");
    }
}

//上下文环境
static class Context {
    private IStrategy mStrategy;

    public Context(IStrategy strategy) {
        this.mStrategy = strategy;
    }

    public void algorithm() {
        this.mStrategy.algorithm();
    }
}

    public static void main(String[] args) {
        //选择一个具体策略
        IStrategy strategy = new ConcreteStrategyA();
        //来一个上下文环境
        Context context = new Context(strategy);
        //客户端直接让上下文环境执行算法
        context.algorithm();
    }
}
