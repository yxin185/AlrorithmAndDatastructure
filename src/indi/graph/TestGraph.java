package indi.graph;

import java.util.ArrayList;
import java.util.Random;

public class TestGraph {
    public static void main(String[] args) {
        int N = 20;
        int M = 100;
        SparseGraph sg = new SparseGraph(N, false);
        DenseGraph dg = new DenseGraph(N, false);
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0;i < M;i ++) {
            int a = random.nextInt(N);
            int b = random.nextInt(N);
            sg.addEdge(a, b);
            dg.addEdge(a, b);
        }

        for (int v = 0;v < N;v ++) {
            System.out.print(v + " : ");
            ArrayList<Integer> it = (ArrayList<Integer>) dg.adj(v);
            for (int w : it) {
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
