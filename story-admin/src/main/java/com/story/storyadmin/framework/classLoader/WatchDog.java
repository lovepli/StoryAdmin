package com.story.storyadmin.framework.classLoader;

/**
 * @author: 59688
 * @date: 2021/10/14
 * @description: 接下来我们建立周期性执行的任务类。
 */

import com.story.storyadmin.framework.classLoader.watchfile.ITest;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

public class WatchDog implements Runnable{

    private Map<String,FileDefine> fileDefineMap;

    public WatchDog(Map<String,FileDefine> fileDefineMap){
        this.fileDefineMap = fileDefineMap;
    }

    @Override
    public void run() {
        File file = new File(FileDefine.WATCH_PACKAGE);
        File[] files = file.listFiles();
        for (File watchFile : files){
            long newTime = watchFile.lastModified();
            FileDefine fileDefine = fileDefineMap.get(watchFile.getName());
            long oldTime = fileDefine.getLastDefine();
            //如果文件被修改了,那么重新生成累加载器加载新文件
            if (newTime!=oldTime){
                fileDefine.setLastDefine(newTime);
                loadMyClass();
            }
        }
    }

    public void loadMyClass(){
        try {
            CustomClassLoader customClassLoader = new CustomClassLoader();
            Class<?> cls = customClassLoader.loadClass("com.story.storyadmin.framework.classLoader.watchfile.Test",false);
            Object test = cls.newInstance();
            Method method = cls.getMethod("test");
            method.invoke(test);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //TODO 改进
    public void loadMyClass2(){
        try {
            CustomClassLoader customClassLoader = new CustomClassLoader();
            Class<?> cls = customClassLoader.loadClass("com.example.watchfile.Test",false);
            ITest test = (ITest) cls.newInstance();
            test.test();
//            Method method = cls.getMethod("test");
//            method.invoke(test);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}