package com.qiangqiang.sort;

import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/31
 * \* Time: 16:13
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ChoiceSort {
    public static void main(String[] args) {
        //选择排序
        int [] arr = {8,3,5,7,2,9,4,1};
//        int[] arr = new int[8];
//        for (int i = 0; i < 8; i++) {
//            Random random = new Random();
//            arr[i] = random.nextInt(100);
//        }

        int length = arr.length;
        //算法交换元素的执行总数
        int a = 0;

        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i; j < length; j++) {

                if (arr[j] < arr[min]) {
                    min = j;
                }



            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            a++;
        }
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();
        System.out.println(a);


    }
}