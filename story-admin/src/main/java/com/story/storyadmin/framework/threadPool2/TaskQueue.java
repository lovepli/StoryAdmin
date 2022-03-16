package com.story.storyadmin.framework.threadPool2;


/**
 * @Author Nxy
 * @Date 2020/3/14 14:23
 * @Description 任务队列
 */
public interface TaskQueue {

    //新任务追加到队列结尾
    void putTask(Runnable runnable);

    //获取任务,该方法是阻塞的，应当向上抛出 InterruptException 使调用方做出阻塞期间对 interrupt 信号的响应
    Runnable getTask() throws InterruptedException;

    //获取当前任务数
    int getSize();
}
