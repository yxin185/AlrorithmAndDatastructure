package indi.weightgraph;

public class Main {

    // 测试通过文件读取图的信息
    public static void main(String[] args) {

        String filename = "testWeightedG1.txt";
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph readWeightedGraph = new ReadWeightedGraph(g1, filename);
        System.out.println("test G1 in Sparse Weighted Graph");
        g1.showGraph();

        System.out.println();

        DenseWeightedGraph<Double> g2 = new DenseWeightedGraph<>(8, false);
        ReadWeightedGraph readWeightedGraph2 = new ReadWeightedGraph(g2, filename);
        System.out.println("test G1 in Dense Weighted Graph");
        g2.showGraph();
    }
}
