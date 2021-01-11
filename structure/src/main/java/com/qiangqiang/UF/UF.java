package com.qiangqiang.UF;

import java.util.Scanner;

public class UF {
    //并查集
    public static void main(String[] args) {
        UF uf = new UF(5);
        System.out.println("默认情况下有多少分组" + uf.count());
        //从控制台录入两个要合并的元素,调用union方法,观察合并后的并查集中的分组是否减少

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入第一个要合并的元素");
            int p = scanner.nextInt();
            System.out.println("输入第二个要合并的元素");
            int q = scanner.nextInt();
            if (uf.connected(p, q)) {
                System.out.println(p + "和" + q + "已经在一个组了");
                continue;
            }
            uf.union(p, q);
            System.out.println("还有多少个分组" + uf.count());
        }

    }

    //记录结点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private int count;

    //初始化并查集
    private UF(int N) {
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

        return eleAndGroup[p];
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
        int pGroup = find(p);

        //再找到q所在分组的标识符
        int qGroup = find(q);

        //合并组:让p所在组的所有元素的标识符变为q所在组的标识符
        //遍历eleAndGroup,值为qGroup的元素,把值变为pGroup
        for (int i = 0; i < eleAndGroup.length; i++) {
            if (eleAndGroup[i] == pGroup) {
                eleAndGroup[i] = qGroup;
            }
        }

        //组个数-1
        count--;


    }


}
