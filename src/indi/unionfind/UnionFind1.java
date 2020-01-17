package indi.unionfind;

// Quick Find
// 查找效率 O(1)
// 并操作 O(n)
public class UnionFind1 implements UF{

    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0;i < size;i ++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("Illegal find index.");
        return id[p];
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID)
            return;
        for (int i = 0;i < id.length;i ++) {
            if (id[i] == pID)
                id[i] = qID;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
