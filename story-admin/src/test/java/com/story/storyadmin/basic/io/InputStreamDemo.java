package com.story.storyadmin.basic.io;

import java.io.*;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:   byte[]字节流 InputStream  OutputStream --> FileInputStream  FileOutputStream
 *                 char[]字符流 Reader  Writer  --> FileReader  FileWriter
 *
 */

public class InputStreamDemo {

    public static void main(String[] args) throws IOException {



        try (InputStream input = new FileInputStream("readme.txt")) {  //文件路径不正确！！！
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);  //7个字符 输出了5+2*3=11个字节 中文占3个字节
            }

            //改进
            int k;
            byte[] buffer = new byte[1000];
            while ((k = input.read(buffer)) != -1) {
                System.out.println(n + "bytes read.");  //11 bytes read.
            }
        }
    }
}
