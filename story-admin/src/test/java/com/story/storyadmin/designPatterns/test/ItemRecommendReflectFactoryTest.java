package com.story.storyadmin.designPatterns.test;


import com.story.storyadmin.designPatterns.adverService.GetPointAdvertisePageReflectFactory;
import com.story.storyadmin.designPatterns.adverService.GetPointAdvertisePageSimpleFactory;
import com.story.storyadmin.designPatterns.adverService.GetPointAdvertisePageStrategey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


/**
 * 测试类：测试代码用的是 策略模式+反射工厂，这里未使用简单工厂，但代码也贴出来了
 */
@ExtendWith(SpringExtension.class) //导入spring测试框架[2]
@SpringBootTest  //提供spring依赖注入
@Transactional  //事务管理，默认回滚,如果配置了多数据源记得指定事务管理器
@DisplayName("策略模式+工厂模式使用")
public class ItemRecommendReflectFactoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRecommendReflectFactoryTest.class);

    @Autowired
    private GetPointAdvertisePageReflectFactory getItemRecommendStrategey;

    @Autowired
    private GetPointAdvertisePageSimpleFactory getPointAdvertisePageSimpleFactory;

    @Test
    @DisplayName("简单工厂单元测试")
    public void  simpleFactoryTest(){
        GetPointAdvertisePageStrategey getPointAdvertisePageStrategey =getPointAdvertisePageSimpleFactory.getStrategey("PDP_ITEM_ADVERTISE");
        if(getPointAdvertisePageStrategey == null) return;
            System.out.println("执行方法："+getPointAdvertisePageStrategey.getAdvertisePage());
    }

    @Test
    @DisplayName("反射工厂单元测试")
    public void  refectFactoryTest(){
        LOGGER.info("通过配置文件和反射机制，在运行时动态获取指定的执行类");
        String path = "itemrecommend.properties";
        GetPointAdvertisePageStrategey getPointAdvertisePageStrategey = getItemRecommendStrategey.getAdvertisePageStrategey(path,"plp.item.advertise");
        if(getPointAdvertisePageStrategey == null) return;
        System.out.println("通过配置文件和反射机制，在运行时动态获取指定的执行类，测试成功");
        System.out.println("执行方法："+getPointAdvertisePageStrategey.getAdvertisePage());
    }
}
