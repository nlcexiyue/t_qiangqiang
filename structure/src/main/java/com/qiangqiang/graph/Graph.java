package com.qiangqiang.graph;


import sun.misc.Queue;

public class Graph {
    //图


    //顶点数目
    private final int V;
    //边的数目
    private int E;
    //邻接表
    private Queue<Integer>[] adj;


    public Graph(int v) {
        //初始化顶点数量
        this.V = v;
        //初始化边的数量
        this.E = 0;
        //初始化连接表
        this.adj = new Queue[V];

        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Integer>();
        }
    }

    //获取顶点数目
    public int V() {
        return this.V;
    }

    //获取边的数目
    public int E() {
        return this.E;
    }

    //向图中添加一条边v-w
    public void addEdge(int v, int w) {
        //无向图中边是没有方向的,所以边既可以是v-w的边,也可以是w-v的边,因此需要w出现在v的邻接表中,并且v也要出现在w的邻接表中

        adj[v].enqueue(w);
        adj[w].enqueue(v);
        //边+1
        E++;
    }

    //获取和顶点v相邻的所有顶点
    public Queue<Integer> adj(int v) {


        return adj[v];
    }

}
