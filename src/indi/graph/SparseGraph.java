package indi.graph;

import java.util.ArrayList;

// 稀疏矩阵-邻接表
// 遍历邻边更加高效快捷
public class SparseGraph implements Graph{
    private int n;
    private int m;
    private boolean directed;
//    private int[][] g; // 邻接表
    private ArrayList<ArrayList<Integer>> g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0;i < n;i ++) {
            g.add(new ArrayList<>());
        }
    }
    @Override
    public int V() {
        return n;
    }
    @Override
    public int E() {
        return m;
    }
    @Override
    public void addEdge(int v, int w) {
        if (v < 0 || v > n - 1 || w < 0 || w > n - 1) {
            throw new IllegalArgumentException("Illegal index v or w.");
        }
        // 难以排除自环边和平行边
        g.get(v).add(w);
        if (!directed) {
            g.get(w).add(v);
        }
        m ++;
    }

    @Override
    public void show() {
        for (int i = 0;i < n;i ++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0;j < g.get(i).size();j ++) {
                System.out.print(g.get(i).get(j) + "\t");
            }
            System.out.println();
        }
    }

    // 验证图中是否有从 v 到 w 的边
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v > n - 1 || w < 0 || w > n - 1) {
            throw new IllegalArgumentException("Illegal index v or w.");
        }
        for (int i = 0;i < g.get(v).size();i ++) {
            if (g.get(v).get(i) == w)
                return true;
        }
        return false;
    }
    // 返回一个图中一个顶点的所有邻边
    //
    public Iterable<Integer> adj(int v) {
        if (v < 0 || v > n - 1) {
            throw new IllegalArgumentException("Illegal index v or w.");
        }
        return g.get(v);
    }

}
