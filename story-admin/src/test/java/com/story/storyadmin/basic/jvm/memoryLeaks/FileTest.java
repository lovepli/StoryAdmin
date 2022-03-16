package com.story.storyadmin.basic.jvm.memoryLeaks;

import java.io.File;
import java.io.IOException;

/**
 * @author: 59688
 * @date: 2021/9/29
 * @description: 2、连接资源未关闭
 * 每当建立一个连接，jvm就会为这么资源分配内存。比如数据库连接、文件输入输出流、网络连接等等。
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
        File f=new File("G:\\nginx配套资料\\笔记资料.zip");
        System.out.println(f.exists());
        System.out.println(f.isDirectory());
    }
}
