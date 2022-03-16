package com.story.storyadmin.设计模式.原型模式.demo2;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description: 深克隆
 * 关于深克隆，我们来用一个很经典的案例，西游记里的孙悟空。一个孙悟空能变成n多个孙悟空，手里都会拿着一个金箍棒。
 *
 * 按照前面的浅克隆，结果就是：孙悟空倒是变成很多孙悟空，但是金箍棒用的是同一根。
 *
 * 深克隆的结果是：孙悟空变成了很多个，金箍棒也变成很多个根。
 */
//猴子，有身高体重和生日
public class Monkey {
    public int height;
    public int weight;
    public Date birthday;
}
