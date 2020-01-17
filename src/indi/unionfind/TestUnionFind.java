package indi.unionfind;

import java.util.Random;

public class TestUnionFind {
    public static void main(String[] args) {
        int n = 1000000;
//        double t1 = testUF(new UnionFind1(n));
//        double t2 = testUF(new UnionFind2(n));
//        double t3 = testUF(new UnionFind3(n));
        double t4 = testUF(new UnionFind4(n));
        double t5 = testUF(new UnionFind5(n));
//        System.out.println("UnionFind1 = " + t1 + "ms");
//        System.out.println("UnionFind2 = " + t2 + "ms");
//        System.out.println("UnionFind3 = " + t3 + "ms");
        System.out.println("UnionFind4 = " + t4 + "ms");
        System.out.println("UnionFind5 = " + t5 + "ms");
    }

    private static double testUF(UF uf) {
        int size = uf.getSize();
        Random random = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0;i < size;i ++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a,b);
        }
        for (int i = 0;i < size;i ++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.isConnected(p,q);
        }
        long end = System.currentTimeMillis();
        return (end - start);
    }
}
