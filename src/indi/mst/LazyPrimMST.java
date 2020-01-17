package indi.mst;

import indi.weightgraph.Edge;
import indi.weightgraph.WeightedGraph;

import java.util.ArrayList;

// 切分定理，最小生成树,Minimum Span Tree
// 时间复杂度 O(E log E):E 代表边数
public class LazyPrimMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> G;    // 图的引用
    private MinHeap<Edge<Weight>> pq;   // 最小堆，算法辅助数据结构
    private boolean[] marked;           // 标记数组，在算法运行过程中判断一个节点 i 是否被访问
    private ArrayList<Edge<Weight>> mst;// 最小生成树所包含的边
    private Number mstWeight;           // 最小生成树的权值

    public LazyPrimMST(WeightedGraph<Weight> graph) {
        G = graph;
        pq = new MinHeap<>(G.E()); // 优先队列存的是每一条边的值
        marked = new boolean[G.V()]; // 存的是每一个顶点是否被访问过,初始值都是 false
        mst = new ArrayList<>();

        // Lazy Prim
        // 从 0 开始遍历各个节点
        visit(0);
        while (!pq.isEmpty()) {
            // 通过优先队列，找出权值最小的边
            Edge<Weight> e = pq.extractMin();
            // 如果这条边的两端都已经访问过了，那么就扔掉这条边
            if (marked[e.v()] == marked[e.w()])
                continue;
            // 否则这条边应该在最小生成树中
            mst.add(e);

            // 访问和这条边连接的还没有被访问过的节点
            if (!marked[e.v()])
                visit(e.v());
            else
                visit(e.w());
        }
        // 计算最小生成树的权值
        mstWeight = mst.get(0).wt(); // 赋值为第一条边的权值
        for (int i = 1;i < mst.size();i ++) {
            mstWeight = mstWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }
    }
    // 访问节点 v
    private void visit(int v) {
        assert !marked[v];
        marked[v] = true;
        // 将和节点 v 相连接的所有未访问的边放入最小堆
        for (Edge<Weight> e : G.adj(v)) {
            if (!marked[e.other(v)])
                // 边e顶点 v 的另一个节点未被访问
                pq.insert(e);
        }
    }
    // 返回最小生成树的所有边
    public ArrayList<Edge<Weight>> mstEdges() {
        return mst;
    }
    // 返回最小权值
    public Number result() {
        return mstWeight;
    }

}
