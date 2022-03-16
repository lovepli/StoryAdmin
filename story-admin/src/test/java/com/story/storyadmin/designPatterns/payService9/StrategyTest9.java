package com.story.storyadmin.designPatterns.payService9;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description:
 */
@Slf4j
public class StrategyTest9 {

    public static void main(String[] args) {
        //使用简单示例 使用函数式编程进行代码的操作。
        HashMap<String, Strategy9> map = new HashMap<>();
        map.put("man", () -> log.debug("执行男人相关的逻辑..."));
        map.put("woman", () -> log.debug("执行女人相关的逻辑..."));
        map.put("other", () -> log.debug("执行其他人相关的逻辑..."));

        try {
            map.get("woman").run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
