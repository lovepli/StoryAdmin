package com.story.storyadmin.designPatterns.payService8;

import com.story.storyadmin.designPatterns.payService8.impl.OrdinaryStrategy;
import com.story.storyadmin.designPatterns.payService8.impl.SilverStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class StrategyFactory {

    private Map<Integer, Strategy8> map;

    public StrategyFactory() {

        List<Strategy8> strategies = new ArrayList<>();

        strategies.add(new OrdinaryStrategy());
        strategies.add(new SilverStrategy());

        // 看这里 看这里 看这里！
        map = strategies.stream().collect(Collectors.toMap(Strategy8::getType, strategy -> strategy));
        //toMap 第一个参数是一个Function，对应 Map 中的 key，第二个参数也是一个Function，strategy -> strategy， 左边strategy 是遍历 strategies 中的每一个strategy，右边strategy则是 Map 对应 value 值。

        /* 等同上面
        map = new HashMap<>();
        for (Strategy strategy : strategies) {
            map.put(strategy.getType(), strategy);
        }*/
    }

    public static class Holder {
        public static StrategyFactory instance = new StrategyFactory();
    }

    public static StrategyFactory getInstance() {
        return Holder.instance;
    }

    public Strategy8 get(Integer type) {
        return map.get(type);
    }
}
