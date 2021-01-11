package com.qiangqiang.service;

import com.qiangqiang.entity.Order;

import java.util.List;
import java.util.concurrent.Future;

public interface OrderService {
    Order getOrderById();

    Future<Order> getOrder(Integer id);

    List<Order> getOrderByIds(List<Integer> ids);

}
