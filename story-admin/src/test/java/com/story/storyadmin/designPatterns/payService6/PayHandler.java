package com.story.storyadmin.designPatterns.payService6;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */

public abstract class PayHandler {

    @Getter
    @Setter
    protected PayHandler next;

    public abstract void pay(String pay);

}
