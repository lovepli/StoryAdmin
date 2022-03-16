package com.story.storyadmin.enumerator.demo3;

/**
 * @author: 59688
 * @date: 2021/7/21
 * @description: 通用枚举范式接口
 * 为了便于统一处理和规范统一的风格，指定一个统一的抽象接口
 *
 * https://mp.weixin.qq.com/s/Q5E059XI5cBfjKhiRnt0ug
 * 场景：在Java开发中我们为了避免过多的魔法值(魔数)，使用枚举类来封装一些静态的状态代码。但是在将这些枚举的意思正确而全面的返回给前端却并不是那么顺利，我们通常会使用Jackson类库序列化对象为JSON，今天就来讲一个关于使用Jackson序列化枚举的通用性技巧
 */
/**
 * The interface Enumerator.
 */
public interface Enumerator {
    /**
     * Code integer.
     *
     * @return the integer
     */
    Integer code(); // TODO 应该使用范型来定义code的类型！！

    /**
     * Description string.
     *
     * @return the string
     */
    String description();
}