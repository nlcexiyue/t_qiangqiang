package com.qiangqiang.controller;

import com.netflix.discovery.converters.Auto;
import com.qiangqiang.entity.Product;
import com.qiangqiang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private ProductService productService;

    @RequestMapping("/product")
    public List<Product> getProduct() {

        List<Product> productList = productService.getProduct();
        return productList;
    }


    @RequestMapping("/productById")
    public List<Product> getProductById(Integer id) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<Product> product = productService.getProductById(id);
        return product;
    }
}
