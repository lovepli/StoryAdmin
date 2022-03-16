package com.story.storyadmin.design.strategy;

/**
 * Description:
 *
 * @author Lvshen
 * @version 1.0
 * @date: 2020-10-16 17:24
 * @since JDK 1.8
 */
public class WalkStrategy implements How2WorkStrategy{
    @Override
    public String how2WorkFun() {
        return "步行";
    }
}
