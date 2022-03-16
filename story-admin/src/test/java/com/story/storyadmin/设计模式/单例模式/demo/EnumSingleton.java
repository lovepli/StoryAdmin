package com.story.storyadmin.设计模式.单例模式.demo;

import java.lang.reflect.Constructor;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 枚举式的单例模式，《Effective Java》作者力荐，线程安全，不用怕被暴力反射破解。
 */
public enum EnumSingleton {
    INSTANCE;
    private Object data;

    public Object getData() {
        return data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

    //我们把上面反射的那个代码，来测试这个枚举式单例模式。
    public static void main(String[] args) {
        try {
            Class<?> clazz = EnumSingleton.class;
            Constructor constructor = clazz.getDeclaredConstructor(null);
            //强行访问
            constructor.setAccessible(true);
            Object object = constructor.newInstance();

            Object object1 = EnumSingleton.getInstance();

            System.out.println(object == object1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
