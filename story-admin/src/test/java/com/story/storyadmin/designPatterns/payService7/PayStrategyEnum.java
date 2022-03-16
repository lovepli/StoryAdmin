package com.story.storyadmin.designPatterns.payService7;

import com.story.storyadmin.designPatterns.payService7.impl.AliaPay7;
import com.story.storyadmin.designPatterns.payService7.impl.JingDongPay7;
import com.story.storyadmin.designPatterns.payService7.impl.UnionPay;
import com.story.storyadmin.designPatterns.payService7.impl.WeixinPay7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 */
public enum PayStrategyEnum {

    ALI_PAY("alia"),
    WEIXIN_PAY("weixin"),
    JINGDONG_PAY("jingdong"),
    UNION_PAY("union"),
    //默认使用微信支付
    DEFAULT_PAY("weixin");

    private String key;

    PayStrategyEnum(String key) {
        this.key = key;
    }

    private static final Map<String, IPay7> payKeyMap = new HashMap();

    static {
        payKeyMap.put(ALI_PAY.key, new AliaPay7());
        payKeyMap.put(JINGDONG_PAY.key, new JingDongPay7());
        payKeyMap.put(WEIXIN_PAY.key, new WeixinPay7());
        payKeyMap.put(UNION_PAY.key, new UnionPay());
        payKeyMap.put(DEFAULT_PAY.key, new WeixinPay7());
    }

    public static IPay7 getPay(String payKey) {
        if (!payKeyMap.containsKey(payKey)) {
            return payKeyMap.get(DEFAULT_PAY.key);
        }
        return payKeyMap.get(payKey);
    }
}
