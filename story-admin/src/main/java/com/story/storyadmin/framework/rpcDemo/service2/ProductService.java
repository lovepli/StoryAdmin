package com.story.storyadmin.framework.rpcDemo.service2;

import com.story.storyadmin.framework.rpcDemo.bean.Product;
import com.story.storyadmin.framework.rpcDemo.service.IProductService;

/**
 * @author: lipan
 * @date: 2021年09月27日 10:17 下午
 * @description: 商品服务API的具体实现
 */
public class ProductService implements IProductService {


    @Override
    public Product queryById(long id) {
     Product product = new Product();
     product.setId(id);
     product.setName("water");
     product.setPrice(1.0);
     return product;
    }
}
