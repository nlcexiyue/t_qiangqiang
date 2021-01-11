package com.qiangqiang.fallback;

import com.qiangqiang.entity.Product;
import com.qiangqiang.service.ProductService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        Logger logger = LoggerFactory.getLogger(ProductServiceFallbackFactory.class);
        return new ProductService() {
            @Override
            public List<Product> getProduct() {
                logger.error("product-server服务的getProduct方法异常，异常信息为：" + throwable);
                Product product1 = new Product(1, "手机兜底兜底",1);
                Product product2 = new Product(2, "电脑兜底兜底",1);
                List<Product> list = new ArrayList<>();
                list.add(product1);
                list.add(product2);
                return list;
            }

            @Override
            public List<Product> getProductById(Integer id) {
                logger.error("product-server服务的getProductById方法异常，异常信息为：" + throwable);
                Product product = new Product(id, "手机兜底"+id,1);
                List<Product> list = new ArrayList<>();
                list.add(product);
                return list;
            }
        };
    }
}
