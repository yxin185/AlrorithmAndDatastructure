package indi.unionfind;

// Quick Union：一棵由孩子指向父亲的树
public class UnionFind2 implements UF {
    // parent[i] 代表：索引 i 指向的父亲节点的索引
    // parent[1] = 2: 索引1的父亲节点的索引为2，这个父亲节点即为 parent[2]
    private int[] parent;

    public UnionFind2(int count) {
        parent = new int[count];
        for (int i = 0;i < count;i ++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 查找 p 这个索引对应的元素属于哪一个并集
    // 即 p 对应的集合编号
    @Override
    public int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("Illegal find index.");
        while (p != parent[p]) {
            p = parent[p];
        }
        // 返回这个并集的根节点
        return p;
    }
}
