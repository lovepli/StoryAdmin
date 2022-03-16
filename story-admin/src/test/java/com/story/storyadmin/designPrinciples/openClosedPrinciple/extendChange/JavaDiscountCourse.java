package com.story.storyadmin.designPrinciples.openClosedPrinciple.extendChange;

import com.story.storyadmin.designPrinciples.openClosedPrinciple.JavaCourse;

import java.math.BigDecimal;

/**
 * @author: lipan
 * @date: 2021/8/18
 * @description:
 * 现在要给Java架构课程做活动，价格优惠，比如双11、618等节日搞促销活动。如果修改JavaCourse中的getPrice()方法，
 * 则会存在一定风险，可能影响其他地方的调用结果。
 * 如何在不修改原有代码的前提下，实现价格优惠这个功能呢？我们再写一个处理优惠逻辑的类——JavaDiscountCourse类（可以思考一下为什么要叫JavaDiscountCourse，而不叫DiscountCourse）。
 *
 * 于是我们就这么干，增加一个java课程的打折类。
 */
public class JavaDiscountCourse extends JavaCourse {

    public BigDecimal getDiscountCoursePrice(BigDecimal discount) {
        return super.getCoursePrice().multiply(discount);
    }


}
