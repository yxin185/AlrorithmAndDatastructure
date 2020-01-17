package indi.mst;

import indi.unionfind.UnionFind5;
import indi.weightgraph.Edge;
import indi.weightgraph.WeightedGraph;

import java.util.ArrayList;

// 效率低于Prim算法，但是思想简单，先排序，再找权值小的边；适用于小图
public class KruscalMST<Weight extends Number & Comparable> {
    private ArrayList<Edge<Weight>> mst;    // 最小生成树所包含的所有边
    private Number mstWeight;               // 最小生成树权值

    public KruscalMST(WeightedGraph graph) {
        mst = new ArrayList<>();
        // 将图中所有边存到最小堆中进行排序
        MinHeap<Edge<Weight>> pq = new MinHeap<>(graph.V());
        for (int i = 0;i < graph.V();i ++) {
            for (Object item : graph.adj(i)) {
                Edge<Weight> e = (Edge<Weight>) item;
                // 避免重复插入：如 1-2 2-1就是重复的边
                if (e.v() <= e.w()) {
                    pq.insert(e);
                }
            }
        }

        // 创建一个并查集，来查看已经访问的节点的联通情况
        UnionFind5 uf = new UnionFind5(graph.V());
        // mst 中应该含有 顶点数-1 条边
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            // 从最小堆中依次从小到大取出所有的边
            Edge<Weight> e = pq.extractMin();
            // 如果该边的两个端点是联通的，说明加入这条边会形成环，扔掉这条边
            if (uf.isConnected(e.v(), e.w()))
                continue;
            // 否则，将这条边加入最小生成树，同时标记边的两个端点联通
            mst.add(e);
            uf.unionElements(e.v(), e.w());
        }

        mstWeight = mst.get(0).wt();
        for (int i = 1;i < mst.size();i ++) {
            mstWeight = mst.get(i).wt().doubleValue() + mstWeight.doubleValue();
        }
    }

    public Number result() {
        return mstWeight;
    }

    public ArrayList<Edge<Weight>> mstEdges() {
        return mst;
    }
}
