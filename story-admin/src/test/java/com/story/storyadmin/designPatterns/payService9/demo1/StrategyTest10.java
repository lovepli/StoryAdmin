package com.story.storyadmin.designPatterns.payService9.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 枚举的策略优化模式
 */
@Slf4j
public class StrategyTest10 {

        public static void main(String[] args) {
            try {
                //简单使用示例
                String param = String.valueOf(Strategy10.WOMAN);
                Strategy10 strategy = Strategy10.valueOf(param);
                strategy.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


}
