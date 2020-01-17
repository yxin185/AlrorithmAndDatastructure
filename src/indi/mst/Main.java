package indi.mst;

import indi.weightgraph.Edge;
import indi.weightgraph.ReadWeightedGraph;
import indi.weightgraph.SparseWeightedGraph;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String filename = "testWeightedG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
        ArrayList<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for (int i = 0;i < mst.size();i ++) {
            System.out.println(mst.get(i));
        }

        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<>(g);
        ArrayList<Edge<Double>> mst1 = primMST.mstEdges();
        for (int i = 0;i < mst.size();i ++) {
            System.out.println(mst.get(i));
        }
        System.out.println("The MST weight is: " + primMST.result());
    }
}
