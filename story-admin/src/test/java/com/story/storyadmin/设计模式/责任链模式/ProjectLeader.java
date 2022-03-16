package com.story.storyadmin.设计模式.责任链模式;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 项目负责人
 */
public class ProjectLeader extends Leader {
    @Override
    public void handleRequest(double LeaveDays) {
        if (LeaveDays <= 0.5) {
            System.out.println("项目负责人批准您请假" + LeaveDays + "天。");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(LeaveDays);
            } else {
                System.out.println("请假天数太多，没有人批准该假条！");
            }
        }
    }
}
