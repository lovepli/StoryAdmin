package com.story.storyadmin.framework.rpcDemo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: lipan
 * @date: 2021年09月27日 9:51 下午
 * @description: 要注意的是，Product是可以被序列化的，Why?
 *
 * 很显然，订单系统调用商品系统的时候，需要商品系统返回一个商品，必然涉及到发生网络传输，这就涉及对象的序列化和反序列化了。
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private double price;


    /**
     * 在实际后台服务开发中，比如订单服务（开发者A负责）需要调用商品服务（开发者B负责），那么开发者B会和A约定调用API，以接口的形式提供给A。通常都是B把API上传到Maven私服，然后B开始写API的实现，A只需要引入API依赖进行开发即可。
     *
     * 作者：张丰哲
     * 链接：https://www.jianshu.com/p/29d75a25eeaf
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
}
