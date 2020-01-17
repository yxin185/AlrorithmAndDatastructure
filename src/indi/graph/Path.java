package indi.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// 找到两点之间的路径
public class Path {
    private Graph G; // 图引用
    private int s; // 源点
    private int[] from; // 记录从哪一个点过来的。记录路径, from[i]表示查找的路径上i的上一个节点
                        // i 来自于 from[i]
    private boolean[] visited; // 记录这个点是否被访问过了
    // 构造函数, 寻路算法, 寻找图graph从s点到其他点的路径
    public Path(Graph graph, int s) {
        // 算法初始化
        G = graph;
        this.s = s;
        from = new int[G.V()];
        visited = new boolean[G.V()];
        for (int i = 0;i < G.V();i ++) {
            from[i] = -1;
            visited[i] = false;
        }
        // 寻路算法
        dfs(s);
    }
    // 图的深度优先遍历
    private void dfs(int v) {
        visited[v] = true;
        for (int i : G.adj(v)) {
            if (!visited[i]) {
                // 表示节点 i 的上一个节点为 v
                // 将 i 的上一个节点记录下来，为 v
                from[i] = v;
                dfs(i);
            }
        }
    }

    public boolean hasPath(int w) {
        if (w < 0 || w > G.V() - 1) {
            throw new IllegalArgumentException("Illegal index.");
        }
        return visited[w];
    }
    // 记录 从s到w的一条记录，存在List中返回
    public List<Integer> path(int w) {
        if (w < 0 || w > G.V() - 1) {
            throw new IllegalArgumentException("Illegal index.");
        }
        // 从最后一个点往前遍历，就能找到到达这个索引的一条路径
        // 所以采用栈来存储路径，先进后出
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while (p != -1) {
            stack.push(p);
            // p 赋值为他的来源顶点，即他的上一个节点
            p = from[p];
        }
        List<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
    // 打印出从 s 到 w 的一条路径
    public void showPath(int w) {
        assert hasPath(w);
        List<Integer> res = path(w);
        for (int i = 0;i < res.size();i ++) {
            System.out.print(res.get(i));
            if (i == res.size() - 1)
                System.out.println();
            else
                System.out.print(" -> ");
        }
    }
}
