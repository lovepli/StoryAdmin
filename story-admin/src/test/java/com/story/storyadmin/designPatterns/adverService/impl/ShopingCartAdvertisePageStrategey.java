package com.story.storyadmin.designPatterns.adverService.impl;

/**
 * @author: lipan
 * @date: 2021/8/17
 * @description:
 */
import com.story.storyadmin.designPatterns.adverService.GetPointAdvertisePageStrategey;
import org.springframework.stereotype.Component;

/**
 * @author : David.liu
 * @description : 得到购物车广告页面
 * @creat :2019-05-26-22:10
 */
@Component
public class ShopingCartAdvertisePageStrategey implements GetPointAdvertisePageStrategey {
    /**
     * 得到指定的购物车页面
     *
     * @param model
     * @return
     */
    @Override
    public String getAdvertisePage() {
        return "shoppingcart-advertise.jsp";
    }
}