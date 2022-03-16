package com.story.storyadmin.designPatterns.payService8;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description: https://mp.weixin.qq.com/s/UyiqGjPb9K5f7XwlfsrXBw
 */
public interface Strategy8 {

    double compute(long money);

    // 返回 type
    int getType();
}
