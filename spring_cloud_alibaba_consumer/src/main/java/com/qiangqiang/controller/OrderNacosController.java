package com.qiangqiang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * create date  2020-08-25 16:13 by niugang
 */
@RestController
@Slf4j
public class OrderNacosController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.cloudalibaba-provider}")
    private String serverURL;

    @GetMapping(value = "/consumer/payment/nacos")
    public String paymentInfo(Long id) {
        return "/payment/nacos/ " + "server To nacos" + " id" + id;
    }

}
