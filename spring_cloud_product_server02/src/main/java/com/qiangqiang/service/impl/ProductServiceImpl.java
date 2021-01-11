package com.qiangqiang.service.impl;


import com.qiangqiang.entity.Product;
import com.qiangqiang.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProduct() {
        Product product1 = new Product(1, "手机",1);
        Product product2 = new Product(2, "电脑",1);
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        return list;
    }

    @Override
    public List<Product> getProductById(Integer id) {
        Product product = new Product(id, "手机"+id,1);
        List<Product> list = new ArrayList<>();
        list.add(product);
        return list;
    }
}
