package com.story.storyadmin.designPrinciples.dependenceInversionPrinciple.extendChange;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 * 随着学习兴趣的暴涨，老田还想学习AI课程。
 *
 * 这个时候，需要业务扩展，代码要从底层到高层（调用层）一次修改代码。
 * 在Tian类中增加studyAICourse()的方法，在高层也要追加调用。如此一来，在系统发布以后，实际上是非常不稳定的
 * ，在修改代码的同时会带来意想不到的风险。因此我们优化代码，首先创建一个课程的抽象接口ICourse。
 */
public interface ICourse {
    void study();
}
