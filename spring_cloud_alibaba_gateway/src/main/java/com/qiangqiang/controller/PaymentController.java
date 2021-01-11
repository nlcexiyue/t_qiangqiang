package com.qiangqiang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @GetMapping(value = "/payment/nacos")
    public String getPayment(Integer id) {
        return "nacos registry, serverPort: " + "server To nacos" + "\t id" + id;
    }
}
