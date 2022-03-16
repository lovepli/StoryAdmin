package com.story.storyadmin.Interceptor.demo2;

/**
 * @author: 59688
 * @date: 2021/8/2
 * @description:
 */
public class AccessKey extends BasePrefix {

    private AccessKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }

    public static AccessKey access = new AccessKey(5,"access");
}

