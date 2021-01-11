package com.qiangqiang.heap;

public class IndexMinPriorityQueue<T extends Comparable> {
    //索引最小优先队列

    public static void main(String[] args) {
        IndexMinPriorityQueue<String> minPriorityQueue = new IndexMinPriorityQueue<>(10);
        minPriorityQueue.insert(0,"A");
        minPriorityQueue.insert(1,"C");
        minPriorityQueue.insert(2,"F");

        minPriorityQueue.changeItems(2,"B");
        while (!minPriorityQueue.isEmpty()){
            int i = minPriorityQueue.delMin();
            System.out.println(i);
        }

    }


    //存储堆中的元素
    private T[] items;

    //保证每个元素在items数组中的索引,pq数组需要堆有序
    private int[] pq;

    //保证qp的逆序,pq的值作为索引,pq的索引作为值
    private int[] qp;

    //记录堆中的元素个数
    private int N;

    //构造方法
    public IndexMinPriorityQueue(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.pq = new int[capacity + 1];
        this.qp = new int[capacity + 1];
        this.N = 0;
        //默认情况下队列中没有存储任何数据,让qp中的元素都为-1
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    //计算数组个数的方法
    public int size() {
        return this.N;
    }

    //判断数组是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    //判断堆中索引i处的元素是否小于索引j处的元素
    public boolean less(int i, int j) {
        //这里对堆调整的时候,比较的是pq数组和items的内容

        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    //交换堆中的索引i和索引j的值
    public void exchange(int i, int j) {
        //交换pq中的元素
        int tempPQ = pq[i];
        pq[i] = pq[j];
        pq[j] = tempPQ;
        //更新qp中的数据
        qp[pq[i]] = i;
        qp[pq[j]] = j;

    }

    //判断k对应的元素是否存在
    public boolean contains(int k) {
        //这里判断的根据构造方法中qp数组的默认值-1
        return qp[k] != -1;
    }

    //最小元素对应的索引
    public int minIndex() {
        return pq[1];
    }


    //往堆中插入一个元素
    public void insert(int i, T t) {
        //首先判断下i是否被关联,如果已经被关联,则不让插入
        if (contains(i)) {
            return;
        }
        //元素个数加1
        N++;
        //把数组存储到items中的i位置
        items[i] = t;
        //把i存储到pq中,往最后一个位置插入
        pq[N] = i;

        //通过qp来记录pq中的i
        qp[i] = N;


        //由于新增一个堆中的元素,都是在最后面新增的,所以要调整位置,应该往上层调整,上浮调整
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
            int min;
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    min = 2 * k;
                } else {
                    min = 2 * k + 1;
                }
            } else {
                min = 2 * k;
            }


            //比较交换
            if (less(k, min)) {
                break;
            } else {
                exchange(k, min);
            }

            //变换k的值
            k = min;

        }


    }

    //删除堆中的最小的元素,并返回这个最小元素的索引
    public int delMin() {
        //获取最小元素关联的索引
        int minIndex = pq[1];
        //交换pq中索引1处和最大索引处的元素
        exchange(1, N);
        //删除qp中对应的内容,N是交换过的了
        qp[pq[N]] = -1;

        //真正的删除掉items 的内容
        items[pq[N]] = null;
        //删除掉pq中最大索引处的内容
        pq[N] = -1;
        //元素个数-1
        N--;
        //对堆进行调整,下沉调整,调整的是pq这个数组,1索引处就是最值
        sink(1);
        return minIndex;
    }

    //删除索引i关联的元素
    public void delete(int i) {
        //要找到i在pq中的索引
        int k = qp[i];
        //交换pq中索引k处的元素和索引N的元素,删除要和最后一个元素交换
        exchange(k, N);
        //删除qp中的内容
        qp[pq[N]] = -1;
        //删除pq中的内容
        pq[N] = -1;

        //删除items中的内容给
        items[k] = null;
        //元素个数-1
        N--;
        //调整堆
        sink(k);
        swim(k);

    }


    //修改索引i关联的元素为t
    public void changeItems(int i, T t) {
        //修改items数组中i位置的元素为t
        items[i] = t;
        //找到i在pq中的索引
        int k = qp[i];
        //堆调整
        sink(k);
        swim(k);
    }


}
