package indi.unionfind;

// 改进 UnionFind2,基于 size 优化
public class UnionFind3 implements UF {
    private int[] parent;
    private int[] sz; // sz[i] 表示以 i 为根节点的集合中元素的个数

    public UnionFind3(int count) {
        parent = new int[count];
        sz = new int[count];
        for (int i = 0;i < count;i ++) {
            parent[i] = i;
            sz[i] = 1; // 初始值为 1
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
        if (sz[pRoot] > sz[qRoot]) {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            parent[pRoot] = qRoot;
            sz[qRoot] += pRoot;
        }
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
