package com.qiangqiang.fallback;

import com.qiangqiang.entity.Product;
import com.qiangqiang.service.ProductService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductServiceFallback implements ProductService {
    @Override
    public List<Product> getProduct() {
        Product product1 = new Product(1, "手机兜底兜底",1);
        Product product2 = new Product(2, "电脑兜底兜底",1);
        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);
        return list;
    }

    @Override
    public List<Product> getProductById(Integer id) {
        Product product = new Product(id, "手机兜底"+id,1);
        List<Product> list = new ArrayList<>();
        list.add(product);
        return list;
    }
}
