package com.story.storyadmin.designPatterns.payService8;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class StrategyService8 {

    private static double getResult(long money, int type) {

        if (money < 1000) {
            return money;
        }

        Strategy8 strategy = StrategyFactory.getInstance().get(type);

        if (strategy == null){
            throw new IllegalArgumentException("please input right type");
        }

        return strategy.compute(money);
    }
}
