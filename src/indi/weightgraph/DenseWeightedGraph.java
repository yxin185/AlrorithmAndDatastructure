package indi.weightgraph;

import java.util.ArrayList;

public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    private int n; // 节点数
    private int m; // 边数
    private boolean directed;
    private Edge<Weight>[][] g; // 图的具体数据,g[v][w]指从v到w的一条边，不存在就为空

    public DenseWeightedGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new Edge[n][n];
        for (int i = 0;i < n;i ++) {
            for (int j = 0;j < n;j ++) {
                g[i][j] = null;
            }
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
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return g[v][w] != null;
    }

    @Override
    public void showGraph() {
        for (int i = 0;i < n;i ++) {
            for (int j = 0;j < n;j ++) {
                if (g[i][j] != null) {
                    // 打印的是邻边之间的权值
                    System.out.print(g[i][j].wt() + "\t");
                } else {
                    System.out.print("NULL\t");
                }
            }
            // 打印完一排之后换行
            System.out.println();
        }
    }

    @Override
    public void addEdge(Edge e) {
        assert e.v() >= 0 && e.v() < n;
        assert e.w() >= 0 && e.w() < n;
        if (hasEdge(e.v(), e.w())) {
            return;
        }
        g[e.v()][e.w()] = new Edge(e);
        // 不是环且非有向
        if (e.v() != e.w() && !directed) {
            g[e.w()][e.v()] = new Edge(e.w(), e.v(), e.wt());
        }
        m ++;
    }

    // 返回图中一个顶点的所有邻边
    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert v >= 0 && v < n;
        ArrayList<Edge<Weight>> adjV = new ArrayList<>();
        for (int i = 0;i < n;i ++) {
            if (g[v][i] != null) {
                adjV.add(g[v][i]);
            }
        }
        return adjV;
    }
}
