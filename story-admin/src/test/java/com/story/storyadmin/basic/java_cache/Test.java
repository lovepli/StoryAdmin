package com.story.storyadmin.basic.java_cache;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2019-11-20
 * @description:
 */
public class Test {

    public  static  void main(String[] args) {
        addData();//添加缓存
        int num=getData();//取缓存个数
        System.out.println(num);
    }


    public static synchronized void addData(){
        System.out.println("进入getData()…………………………");
        CacheMgr cm=CacheMgr.getInstance();
        CacheConfModel cModel=new CacheConfModel();
        Date d=new Date();
        cModel.setBeginTime(d.getTime());
        cModel.setDurableTime(6000);
        cModel.setForever(true);
        cm.addCache("kk", "123", cModel);
    }

    public static synchronized int getData(){
        CacheMgr cm=CacheMgr.getInstance();
        int num=cm.getSize();
        System.out.println("num====="+num);
        return num;
    }



}
