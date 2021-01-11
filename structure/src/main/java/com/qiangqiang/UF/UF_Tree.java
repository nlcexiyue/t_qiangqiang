package com.qiangqiang.UF;

public class UF_Tree {
    //优化并查集

    //记录结点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private int count;

    //初始化并查集
    private UF_Tree(int N) {
        //初始化分组的数量
        this.count = N;
        //初始化eleAndGroup数组
        this.eleAndGroup = new int[N];
        //初始化eleAndGroup中元素及其所在的组的标识符
        //让eleAndGroup数组的索引作为并查集的每个结点的元素,并且让每个索引处的值(该元素所在组的标识符)就是该索引
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }

    //获取当前并查集中的数据有多少个分组
    public int count() {
        return this.count;
    }

    //元素p所在分组的标识符
    public int find(int p) {
        while (true) {
            if (p == eleAndGroup[p]) {
                return p;
            }
            p = eleAndGroup[p];
        }
    }

    //判断并查集中元素p和元素q是否在同一分组
    public boolean connected(int p, int q) {

        return find(p) == find(q);
    }

    //把p元素所在分组和q元素所在分组合并
    public void union(int p, int q) {
        //判断一下元素p和q是否已经在一个分组
        if (connected(p, q)) {
            return;
        }
        //不在同一个分组的话
        //先找到p所在分组的标识符
        int pRoot = find(p);

        //再找到q所在分组的标识符
        int qRoot = find(q);

        //合并组:让p所在组的所有元素的标识符变为q所在组的标识符
        eleAndGroup[pRoot] = qRoot;

        //组个数-1
        count--;


    }


}
