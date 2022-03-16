package com.story.storyadmin.manyThread.CallableDemo.CallableDemo3;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author: 59688
 * @date: 2021/7/27
 * @description:
 */
public class CallableTask implements Callable<String> {

    private String taskNum;

    public CallableTask(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String call() throws Exception {
        System.out.println("-----线程2-----" + Thread.currentThread().getName());
        System.out.println(">>>" + taskNum + "任务启动");

        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】 ";
    }

}
