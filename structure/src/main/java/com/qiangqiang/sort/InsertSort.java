package com.qiangqiang.sort;

import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/31
 * \* Time: 16:39
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class InsertSort {

    //插入排序
    public static void main(String[] args) {
        int[] arr = new int[15];
        for (int i = 0; i < 15; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(100);
        }
//        int [] arr = {8,3,5,7,2,9,4,1};
        int length = arr.length;
        //算法交换元素的执行总数
        int a = 0;

        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
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