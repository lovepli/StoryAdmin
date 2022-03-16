package com.story.storyadmin.designPatterns.payService;

import com.story.storyadmin.designPatterns.payService.impl.AliaPay;
import com.story.storyadmin.designPatterns.payService.impl.JingDongPay;
import com.story.storyadmin.designPatterns.payService.impl.WeixinPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 * PayService类的toPay方法主要是发起支付。根据不同的code，决定调用不同支付类（例如：aliaPay）的pay方法进行支付。

 * 这段代码有什么问题？也许有些人会这样做。
 *
 * 想象一下，如果支付方式越来越多，比如百度支付、美团支付、银联等，你需要修改toPay方式的代码，添加一个新的else...更多的逻辑会导致更多 和更多的逻辑？  显然，这里违反了设计模式的六大原则：
 *
 * 开闭原则：对扩展开放，对修改封闭。也就是说，要添加新功能，应尽量少改动现有代码。
 *
 * 单一职责原则：顾名思义，要求逻辑尽量简单，不要太复杂，易于复用。
 *
 *  有没有办法解决这个问题？
 *
 * 消除 if...else 花招
 */
//@Service
@Component
public class PayService {
    @Autowired
    private AliaPay aliaPay;
    @Autowired
    private WeixinPay weixinPay;
    @Autowired
    private JingDongPay jingDongPay;

    public void toPay(String code) {
        if ("alia".equals(code)) {
            aliaPay.pay();
        } else if ("weixin".equals(code)) {
            weixinPay.pay();
        } else if ("jingdong".equals(code)) {
            jingDongPay.pay();
        } else {
            System.out.println("找不到支付方式");
        }
    }

}
