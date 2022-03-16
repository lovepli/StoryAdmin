package com.story.storyadmin.设计模式.原型模式.demo1;

import java.io.Serializable;

/**
 * @author: lipan
 * @date: 2021/8/23
 * @description:
 */
//用户地址信息
public class UserAddress  implements Serializable {
    private String province;
    private String cityCode;

    public UserAddress(String province, String cityCode) {
        this.province = province;
        this.cityCode = cityCode;
    }
}
