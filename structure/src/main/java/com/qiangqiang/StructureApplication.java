package com.qiangqiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.LinkedList;

@SpringBootApplication
public class StructureApplication {

    public static void main(String[] args) {
        new ArrayList<>();
        new LinkedList<>();

        SpringApplication.run(StructureApplication.class, args);
    }

}
