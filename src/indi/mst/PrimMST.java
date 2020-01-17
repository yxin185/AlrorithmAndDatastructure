package indi.mst;

import indi.weightgraph.Edge;
import indi.weightgraph.WeightedGraph;

import java.util.ArrayList;

// 使用优化的 Prim 算法
// 时间复杂度 O(E log V)
public class PrimMST<Weight extends Number & Comparable> {
    private WeightedGraph G;    // 图的引用
    private IndexMinHeap ipq;   // 最小索引堆，算法辅助数据结构
    private Edge<Weight>[] edgeTo;  // 访问的点所对应的边，算法辅助数据结构
    private boolean[] marked;   // 标记数组
    private ArrayList<Edge<Weight>> mst;    // 最小生成树所包含的所有边
    private Number mstWeight;   // 最小生成树的权值

    public PrimMST(WeightedGraph graph) {
        G = graph;
        assert G.E() >= 1;  // 确保边数至少大于等于1
        ipq = new IndexMinHeap(G.V());
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        for (int i = 0;i < G.V();i ++) {
            marked[i] = false;
            edgeTo[i] = null;
        }
        mst = new ArrayList<>();
        // Prim
        visit(0);
        while (!ipq.isEmpty()) {
            // 使用最小索引堆找出已经访问的边中权值最小的边
            // 最小索引堆中存储的是点的索引，通过点的索引找到对应的边
            int v = ipq.extractMinIndex();
            assert (edgeTo[v] != null);
            mst.add(edgeTo[v]);
            visit(v);
        }

        mstWeight = mst.get(0).wt();
        for (int i = 1;i < mst.size();i ++) {
            mstWeight = mstWeight.doubleValue() + mst.get(i).wt().doubleValue();
        }
    }
    // 访问节点 v
    private void visit(int v) {
        assert !marked[v];
        marked[v] = true;
        // 将和节点v相连接的未访问的另外一个端点，和与之相连的边，放入最小堆中
        for (Object item : G.adj(v)) {
            Edge<Weight> e = (Edge<Weight>) item;
            int w = e.other(v);
            if (!marked[w]) {
                if (edgeTo[w] == null) {
                    edgeTo[w] = e;
                    ipq.insert(w, e.wt());
                } else if (e.wt().compareTo(edgeTo[w].wt()) < 0) {
                    edgeTo[w] = e;
                    ipq.change(w, e.wt());
                }
            }
        }
    }

    // 返回最小生成树的所有边
    public ArrayList<Edge<Weight>> mstEdges() {
        return mst;
    }
    public Number result() {
        return mstWeight;
    }
}
