package com.story.storyadmin.designPatterns.adverService;

/**
 * @author: lipan
 * @date: 2021/8/17
 * @description:
 */

/**
 * @author : David.liu
 * @description : 得到指定广告页面
 * @creat :2019-05-26-22:02
 */
public interface GetPointAdvertisePageStrategey {

    /**
     * 得到指定的页面
     * @param itemRecommendParam
     * @param model
     * @return
     */
    String getAdvertisePage();

    default String test(){
        return "hello world";
    }
}