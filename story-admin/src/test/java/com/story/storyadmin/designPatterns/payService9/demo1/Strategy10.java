package com.story.storyadmin.designPatterns.payService9.demo1;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description:
 * 2.3 枚举的策略优化模式
 *
 * 定义一个枚举策略类。
 */
@Slf4j
public enum Strategy10 {

    //男人状态
    MAN(0) {
        @Override
        void run() {
            //执行男人相关操作
            log.debug("执行男人相关的逻辑");
        }
    },
    //女人状态
    WOMAN(1) {
        @Override
        void run() {
            //执行女人相关操作
            log.debug("执行女人相关的逻辑");
        }
    },
    //其他状态
    OTHER(2) {
        @Override
        void run() {
            //执行其他相关操作
            log.debug("执行其他相关的逻辑");
        }
    };

    // 定义抽象方法 TODO 枚举类内部还可以定义抽象方法
    abstract void run();

    //状态码
    public int statusCode;

    Strategy10(int statusCode) {
        this.statusCode = statusCode;
    }

}
