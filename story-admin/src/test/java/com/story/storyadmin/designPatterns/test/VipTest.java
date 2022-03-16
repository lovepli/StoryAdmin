package com.story.storyadmin.designPatterns.test;

import com.story.storyadmin.designPatterns.vipService.VipRechargeFactory;
import com.story.storyadmin.designPatterns.vipService.VipRechargeStrategy;
import com.story.storyadmin.designPatterns.vipService.impl.OneMonthVipStrategy;
import com.story.storyadmin.designPatterns.vipService.impl.ThreeMonthVipStrategy;
import com.story.storyadmin.designPatterns.vipService.impl.YearVipStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author: lipan
 * @date: 2021/8/17
 * @description: 干掉 if-else 语句的 3 种方式，总有一种适合你！ https://mp.weixin.qq.com/s/eFkIl_HgzepMiH95ftxeow
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("策略模式+工厂模式使用")
public class VipTest {

    /**
     * 表面上看，代码稍微优雅了点，但是还是没和if-else彻底说拜拜，且recharge()充值方法可单独拎出来，只需要根据priceCode实例化不同的策略对象即可
     * @param
     */
    @Test
    @DisplayName("单元测试1")
    public void payNotify(){
        String priceCode ="充值一个月会员";
        VipRechargeStrategy strategy;
        if ("充值一个月会员".equals(priceCode)){
            strategy =new OneMonthVipStrategy();
        }else if ("充值三个月会员".equals(priceCode)){
            strategy = new ThreeMonthVipStrategy();
        }else {
            strategy = new YearVipStrategy();
        }
        strategy.recharge(priceCode);
    }

    /**
     * 策略模式+工厂+单例模式
     * @param
     */
    @Test
    @DisplayName("单元测试2")
    public void payNotify2(){
        String priceCode ="充值三个月会员";
        VipRechargeStrategy strategy= VipRechargeFactory.getInstance().getConcreteStrategy(priceCode);
        if (strategy == null){ //策略不符合及时中断
            return;
        }
        strategy.recharge(priceCode);
    }

}
