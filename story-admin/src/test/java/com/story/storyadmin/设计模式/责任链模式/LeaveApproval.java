package com.story.storyadmin.设计模式.责任链模式;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 发起审批
 */
public class LeaveApproval {
    public static void main(String[] args) {
        //组装责任链
        Leader projectLeader = new ProjectLeader();
        Leader technicalDirectorLeader = new TechnicalDirectorLeader();
        Leader bossLeader = new BossLeader();

        projectLeader.setNext(technicalDirectorLeader);
        technicalDirectorLeader.setNext(bossLeader);

        //请假两天，提交请假流程，开启审批环节,
        projectLeader.handleRequest(2);
    }
}
