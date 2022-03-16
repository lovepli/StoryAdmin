package com.story.storyadmin.framework.rpcDemo.service;

import com.story.storyadmin.framework.rpcDemo.bean.Product;

/**
 * @author: lipan
 * @date: 2021年09月27日 9:55 下午
 * @description: 商品查询API接口
 */
public interface IProductService {

    public Product queryById(long id);
}
