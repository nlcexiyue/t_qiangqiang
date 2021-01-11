package com.qiangqiang.heap;

public class Heap<T extends Comparable<T>> {

    public static void main(String[] args) {
        Heap<String> tHeap = new Heap<>(10);
        tHeap.insert("A");
        tHeap.insert("B");
        tHeap.insert("C");
        tHeap.insert("D");
        tHeap.insert("E");
        tHeap.insert("F");
        tHeap.insert("G");

        String result = null;
        while ((result = tHeap.delMax()) != null) {
            System.out.println(result);
        }


    }

    //存储堆中的元素
    private T[] items;

    //记录堆中的元素个数
    private int N;

    //构造方法
    public Heap(int capacity) {
        //堆其实就是一个数组
        this.items = (T[]) new Comparable[capacity + 1];
        this.N = 0;
    }

    //判断堆中索引i处的元素是小于索引j处的元素
    public boolean less(int i, int j) {

        return items[i].compareTo(items[j]) < 0;
    }

    //交换堆中的索引i和索引j的值
    public void exchange(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //往堆中插入一个元素
    public void insert(T t) {
        items[++N] = t;
        //由于新增一个堆中的元素,都是在最后面新增的,所以要调整位置,应该往上层调整
        swim(N);
    }

    //使用上浮算法,使索引k处的元素能在堆中处于一个正确的位置
    public void swim(int k) {
        //通过循环,不断的比较当前结点的值和父结点的值,如果发现父结点的值比当前结点的值小,则交换位置
        while (k > 1) {
            if (less(k / 2, k)) {
                exchange(k / 2, k);
            }
            //一次循环后往上走一圈
            k = k / 2;
        }
    }


    //使用下沉算法,使索引k处的元素能在堆中处于一个正确的位置
    public void sink(int k) {
        //通过循环,不断的比较当前k结点的值和其左子结点2k和右子结点2k+1的元素大小,如果当前结点小,则需要交换位置
        while (2 * k <= N) {
            //获取当前结点的子结点中的较大结点
            int max;
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                max = 2 * k;
            }


            //比较交换
            if (!less(k, max)) {
                break;
            } else {
                exchange(k, max);
            }

            //变换k的值
            k = max;

        }


    }

    //删除堆中的最大的元素,并返回这个最大元素
    public T delMax() {
        T max = items[1];
        //交换索引1处的元素和最大索引处的元素,让完全二叉树中最右侧的元素变为临时根节点
        exchange(1, N);
        //最大索引处的元素删除掉
        items[N] = null;
        //元素个数-1
        N--;
        //让堆重新有序,同过下沉算法调整堆
        sink(1);
        return max;
    }


    //使用下沉算法,是

}
