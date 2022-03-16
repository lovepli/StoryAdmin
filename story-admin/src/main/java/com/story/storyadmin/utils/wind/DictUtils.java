package com.story.storyadmin.utils.wind;

import com.google.common.collect.Lists;
import com.story.storyadmin.service.wind.IDictService;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.sunseagear.com
 *
 * @version V1.0
 * @package com.sunseagear.wind.utils
 * @title:
 * @description: Cache工具类 * @date: 2018/3/22 16:11
 * @copyright: 2017 www.sunseagear.com Inc. All rights reserved.
 */
public class DictUtils {

    public static String WDict_CACHE_KEY = "WDict_CACHE_KEY";
    protected final static String WDict_CACHE_NAME = "WDictCache";

    /**
     * 获取数据字典
     * @return
     */
    public static Map<String, List<WDict>> getDict() {
        //从缓存中查询数据字典
        if (CacheUtils.get(WDict_CACHE_NAME, WDict_CACHE_KEY) == null) {
            //初始化数据字典
            initDict();
        }
        initDict();
        // 存缓存中取出数据字典并返回
        return (Map<String, List<WDict>>) CacheUtils.get(WDict_CACHE_NAME, WDict_CACHE_KEY);
    }

    /**
     * 数据字典初始化
     *
     * @return
     */
    public static Map<String, List<WDict>> initDict() {
        Map<String, List<WDict>> WDictMap = new HashMap<String, List<WDict>>();
        // 查询数据字典列表并遍历
        for (com.story.storyadmin.domain.entity.wind.WDict WDict : SpringContextHolder.getBean(IDictService.class).selectDictList()) {
            List<WDict> WDictList = WDictMap.get(WDict.getCode());
            if (WDictList != null) {
                WDictList.add(new WDict(WDict.getLabel(), WDict.getValue()));
            } else {
                WDictMap.put(WDict.getCode(),
                        Lists.newArrayList(new WDict(WDict.getLabel(), WDict.getValue())));
            }
        }
        // 数据字典列表放入缓存redis中
        putDict(WDictMap);
        return WDictMap;
    }

    /**
     * 获取数据字典列表
     * @param code
     * @return
     */
    public static List<WDict> getDictList(String code) {
        //数据字典
        if (CacheUtils.get(WDict_CACHE_NAME, WDict_CACHE_KEY) == null) {
            // 数据字典初始化
            initDict();
        }
        //从缓存获取数据字典
        Map<String, List<WDict>> WDictMap = (Map<String, List<WDict>>) CacheUtils.get(WDict_CACHE_NAME, WDict_CACHE_KEY);
        List<WDict> WDictList = WDictMap.get(code);
        if (WDictList == null) {
            WDictList = new ArrayList<WDict>();
        }
        return WDictList;
    }

    /**
     * 放入缓存
     *
     * @param WDictMap
     */
    public static void putDict(Map<String, List<WDict>> WDictMap) {
        CacheUtils.put(WDict_CACHE_NAME, WDict_CACHE_KEY, WDictMap);
    }

    public static String getWDictLabel(String code, String value, String defaultValue) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(code) && org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
            for (WDict WDict : getDictList(code)) {
                if (value.equals(WDict.getValue())) {
                    return WDict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static String getDictLabels(String values, String code, String defaultValue) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(code) && org.apache.commons.lang3.StringUtils.isNotBlank(values)) {
            List<String> valueList = new ArrayList<String>();
            for (String value : org.apache.commons.lang3.StringUtils.split(values, ",")) {
                valueList.add(getWDictLabel(value, code, defaultValue));
            }
            return org.apache.commons.lang3.StringUtils.join(valueList, ",");
        }
        return defaultValue;
    }

    public static String getDictValue(String label, String code, String defaultLabel) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(code) && StringUtils.isNotBlank(label)) {
            for (WDict WDict : getDictList(code)) {
                if (label.equals(WDict.getLabel())) {
                    return WDict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    /*
     * 清除缓存
     */
    public static void clear() {
        CacheUtils.remove(WDict_CACHE_NAME, WDict_CACHE_KEY);
    }

    /**
     * 返回字典
     */
    public static class WDict implements Serializable {
        private String label;
        private String value;

        public WDict() {

        }

        public WDict(String label, String value) {
            this.label = label;
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
