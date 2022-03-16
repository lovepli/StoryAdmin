package com.story.storyadmin.utils.objectMethordUtil;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: lipan
 * @date: 11:18 下午
 * @description:
 * 1、使用BeanUtils.copyProperties进行对象之间的属性赋值
 * 2、BeanUtils.copyProperties不支持复制集合的解决方案
 * https://mp.weixin.qq.com/s/rnlIZdz_YEKVV2zO7ezVfA
 *
 * 1、使用org.springframework.beans.BeanUtils.copyProperties方法进行对象之间属性的赋值，
 * 避免通过get、set方法一个一个属性的赋值
 * 2、List集合之间的对象属性赋值
 */
@Slf4j
public class JavaBeanUtils {

    /**
     * 对象属性拷贝 <br>
     * 将源对象的属性拷贝到目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        try {
            BeanUtils.copyProperties(source, target);
        } catch (BeansException e) {
            log.error("BeanUtil property copy  failed :BeansException", e);
        } catch (Exception e) {
            log.error("BeanUtil property copy failed:Exception", e);
        }
    }


    /**
     * List集合之间的对象属性赋值
     * @param input 输入集合
     * @param clzz  输出集合类型
     * @param <E>   输入集合类型
     * @param <T>   输出集合类型
     * @return 返回集合
     */
    public static <E, T> List<T> convertList2List(List<E> input, Class<T> clzz) {
        List<T> output = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(input)) {
            for (E source : input) {
                T target = BeanUtils.instantiate(clzz);
                BeanUtil.copyProperties(source, target);
                output.add(target);
            }
        }
        return output;
    }


    /**
     * BeanUtils.copyProperties不支持复制集合的解决方案：
     * 通过以下方式解决复制List、Map
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List copyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
    }

    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }
}
