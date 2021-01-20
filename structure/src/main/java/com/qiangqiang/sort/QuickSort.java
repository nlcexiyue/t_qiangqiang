package com.qiangqiang.sort;

import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        Integer[] arr = new Integer[9];
        for (int i = 0; i < 9; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(9);
        }
        long l1 = System.currentTimeMillis();
        sort(arr);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("耗费:" + l + "毫秒");
//        for (int i : arr) {
//            System.out.print(i + ",");
//        }


    }

    //对数组内元素进行排序
    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);

    }

    //对数组a中从索引lo到hi之间的元素进行排序
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        //需要对数组中lo索引到hi索引的元素进行分组,(左子组和右子组)
        int partition = partition(a, lo, hi);      //返回的是头元素最为排序基准排序后的索引


        //让左子组有序,让右子组有序,那么整个就有序了
        sort(a, lo, partition - 1);

        sort(a, partition + 1, hi);


    }

    //对数组a中,从索引lo到索引hi之间的元素进行分组,并返回分组界限对应的索引
    public static int partition(Comparable[] a, int lo, int hi) {
        Comparable key = a[lo];
        int left = lo;
        int right = hi + 1;
        while (true) {

            //先从右往左扫描,移动right指针
            while (comp(key, a[--right])) {
                if (right == lo) {
                    break;
                }
            }

            //从左往右扫描,移动left指针
            while (comp(a[++left], key)) {
                if (left == hi) {
                    break;
                }
            }

            if (left >= right) {
                break;
            } else {
                exchange(a, left, right);
            }


        }
        //交换分界值
        exchange(a, lo, right);


        return right;


    }


    //交换数据
    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static Boolean comp(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
