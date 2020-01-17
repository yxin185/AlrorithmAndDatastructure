package indi.weightgraph;

import java.util.ArrayList;

public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    private int n;
    private int m;
    private boolean directed;
    private ArrayList<ArrayList<Edge<Weight>>> g;

    public SparseWeightedGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0;i < n;i ++) {
            g.add(new ArrayList<>());
        }
    }

    // 返回顶点数
    @Override
    public int V() {
        return n;
    }
    // 返回边数
    @Override
    public int E() {
        return m;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;

        for (int i = 0;i < g.get(v).size();i ++) {
            if (g.get(v).get(i).other(v) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void showGraph() {
        for (int i = 0;i < n;i ++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0;j < g.get(i).size();j ++) {
                Edge e = g.get(i).get(j);
                System.out.print("( to : " + e.other(i) + ", wt : " + e.wt() + ") \t");
            }
            System.out.println();
        }
    }

    // 向图中添加一条边，权值为 weight
    @Override
    public void addEdge(Edge e) {
        assert e.v() >= 0 && e.v() < n;
        assert e.w() >= 0 && e.w() < n;

        // 由于在邻接表的情况，查找是否有重边需要遍历整个链表
        // 我们的程序允许重边存在
        g.get(e.v()).add(new Edge(e));
        if (e.v() != e.w() && !directed) {
            g.get(e.w()).add(new Edge(e.w(), e.v(), e.wt()));
        }
        m ++;
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;

        return g.get(v);
    }
}
