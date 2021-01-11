package com.qiangqiang.crm.controller;

import com.qiangqiang.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {


    @RequestMapping("index")
    public String index(){
        return "customer/customer";
    }








}
