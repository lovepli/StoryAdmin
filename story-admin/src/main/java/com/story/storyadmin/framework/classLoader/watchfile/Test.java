package com.story.storyadmin.framework.classLoader.watchfile;

/**
 * @author: 59688
 * @date: 2021/10/14
 * @description:
 * 监控class文件
 * 这里我们使用ScheduledThreadPoolExecutor来进行周期性的监控文件是否修改。在程序启动的时候记录文件的最后修改时间。随后周期性的查看文件的最后修改时间是否改动。如果改动了那么就重新生成类加载器进行替换。这样新的文件就被加载进内存中了。
 * 首先我们建立一个需要监控的文件：
 * 我们通过在程序运行时修改版本号，来动态的输出版本号。
 */
public class Test {

    public void test(){
        System.out.println("Hello World! Version one");
    }
}