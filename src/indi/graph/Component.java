package indi.graph;

// 联通分量
public class Component {

    private Graph G; // 图引用
    private boolean[] visited; // 记录某一个点是否被访问过
    private int[] id; // 并查集思想，将同一个联通分量中的顶点归并到一个集合
    private int ccount; // 记录联通分量的个数

    public Component(Graph graph) {
        // 算法初始化
        G = graph;
        visited = new boolean[G.V()];
        id = new int[G.V()];
        ccount = 0;
        for (int i = 0;i < G.V();i ++) {
            visited[i] = false;
            id[i] = -1;
        }

        // 计算联通分量
        for (int i = 0;i < G.V();i ++) {
            if (!visited[i]) {
                dfs(i);
                ccount ++;
            }
        }
    }

    private void dfs(int v) {
        // 首先访问到 i 了，那么将 visited 置为 true
        visited[v] = true;
        id[v] = ccount;
        for (int i : G.adj(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public int count() {
        return ccount;
    }

    public boolean isConnected(int v, int w) {
        if (v < 0 || v > G.V() - 1 || w < 0 || w > G.V() - 1) {
            throw new IllegalArgumentException("Illegal index.");
        }
        return id[v] == id[w];
    }

}
