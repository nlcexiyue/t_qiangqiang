package com.qiangqiang.sort;

import java.util.Random;

public class MergeSort {

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(100);
        }
        long l1 = System.currentTimeMillis();
        sort(arr);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("耗费:" + l + "毫秒");
        for (int i : arr) {
            System.out.print(i + ",");
        }


    }

    //辅助数组
    public static Comparable[] assist;

    //交换数据
    public static void exchange(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //数组a中进行排序
    public static void sort(Comparable[] a) {
        //用辅助数组
        int length = a.length;
        assist = new Comparable[length];
        int lo = 0;
        int hi = length - 1;
        sort(a, lo, hi);
    }

    //数组中lo到hi进行排序
    public static void sort(Comparable[] a, int lo, int hi) {
        //防止非法参数
        if (hi <= lo) {
            return;
        }
        //开始归并算法的每次二拆分为两个数组
        int mid = lo + (hi - lo) / 2;  //这里用(lo+hi)/2也行,但是为了防止数组溢出 lo + (hi - lo ) / 2这样比较好看
        //对拆开的两个数组排序
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        //归并
        merge(a, lo, mid, hi);

    }

    public static Boolean comp(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }


    //数组中,从lo到mid为一组,从mid+1到hi为一组,对这两组数据进行归并
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //弄两个指针,一个指向左边数组的第一个索引,另一个指向右边数组的第一个索引,
        //每次比较,小的数存入辅助数组中,然后小的自增加1指针向右走一位,辅助数组填充一个数,下次一次填充
        int i = lo;
        int j = mid + 1;
        int k = lo;
        while (i <= mid && j <= hi) {
            if (comp(a[i], a[j])) {
                assist[k++] = a[i];
                i++;
            } else {
                assist[k++] = a[j];
                j++;
            }

        }
        //如果i或者j的指针没有走完,那么去查询一下,把剩下的直接放在辅助数组的后面跟着
        while (i <= mid) {

            assist[k++] = a[i++];
        }
        while (j <= hi) {

            assist[k++] = a[j++];
        }
        //把辅助数组中的数据拷贝到原数组
        for (int l = lo; l <= hi; l++) {
            a[l] = assist[l];
        }


    }
}
