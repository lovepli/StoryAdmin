package com.story.storyadmin.basic.io.serializable;


import java.io.*;

/**
 * @author: lipan
 * @date: 2019-06-09
 * @description:
 *序列化是指把一个Java对象变成二进制内容（byte[]）
 * 反序列化是指把一个二进制内容变成Java对象
 * 可序列化的Java对象必须实现java.io.Serializable接口
 * 反序列化由JVM直接构造出Java对象，不用调用构造方法
 * 可设置serialversionUID作为版本号 （非必需）
 *
 * java的序列化机制仅适用于Java，如果需要与其他语言交换数据，必须使用通用的序列化方法，例如JSON
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String dataFile = "save.data";  //定义存储的文件名
        //序列化
        try (ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {
            //依次写入int String Person
            output.writeInt(999);
            output.writeUTF("Hello,world");
            output.writeObject(new Person("xiao Ming"));
        }
        System.out.println("Reading...");
       //反序列化
        try (ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
           //依次读出 int String Person
            System.out.println(input.readInt());
            System.out.println(input.readUTF());
            Person p = (Person) input.readObject();
            System.out.println(p);
        }
    }
}
