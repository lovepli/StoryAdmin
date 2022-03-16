package com.story.storyadmin.myDatastucture.threadLocal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 59688
 * @date: 2021/9/26
 * @description:
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> map = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T newValue) {
        map.put(Thread.currentThread(), newValue);
    }

    public T get() {
        return map.get(Thread.currentThread());
    }

    public void remove() {
        map.remove(Thread.currentThread());
    }
}