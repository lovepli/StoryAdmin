package com.story.storyadmin.Interceptor.demo2;

/**
 * @author: lipan
 * @date: 12:40 上午
 * @description:
 */
public abstract class BasePrefix implements KeyPrefix {
    private int expireScconds;
    private String prefix;

    public BasePrefix(String prefix) {
        this(0,prefix); //0表示永不过期
    }

    public BasePrefix(int expireScconds, String prefix) {
        this.expireScconds = expireScconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireScconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className +":"+ prefix;
    }
}

