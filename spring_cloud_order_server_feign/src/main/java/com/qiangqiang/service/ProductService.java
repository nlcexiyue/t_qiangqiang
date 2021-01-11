package com.qiangqiang.service;

import com.qiangqiang.entity.Product;
import com.qiangqiang.fallback.ProductServiceFallback;
import com.qiangqiang.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//@FeignClient(value = "product-server",fallback = ProductServiceFallback.class)
@FeignClient(value = "product-server",fallbackFactory = ProductServiceFallbackFactory.class)
public interface ProductService {

    //这就是业务层抽象方法使用SpringMVC注解1787387
    @GetMapping("/product")
    List<Product> getProduct();

    @GetMapping("/productById")
    List<Product> getProductById(@RequestParam("id") Integer id);
}
