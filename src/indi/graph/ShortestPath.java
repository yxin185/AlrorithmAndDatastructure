package indi.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ShortestPath {
    private Graph G;
    private int s; // 起始点
    private boolean[] visited;
    private int[] from;
    private int[] ord; // 记录路径中节点的次序。ord[i]表示i节点在路径中的次序。

    public ShortestPath(Graph graph, int s) {
        G = graph;
        this.s = s;
        assert s >= 0 && s < G.V();
        visited = new boolean[G.V()];
        from = new int[G.V()];
        ord = new int[G.V()];
        for (int i = 0;i < G.V();i ++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        // 无向图最短路径算法，从 s 开始广度优先遍历
        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        ord[s] = 0;
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int i : G.adj(v)) {
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }
    }

    public boolean hasPath(int w) {
        assert w >= 0 && w < G.V();
        return visited[w];
    }

    public List<Integer> path(int w) {
        assert hasPath(w);
        Stack<Integer> stack = new Stack<>();
        int p = w;
        while (p != -1) {
            stack.push(p);
            p = from[p];
        }
        // 从栈中依次取出元素，放到链表中
        List<Integer> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }
    // 打印从 S 到 w 的最短路径
    public void showPath(int w) {
        assert hasPath(w);
        List<Integer> res = path(w);
        for (int i = 0;i < res.size();i ++) {
            System.out.print(res.get(i));
            if (i == res.size() - 1) {
                System.out.println();
            } else {
                System.out.print(" -> ");
            }
        }
    }

    // 查看从 s 到 w 的最短路径长度，要是不存在最短路径，返回 -1
    public int length(int w) {
        assert w >= 0 && w < G.V();
        return ord[w];
    }

}
