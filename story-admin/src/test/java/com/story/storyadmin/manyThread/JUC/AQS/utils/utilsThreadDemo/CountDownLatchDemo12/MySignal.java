package com.story.storyadmin.manyThread.JUC.AQS.utils.utilsThreadDemo.CountDownLatchDemo12;

/**
 * @author: 59688
 * @date: 2021/7/29
 * @description:
 */
public class MySignal {

    //共享的变量
    private boolean hasDataToProcess=false;
    //取值
    public boolean getHasDataToProcess() {
        return hasDataToProcess;
    }
    //存值
    public void setHasDataToProcess(boolean hasDataToProcess) {
        this.hasDataToProcess = hasDataToProcess;
    }
}
