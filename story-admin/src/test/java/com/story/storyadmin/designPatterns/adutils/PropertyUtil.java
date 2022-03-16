package com.story.storyadmin.designPatterns.adutils;

//import com.feilong.core.Validator;
import com.story.storyadmin.utils.ruoyiutils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 文件读取工具类
 *  获取配置文件参数工具类
 *  拥有缓存功能，当查询了某个文件时会自动缓存
 *  @author wei.liu5
 *  @Date 2019/5/20
 */

public class PropertyUtil {

    private static Map<String, Properties> cache = new HashMap<>();

    /**
     *
     * @param configFileName 配置文件名
     * @param key  配置文件中的 key=value  中的key
     * @return
     */
    public static String get(String configFileName,String key){
        return getProperties(configFileName).getProperty(key);
    }

    public static Properties getProperties(String configFileName) {
        //if (Validator.isNotNullOrEmpty(cache.get(configFileName))) {
          if(StringUtils.isEmpty(cache.get(configFileName)) || cache.get(configFileName) == null){
            return cache.get(configFileName);
        }
        InputStream inputStream = PropertyUtil.class.getResourceAsStream(configFileName);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cache.put(configFileName,properties);
        return  properties;
    }

}
