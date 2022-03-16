package com.story.storyadmin.设计模式.责任链模式;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 请假流程的代码实现
 * 下面我们来对，前面的案例：OA上请假流程做一个Java代码的实现。
 *
 * 抽象处理者：领导
 */
public abstract class Leader {
    private Leader next;
    public void setNext(Leader next) {
        this.next = next;
    }
    public Leader getNext() {
        return next;
    }
    //处理请求的方法
    public abstract void handleRequest(double LeaveDays);
}
