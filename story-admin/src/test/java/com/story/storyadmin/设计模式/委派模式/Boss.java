package com.story.storyadmin.设计模式.委派模式;

/**
 * @author: lipan
 * @date: 2021/8/22
 * @description: 有了开发同事、项目经理，那还得有Boss。
 */
public class Boss {
    //Boss也得根据每个项目经理锁负责的领域进行任务分配
    public void command(String command, Leader leader) {
        leader.doing(command);
    }
}
