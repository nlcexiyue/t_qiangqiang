package com.qiangqiang.service;

import com.qiangqiang.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "product-server")
public interface ProductService {

    //这就是业务层抽象方法使用SpringMVC注解
    @GetMapping("/product")
    List<Product> getProduct();

    @GetMapping("/productById")
    List<Product> getProductById(@RequestParam("id") Integer id);
}
