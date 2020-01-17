package indi.weightgraph;

public interface WeightedGraph<Weight extends Number & Comparable> {
    int V();    // 返回顶点数
    int E();    // 返回边数
    boolean hasEdge(int v, int w);
    void showGraph();
    void addEdge(Edge<Weight> e);
    Iterable<Edge<Weight>> adj(int v);  // 返回与 v 相邻的边的信息
}
