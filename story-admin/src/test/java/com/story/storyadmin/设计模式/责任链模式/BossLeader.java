package com.story.storyadmin.设计模式.责任链模式;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: Boss
 */
public class BossLeader extends Leader {
    @Override
    public void handleRequest(double LeaveDays) {
        if (LeaveDays >= 2 && LeaveDays <= 30) {
            System.out.println("Boss批准您请假" + LeaveDays + "天。");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(LeaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}
