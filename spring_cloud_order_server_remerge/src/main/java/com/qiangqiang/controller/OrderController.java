package com.qiangqiang.controller;

import com.qiangqiang.entity.Order;
import com.qiangqiang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;
    @RequestMapping("/order")
    public Order getOrder() {

        Order order = orderService.getOrderById();
        return order;
    }

    @RequestMapping("/orderById")
    public Order getOrder(Integer id) {

        Future<Order> order1 = orderService.getOrder(id);
        Future<Order> order2 = orderService.getOrder(id);
        Future<Order> order3 = orderService.getOrder(id);
        Future<Order> order4 = orderService.getOrder(id);
        Future<Order> order5 = orderService.getOrder(id);

        try {
            System.out.println(order1.get());
            System.out.println(order2.get());
            System.out.println(order3.get());
            System.out.println(order4.get());
            System.out.println(order5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new Order(1, "用户订单", 2, null);
    }
}
