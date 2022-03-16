package com.story.storyadmin.designPatterns.adverService.impl;

import com.story.storyadmin.designPatterns.adverService.GetPointAdvertisePageStrategey;
import org.springframework.stereotype.Component;

/**
 * @author : David.liu
 * @description : 得到指定的plp广告页面
 * @creat :2019-05-26-22:07
 */
@Component
public class PlpAdvertisePageStrategey implements GetPointAdvertisePageStrategey {
    /**
     * 得到指定的plp广告页面
     *
     * @param itemRecommendParam
     * @param model
     * @return
     */
    @Override
    public String getAdvertisePage() {
        return "plp-advertise.jsp";
    }
}
