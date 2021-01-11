package com.qiangqiang.list;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/28
 * \* Time: 17:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ArrayList {
    /**
     * 定义一个数组,用于保存集合中的数据
     */
    private Object[] elementData;

    /**
     * 定义一个变量,用于保存集合中实际存放元素的个数
     */
    private int size;

    /**
     * 方法:获取元素中实际存放的元素
     */
    private int size() {
        return this.size;
    }

    /**
     * 构造方法()设置elementData数组的空间长度默认为10
     */
    public ArrayList() {
        this.elementData = new Object[10];
    }

    /**
     * 方法:指定设置elementData数组的空间长度
     */
    public ArrayList(int cap) {
        //判断cap变量是否合法
        if (cap < 0) {
            throw new ArrayIndexOutOfBoundsException("参数不合法" + cap);
        }
        //实例化elementData数组
        this.elementData = new Object[cap];
    }

    /**
     * 方法 : add 方法
     *
     * @param element
     */
    public void add(Object element) {
        //判断数组是否需要扩容
        //当数组的空间长度等于数组实际存放的元素的个数时,需要扩容操作
        if (elementData.length == size) {
            //创建一个比原数组更大的数组
            Object[] newArr = new Object[elementData.length * 2 + 1];
            //将原数组中的元素放到新数组中
            for (int i = 0; i < size; i++) {
                newArr[i] = elementData[i];
            }
            //让原数组保存新数组的地址值
            elementData = newArr;
        }
        //把element放入数组中
        elementData[size] = element;
        //更新size的值
        size++;
    }

    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }


    /**
     * 根据索引获取元素值
     */
    public Object get(int index) {
        //判断索引是否合法,
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
        Object elementDatum = elementData[index];
        return elementDatum;
    }


    /**
     * 根据索引删除元素
     */
    public void remove(int index) {
        //判断索引是否合法
        rangeCheck(index);
        //把删除索引之后的元素往前挪动一位
        for (int i = index; i < size - 1; i++) {
            //把后一个元素往前挪动一位
            elementData[i] = elementData[i + 1];
        }
        //挪动后最后两个索引位的元素时相同的,要把最后一个索引位设置为默认值
        elementData[size - 1] = null;
        //更新size的值
        size--;
    }

    /**
     * 元素插入
     */
    public void add(int index, Object element) {
        //1,判断索引是否合法,合法取值范围为[0,size]之间,插入的元素可以在最末尾
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }

        //2.判断数组是否需要扩容
        ensureCapacityInternal();

        //3.把插入索引及其之后的元素往后挪动一位,从后往前挪动
        for (int i = size - 1; i >= index; i--) {
            //把前一个元素往后挪动一位
            elementData[i + 1] = elementData[i];
        }
        //4.在插入索引位置实现赋值操作
        elementData[index] = element;
        //5.更新size的值
        size++;
    }


    /**
     * 数组扩容操作
     */
    private void ensureCapacityInternal() {
        if (elementData.length == size) {
            //创建一个比原数组更大的数组
            Object[] newArr = new Object[elementData.length * 2 + 1];
            //将原数组中的元素放到新数组中
            for (int i = 0; i < size; i++) {
                newArr[i] = elementData[i];
            }
            //让原数组保存新数组的地址值
            elementData = newArr;
        }

    }


    /**
     * 检查索引是否合法
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("数组越界");
        }
    }


    public static void main(String[] args) {
//        ArrayList arrayList = new ArrayList(6);
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        arrayList.add(4);
//
//
//        System.out.println(arrayList);
//
////        Object o = arrayList.get(4);
////        System.out.println(o);
////        arrayList.remove(3);
//
//        arrayList.add(4, 22);
//        for (int i = 0; i < arrayList.size; i++) {
//            System.out.println(arrayList.get(i));
//        }

        int[] arr = {1, 2, 6, 3, 5, 2};

        replaceOrderArray(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " - ") ;
        }
    }

    /**
     * 数组反转
     */
    private static void reverseOrderArray(int[] arr) {
        //1.通过循环,获得数组前半部分的元素
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /**
     * 数组中找出重复值
     */
    private static int getRepeatNumber(int[] arr) {
        //1.判断arr等于null或arr.length = 0的情况
        if (arr == null || arr.length == 0) {
            return -1;
        }
        //2.通过循环遍历数组中的所有元素
        for (int i = 0; i < arr.length; ) {
            //3.判断数组元素是否合法
            if (arr[i] < 0 || arr[i] > arr.length) {
                return -1;
            }
            //4.处理arr[i]等于i的情况
            if (arr[i] == i) {
                i++;
            }
            //5.处理arr[i]不等于i的情况
            else {
                //如果arr[i]等于arr[arr[i]],则证明找到了重复的元素
                if (arr[i] == arr[arr[i]]) {
                    return arr[i];
                } else {
                    int temp = arr[i];
                    arr[i] = arr[arr[i]];
                    arr[temp] = temp;
                }
            }

        }
        //6.执行到此处,说明数组中不存在重复的元素
        return -1;
    }

    /**
     * 使数组中的奇数位于偶数前面
     */
    public static void replaceOrderArray(int[] arr) {
        //1.判断arr等于null或arr.length = 0的情况
        if (arr == null || arr.length == 0) {
            throw new RuntimeException();
        }
        int min = 0, max = arr.length - 1;

        while (min < max){
            while (min < max && arr[min] % 2 == 0){
                min++;
            }
            while (max > min && arr[max] % 2 == 1){
                max--;
            }
            if (min != max ){
                int temp = arr[min];
                arr[min] = arr[max];
                arr[max] = temp;
            }
        }




    }


}