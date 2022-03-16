package com.story.storyadmin.myDatastucture.threadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author: 59688
 * @date: 2021/9/26
 * @description:
 */
public class MyThreadLocal2<T> {

    private Map<Thread,T> map = new ConcurrentHashMap<Thread, T>();

    protected T initialValue(){
        return null;
    }

    public void set(T value){
        map.put(Thread.currentThread(), value);
    }

    public T get(){
        T result = null;
        result = map.get(Thread.currentThread());
        if(result==null && !map.containsKey(Thread.currentThread())){
            result=initialValue();
            set(result);
        }
        return result;
    }

    public void remove(){
        map.remove(Thread.currentThread());
    }
}