package com.qiangqiang.sort;

import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/31
 * \* Time: 15:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class BubbleSort {
    public static void main(String[] args) {
        //冒泡排序
//        int [] arr = {8,3,5,7,2,9,4,1};
        int[] arr = new int[15];
        for (int i = 0; i < 15; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(100);
        }
        int length = arr.length;
        //算法交换元素的执行总数
        int a = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];

                    arr[j + 1] = temp;
                    a++;
                }

            }
        }
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();
        System.out.println(a);
    }
}