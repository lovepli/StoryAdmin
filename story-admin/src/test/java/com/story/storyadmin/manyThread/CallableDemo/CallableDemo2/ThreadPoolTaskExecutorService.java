package com.story.storyadmin.manyThread.CallableDemo.CallableDemo2;


/**
 * @author: 59688
 * @date: 2021/7/23
 * @description:
 */
public interface ThreadPoolTaskExecutorService {

    public String submit(String param);
    public String execute(String param);

    public String submit2(String param);
    public String execute2(String param);


}
