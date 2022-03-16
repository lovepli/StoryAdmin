package com.story.storyadmin.designPatterns.adverService;

//import com.feilong.core.Validator;
import com.story.storyadmin.designPatterns.adverService.impl.CheckoutAdvertisePageStrategey;
import com.story.storyadmin.designPatterns.adverService.impl.PdpAdvertisePageStrategey;
import com.story.storyadmin.designPatterns.adverService.impl.PlpAdvertisePageStrategey;
import com.story.storyadmin.designPatterns.adverService.impl.ShopingCartAdvertisePageStrategey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : David.liu
 * @description : 生产广告页面,简单工厂类
 * @creat :2019-05-26-22:14
 *
 * 生产广告页面。简单工厂类 GetPointAdvertisePageReflectFactory.java，案例未使用，但代码贴出来了，
 * 供读者对比两个的差异，感受一下，开闭原则，对比一下，简单工厂和反射工厂的不同。
 *
 * 思考角度，如果需求在加一个策略类，2个、3个？那么你就感受到了
 */
@Component
public class GetPointAdvertisePageSimpleFactory {
    public static final String PDP_ITEM_ADVERTISE = "PDP_ITEM_ADVERTISE";
    public static final String PLP_ITEM_ADVERTISE = "PLP_ITEM_ADVERTISE";
    public static final String CHECKOUT_ITEM_ADVERTISE = "CHECKOUT_ITEM_ADVERTISE";
    public static final String SHOPPINGCART_ITEM_ADVERTISE = "SHOPPINGCART_ITEM_ADVERTISE";

    private static Map<String, GetPointAdvertisePageStrategey> recommendStrategyMap = new HashMap<>();

    @Autowired
    private CheckoutAdvertisePageStrategey checkoutAdvertisePageStrategey;
    @Autowired
    private PdpAdvertisePageStrategey pdpAdvertisePageStrategey;
    @Autowired
    private PlpAdvertisePageStrategey plpAdvertisePageStrategey;
    @Autowired
    private ShopingCartAdvertisePageStrategey shopingCartAdvertisePageStrategey;

    /** 初始化所有的策略类 */
    protected void init(){
        recommendStrategyMap.put(PDP_ITEM_ADVERTISE, pdpAdvertisePageStrategey);
        recommendStrategyMap.put(PLP_ITEM_ADVERTISE,plpAdvertisePageStrategey );
        recommendStrategyMap.put(CHECKOUT_ITEM_ADVERTISE, checkoutAdvertisePageStrategey);
        recommendStrategyMap.put(SHOPPINGCART_ITEM_ADVERTISE,shopingCartAdvertisePageStrategey );
    }

    /** 根据pageType 得到指定的处理类 */
    public GetPointAdvertisePageStrategey getStrategey(String pageType) {
        if(recommendStrategyMap.isEmpty()){
            init();
        }
        return recommendStrategyMap.get(pageType);
    }
}