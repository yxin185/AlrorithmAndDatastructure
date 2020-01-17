package indi.graph;

public interface Graph {
    int V();
    int E();
    void addEdge(int v, int w);
    void show();
    Iterable<Integer> adj(int v);
}
