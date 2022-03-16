package com.story.storyadmin.designPatterns.adverService.impl;
import com.story.storyadmin.designPatterns.adverService.GetPointAdvertisePageStrategey;
import org.springframework.stereotype.Component;

/**
 * @author : David.liu
 * @description : 得到结算页面广告页面
 * @creat :2019-05-26-22:12
 */
@Component
public class CheckoutAdvertisePageStrategey implements GetPointAdvertisePageStrategey {
    /**
     * 得到指定的结算页面
     *
     * @param itemRecommendParam
     * @param model
     * @return
     */
    @Override
    public String getAdvertisePage() {
        return "checkout-advertise.jsp";
    }
}
