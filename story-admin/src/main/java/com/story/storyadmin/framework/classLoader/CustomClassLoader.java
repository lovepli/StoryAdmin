package com.story.storyadmin.framework.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 如何自己手写一个热加载 https://www.jianshu.com/p/d8fa14802b7a
 *
 * 实现自己的类加载器
 * 要想实现自己的类加载器，只需要继承ClassLoader类即可。而我们要打破双亲委派规则，那么我们就必须要重写loadClass方法，因为默认情况下loadClass方法是遵循双亲委派的规则的。
 *
 * 为什么要先获取ExtClassLoader类加载器呢？其实这里是借鉴了Tomcat里面的设计，是为了避免我们自定义的类加载器覆盖了一些核心类。例如java.lang.Object。
 *
 * 为什么是获取ExtClassLoader类加载器而不是获取AppClassLoader呢？这是因为如果我们获取了AppClassLoader进行加载，那么不还是双亲委派的规则了吗？Java类加载机制，这篇推荐看下。
 */
public class CustomClassLoader extends ClassLoader{

    private static final String CLASS_FILE_SUFFIX = ".class";

    //AppClassLoader的父类加载器
    private ClassLoader extClassLoader;

    public CustomClassLoader(){
        ClassLoader j = String.class.getClassLoader();
        if (j == null) {
            j = getSystemClassLoader();
            while (j.getParent() != null) {
                j = j.getParent();
            }
        }
        this.extClassLoader = j ;
    }

    protected Class<?> loadClass(String name, boolean resolve){

        Class cls = null;
        cls = findLoadedClass(name);
        if (cls != null){
            return cls;
        }
        //获取ExtClassLoader
        ClassLoader extClassLoader = getExtClassLoader() ;
        //确保自定义的类不会覆盖Java的核心类
        try {
            cls = extClassLoader.loadClass(name);
            if (cls != null){
                return cls;
            }
        }catch (ClassNotFoundException e ){

        }
        cls = findClass(name);
        return cls;
    }

    //TODO 改进
    protected Class<?> loadClass2(String name, boolean resolve){

        Class cls = null;
        cls = findLoadedClass(name);
        if (cls != null){
            return cls;
        }
        if ("com.example.watchfile.ITest".equals(name)){
            try {
                cls = getSystemClassLoader().loadClass(name);
            } catch (ClassNotFoundException e) {

            }
            return cls;
        }
        //获取ExtClassLoader
        ClassLoader extClassLoader = getExtClassLoader() ;
        //确保自定义的类不会覆盖Java的核心类
        try {
            cls = extClassLoader.loadClass(name);
            if (cls != null){
                return cls;
            }
        }catch (ClassNotFoundException e ){

        }
        cls = findClass(name);
        return cls;
    }

    @Override
    public Class<?> findClass(String name) {
        byte[] bt = loadClassData(name);
        return defineClass(name, bt, 0, bt.length);
    }

    private byte[] loadClassData(String className) {
        // 读取Class文件呢
        InputStream is = getClass().getClassLoader().getResourceAsStream(className.replace(".", "/")+CLASS_FILE_SUFFIX);
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        // 写入byteStream
        int len =0;
        try {
            while((len=is.read())!=-1){
                byteSt.write(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 转换为数组
        return byteSt.toByteArray();
    }

    public ClassLoader getExtClassLoader(){
        return extClassLoader;
    }
}
