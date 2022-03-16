package com.story.storyadmin.设计模式.原型模式.demo2;

import java.io.Serializable;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 孙悟空也是猴子，兵器 孙悟空有个金箍棒：
 */
//孙悟空的金箍棒
public class JinGuBang implements Serializable {
    public float  h=100;
    public float  d=10;
    //金箍棒变大
    public void big(){
        this.h *=10;
        this.d *=10;
    }
    //金箍棒变小
    public void small(){
        this.h /=10;
        this.d /=10;
    }
}
