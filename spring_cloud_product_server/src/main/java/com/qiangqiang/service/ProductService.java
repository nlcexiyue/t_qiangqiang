package com.qiangqiang.service;

import com.qiangqiang.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
    List<Product> getProduct();

    List<Product> getProductById(@RequestParam("id") Integer id);
}
