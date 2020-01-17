package indi.unionfind;

// 路径压缩
// 相较于 UnionFind4 增加 find 函数中一行代码
public class UnionFind5 implements UF {
    private int[] parent;
    private int[] rank; // rank[i] 表示根节点为 i 的树的高度

    public UnionFind5(int count) {
        parent = new int[count];
        rank = new int[count];
        for (int i = 0;i < count;i ++) {
            parent[i] = i;
            rank[i] = 1; // 初始值为 1
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
        if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
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
            // 路径压缩的过程
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        // 返回这个并集的根节点
        return p;
    }
}
