package com.story.storyadmin.framework.classLoader3;


import java.io.*;
import java.lang.reflect.Field;


/**
 * 带你手写自定义类加载器
 * https://mp.weixin.qq.com/s/QpETXnI44yu-gMRxTVofaA
 */
public class MyClassLoader  extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes=null;
        //将点替换成斜杠
        String fileName=name.replaceAll("\\.","/");
        StringBuilder sb=new StringBuilder("E:");
        sb.append(File.separator);
        sb.append("upload");
        sb.append(File.separator);
        sb.append(fileName);
        sb.append(".class");
        fileName=sb.toString();
        try {
            InputStream is=new FileInputStream(fileName);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            int r=0;
            while ((r=is.read(buf))!=-1){
                bos.write(buf,0,r);
            }
            bytes=bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name,bytes,0,bytes.length);
    }

    public static void main(String[] args) throws Exception{
        //自定义类加载器对象1
        MyClassLoader c1=new MyClassLoader();
        String className="a.BB";
         //loadClass调用的就是findClass()
        Class clazz1=c1.loadClass(className);

        //自定义类加载器对象2
        MyClassLoader c2=new MyClassLoader();
        Class clazz2=c2.loadClass(className);

        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz2.getClassLoader());
    }
}