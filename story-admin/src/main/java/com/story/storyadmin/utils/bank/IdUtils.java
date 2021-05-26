package com.story.storyadmin.utils.bank;

import java.util.UUID;

/**
 * @author: 59688
 * @date: 2021/5/24
 * @description:
 */
public class IdUtils {

    /**
     * 全局唯一ID
     * 自定义生成id(随机字符串 + 时间戳创建组合ID)
     * @return
     */
    public static  String getRandomId(){
        //获得系统时间时间戳（long装载的时间戳十进制最大有19位）
        long currentMill = System.currentTimeMillis();
        StringBuilder randomId = new StringBuilder(UUID.randomUUID().toString().replaceAll("-","").substring(0,20));
        randomId.append(currentMill);
        return randomId.toString();
    }
}
