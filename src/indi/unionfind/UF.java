package indi.unionfind;

public interface UF {

    int getSize();
    void unionElements(int p, int q);
    boolean isConnected(int p, int q);
    int find(int p);
}
