package com.qiangqiang.sort;

import java.util.Random;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/31
 * \* Time: 17:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ShellSort {

    //希尔排序
    public static void main(String[] args) {
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(1000000);
        }
//        int [] arr = {8,3,5,7,2,9,4,1};
        int length = arr.length;
        //算法交换元素的执行总数
        int a = 0;
        long l1 = System.currentTimeMillis();
        //step步长1
        for (int step = length / 2; step > 0; step /= 2) {
            //这里的step开始是因为第一个元素默认是已经排好序的,不算
            for (int i = step; i < length; i++) {
                for (int j = i; j >= step; j -=step) {

                    if (arr[j] < arr[j - step]) {
                        int temp = arr[j - step];
                        arr[j - step] = arr[j];
                        arr[j] = temp;
                        a++;
                    }else{
                        //待插入的元素已经找到了合适位置,直接跳出内循环
                        break;
                    }


                }

            }

        }
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("耗费:" +l + "毫秒");

        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();
        System.out.println(a);

    }
}