package com.story.storyadmin.copybean.copyBean5;

import java.io.*;
import java.util.List;

/**
 * @author: 59688
 * @date: 2021/8/11
 * @description: List复制：深拷贝和浅拷贝用法及区别 https://mp.weixin.qq.com/s/zI3MQLsD3miL13-zWJ7TOQ
 * 几种浅拷贝
 * 1、遍历循环复制
 * List<Person> destList=new ArrayList<Person>(srcList.size());
 * for(Person p : srcList){
 *     destList.add(p);
 * }
 * 2、使用List实现类的构造方法
 * List<Person> destList=new ArrayList<Person>(srcList);
 *
 * 3、使用list.addAll()方法
 * List<Person> destList=new ArrayList<Person>();
 * destList.addAll(srcList);
 *
 * 4、使用System.arraycopy()方法
 * Person[] srcPersons=srcList.toArray(new Person[0]);
 * Person[] destPersons=new Person[srcPersons.length];
 * System.arraycopy(srcPersons, 0, destPersons, 0, srcPersons.length);
 *
 * 深拷贝的方法
 * 1.使用序列化方法
 * 2.clone方法
 */
public class CopyList {

     // 使用序列化方法
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    public static void main(String[] args) {
        //List<Person> destList=deepCopy(srcList);  //调用该方法
    }
}

//clone方法
class A implements Cloneable {
    public String name[];

    public A(){
        name=new String[2];
    }

    public Object clone() {
        A o = null;
        try {
            o = (A) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void main(String[] args) {

    }
}