package com.story.storyadmin.设计模式.责任链模式;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 技术总监
 */
public class TechnicalDirectorLeader extends Leader {

    @Override
    public void handleRequest(double LeaveDays) {
        if (LeaveDays <= 1) {
            System.out.println("技术总监批准您请假" + LeaveDays + "天。");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(LeaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}
