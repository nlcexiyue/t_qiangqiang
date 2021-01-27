package com.qiangqiang.gc;

import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) {
        WeakHashMap<Integer , String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "weak";
        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);
        System.gc();

        System.out.println(map);

    }

}
