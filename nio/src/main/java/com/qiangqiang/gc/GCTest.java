package com.qiangqiang.gc;

import java.util.Random;

public class GCTest {
    public static void main(String[] args) {
        String a = "1";

        for (int i = 0; ; i++) {
            a += a + new String(new Random().nextInt(99999999)+"");
            System.out.println(a);
        }


    }
}
