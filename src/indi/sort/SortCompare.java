package indi.sort;

public class SortCompare {
    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArray(10000,0,10000);
        Integer[] arrCopy = (Integer[]) SortUtil.getCopy(arr);
        Integer[] arr1 = SortUtil.generateNearlyOrderedArray(100000,10);

        long t1 = SortUtil.timer("Insertion",arr);
        long t2 = SortUtil.timer("Selection",arrCopy);
        long t3 = SortUtil.timer("Insertion",arr1);

        System.out.println("随机数组-插入排序时间 = " + t1 + "ms");
        System.out.println("随机数组-选择排序时间 = " + t2 + "ms");
        System.out.println("随机几乎排序数组-插入排序时间 = " + t3 + "ms");
    }
}
