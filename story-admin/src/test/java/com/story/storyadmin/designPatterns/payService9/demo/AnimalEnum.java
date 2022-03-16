package com.story.storyadmin.designPatterns.payService9.demo;

/**
 * @author: lipan
 * @date: 2021/8/20
 * @description: 使用枚举消除 if/else。
 */
public enum AnimalEnum implements Common {
    PANDA {
        @Override
        public String eat() {
            return "吃竹子";
        }
    },
    CAT {
        @Override
        public String eat() {
            return "吃鱼";
        }
    },
    MONKEY{
        @Override
        public String eat() {
            return "吃香蕉";
        }
    }
}
