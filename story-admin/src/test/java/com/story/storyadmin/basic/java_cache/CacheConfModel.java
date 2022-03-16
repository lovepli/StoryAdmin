package com.story.storyadmin.basic.java_cache;

/**
 * @author: lipan
 * @date: 2019-11-20
 * @description:
 */
public class CacheConfModel implements java.io.Serializable{

    private long beginTime;//缓存开始时间
    private boolean isForever = false;
    private int durableTime;//持续时间

    public long getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }
    public boolean isForever() {
        return isForever;
    }
    public void setForever(boolean isForever) {
        this.isForever = isForever;
    }
    public int getDurableTime() {
        return durableTime;
    }
    public void setDurableTime(int durableTime) {
        this.durableTime = durableTime;
    }

}
