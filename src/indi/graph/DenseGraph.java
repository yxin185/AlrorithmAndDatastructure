package indi.graph;

import java.util.ArrayList;

// 稠密图-邻接矩阵存储
public class DenseGraph implements Graph{
    private int n; // 顶点数
    private int m; // 边数
    private boolean directed; // 是不是有向图
    private boolean[][] g; // 邻接矩阵

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.directed = directed;
        this.m = 0;
        // boolean 型初始值就全部为 false
        g = new boolean[n][n];
    }
    // 顶点数
    @Override
    public int V() {
        return n;
    }
    // 边数
    @Override
    public int E() {
        return m;
    }
    // 在 v,w 两点之间添加上一条边
    @Override
    public void addEdge(int v, int w) {
        if (v < 0 || v > n - 1 || w < 0 || w > n - 1) {
            throw new IllegalArgumentException("Illegal index v or w.");
        }
        // 因为涉及到万一两点之间已经存在边的情况，所以需要提前判断一下
        // 如果不存在边，才添加边
        if (!hasEdge(v, w)) {
            g[v][w] = true;
            if (!directed) {
                g[w][v] = true;
            }
            m ++;
        }
    }
    // 显示图信息
    @Override
    public void show() {
        for (int i = 0;i < n;i ++) {
            for (int j = 0;j < n;j ++) {
                System.out.print((g[i][j] ? 1 : 0) + "\t");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int v, int w) {
        if (v < 0 || v > n - 1 || w < 0 || w > n - 1) {
            throw new IllegalArgumentException("Illegal index v or w.");
        }
        return g[v][w];
    }
    // 返回图中一个顶点的所有邻边
    public Iterable<Integer> adj(int v) {
        if (v < 0 || v > n - 1) {
            throw new IllegalArgumentException("Illegal index v or w.");
        }
        ArrayList<Integer> adjV = new ArrayList<>();
        for (int i = 0;i < n;i ++) {
            if (g[v][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }


}
