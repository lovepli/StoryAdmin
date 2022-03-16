package com.story.storyadmin.designPatterns.adverService;

import com.story.storyadmin.designPatterns.adutils.PropertyUtil2;
import com.story.storyadmin.utils.ruoyiutils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author : David.liu
 * @description : 生产广告页面，反射工厂类
 * @creat :2019-05-26-22:15
 */
@Component
public class GetPointAdvertisePageReflectFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetPointAdvertisePageReflectFactory.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PropertyUtil2 propertyUtil2;

    // 通过配置文件名：filePathName   和 文件中的key 得到指定的类
    public GetPointAdvertisePageStrategey getAdvertisePageStrategey(String filePathName, String key){
        GetPointAdvertisePageStrategey getPointAdvertisePageStrategey = null;
        String classPath = null;
        try {
           //classPath = PropertyUtil.get(filePathName,key);
            // 通过配置文件中的key 得到指定的类的全路径
           classPath = propertyUtil2.get(key);
            //if(Validator.isNullOrEmpty(classPath)) return null;
            if(StringUtils.isEmpty(classPath) || classPath == null){
                return null;
            }
            //通过反射创建对象
            Class<?> handler = Class.forName(classPath);
            //这里必须强行纳入spring管理，否则在得到的具体实现类中，如果有通过@Autowired注入的bean，将会报注入失败
            getPointAdvertisePageStrategey = (GetPointAdvertisePageStrategey) context.getAutowireCapableBeanFactory().createBean(handler, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
            System.out.println("打印接口的默认方法："+getPointAdvertisePageStrategey.test());
        } catch (Exception e) {
            LOGGER.error("Failed to reflect the corresponding object through the specified path,key:{}",key);
            return null;
        }
        return getPointAdvertisePageStrategey;
    }

}