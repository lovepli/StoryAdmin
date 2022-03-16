package com.story.storyadmin.basic.thread.concurrentThread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: lipan
 * @date: 2019-06-11
 * @description:
 *
 * Future表示一个未来可能会返回的结果 也就是可以获得异步执行的结果
 * 提交Callable任务，可以获得一个Future对象
 * 可以用Future在将来某个时刻获取结果
 *
 * Runnable vs Callable :
 * 如果线程不需要返回结果：提交Runnable任务
 * 如果线程需要返回结果：提交Callable任务
 */



class DownloadTask implements Callable<String> {  //Callable
    String url;

    public DownloadTask(String url) {
        this.url = url;
    }

    public String call() throws Exception {
        System.out.println("Start download " + url + "...");
        URLConnection conn = new URL(this.url).openConnection();
        conn.connect();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            String s = null;
            StringBuilder sb = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                sb.append(s).append("\n");
            }
            return sb.toString();
        }
    }
}

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        DownloadTask task = new DownloadTask("http://www.sina.com.cn/");
        Future<String> future = executor.submit(task);
        String html = future.get();
        System.out.println(html);
        executor.shutdown();
    }
}
