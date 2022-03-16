package com.story.storyadmin.designPatterns.vipService;

import com.google.common.collect.ImmutableMap;
import com.story.storyadmin.designPatterns.vipService.impl.OneMonthVipStrategy;
import com.story.storyadmin.designPatterns.vipService.impl.ThreeMonthVipStrategy;
import com.story.storyadmin.designPatterns.vipService.impl.YearVipStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/17
 * @description: 使用"工厂类+单例"来给代码加点料:
 */
@Component
public class VipRechargeFactory {

    static Map<String,VipRechargeStrategy> map;

    static {
        map = ImmutableMap.of(
           "充值一个月会员",new OneMonthVipStrategy(),
           "充值三个月会员",new ThreeMonthVipStrategy(),
           "充值一年会员",new YearVipStrategy()
        );
    }

    public static class SingletonHolder{
        public static VipRechargeFactory vipRechargeFactory= new VipRechargeFactory();
    }

    public static VipRechargeFactory getInstance(){
        return  SingletonHolder.vipRechargeFactory;
    }

    public VipRechargeStrategy getConcreteStrategy(String priceCode){
        return map.get(priceCode);
    }
}
