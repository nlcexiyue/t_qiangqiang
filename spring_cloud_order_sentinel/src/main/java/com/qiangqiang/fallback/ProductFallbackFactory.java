package com.qiangqiang.fallback;

import com.qiangqiang.entity.Product;
import com.qiangqiang.service.ProductService;
import feign.hystrix.FallbackFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/11
 * \* Time: 14:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Component
public class ProductFallbackFactory implements FallbackFactory<ProductService> {



    Logger logger = LoggerFactory.getLogger(ProductFallbackFactory.class);


    @Override
    public ProductService create(Throwable throwable) {
        logger.error("熔断了之后，使用fallbackFactory工厂，此时产生的异常信息为："+throwable);
        return new ProductService() {
            @Override
            public List<Product> getProduct() {
                Product product1 = new Product(1, "手机restTemplate兜底",1);
                Product product2 = new Product(2, "电脑restTemplate兜底",1);
                List<Product> list = new ArrayList<>();
                list.add(product1);
                list.add(product2);
                return list;
            }
        };
    }
}