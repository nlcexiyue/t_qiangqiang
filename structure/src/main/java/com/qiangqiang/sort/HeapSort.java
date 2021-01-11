package com.qiangqiang.sort;

import java.util.Random;

public class HeapSort {
    //堆排序
    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D"};
        long l1 = System.currentTimeMillis();
        sort(arr);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println("耗费:" + l + "毫秒");
        for (String i : arr) {
            System.out.print(i + ",");
        }
    }


    //构造堆
    public static void createHeap(Comparable[] source, Comparable[] heap) {
        //把source中的元素拷贝到heap中,heap就形成了一个无序的堆
        System.arraycopy(source, 0, heap, 1, source.length);

        //对堆中的元素做下沉调整,从长度的一半处向索引1扫描
        for (int i = heap.length / 2; i > 0; i--) {
            //做下沉调整,这里是对heap堆的整个范围内调整,range为heap长度
            sink(heap, i, heap.length - 1);
        }

    }


    //对source数组中的数据从小到大排序
    public static void sort(Comparable[] source) {
        //构建堆
        Comparable[] heap = new Comparable[source.length + 1];
        createHeap(source, heap);
        //定义一个变量,记录未排序的元素中最大的索引
        int N = heap.length - 1;


        //通过循环,交换1索引处的元素和排序的元素中的最大索引处的元素
        while (N != 1) {
            exchange(heap, 1, N);
            //排序交换后最大元素所在的索引,让它不要参与堆下沉的过程
            N--;
            //需要对索引1 处的元素进行下沉调整
            sink(heap, 1, N);
        }


        System.arraycopy(heap, 1, source, 0, source.length);
    }

    //在heap堆中,对target处的元素做下沉,范围是0,range
    public static void sink(Comparable[] heap, int target, int range) {

        while (2 * target <= range) {
            //1.找出当前结点的较大的子结点
            int max;
            if (2 * target + 1 <= range) {
                if (less(heap, 2 * target, 2 * target + 1)) {
                    max = 2 * target + 1;
                } else {
                    max = 2 * target;
                }
            } else {
                max = 2 * target;
            }

            //2.比较当前结点
            //比较交换
            if (!less(heap, target, max)) {
                break;
            } else {
                exchange(heap, target, max);
            }

            //变换k的值
            target = max;


        }

    }

    //交换数据
    public static void exchange(Comparable[] heap, int i, int j) {
        Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    public static Boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }
}
