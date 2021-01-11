package com.qiangqiang.entity;


import lombok.Data;

import java.io.Serializable;

@Data

public class Product implements Serializable {
    private int id;
    private String name;
    private int orderId;

    public Product(int id, String name, int orderId) {
        this.id = id;
        this.name = name;
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product() {
    }
}
