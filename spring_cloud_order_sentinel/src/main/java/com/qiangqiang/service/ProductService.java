package com.qiangqiang.service;

import com.qiangqiang.entity.Product;
import com.qiangqiang.exception.ExceptionUtils;
import com.qiangqiang.fallback.ProductFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "product-server",fallbackFactory = ProductFallbackFactory.class)
public interface ProductService {

    @RequestMapping("/product")
    List<Product> getProduct();
}
