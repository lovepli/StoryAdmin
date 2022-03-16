package com.story.storyadmin.designPatterns.adverService.impl;


import com.story.storyadmin.designPatterns.adverService.GetPointAdvertisePageStrategey;
import org.springframework.stereotype.Component;

/**
 * @author : David.liu
 * @description : 得到pdp广告页面
 * @creat :2019-05-26-22:05
 */
@Component
public class PdpAdvertisePageStrategey implements GetPointAdvertisePageStrategey {

    /**
     * 得到指定的页面
     *
     * @param itemRecommendParam
     * @param model
     * @return
     */
    @Override
    public String getAdvertisePage() {
        return "pdp-advertise.jsp";
    }
}