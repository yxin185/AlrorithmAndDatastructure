package indi.yxin;

import java.util.Arrays;

public class SortTest {
    /**
     * 插入排序对于几乎排序好的数组的排序复杂度为 O(n)
     * @param args
     */
    public static void main(String[] args) {
        Integer[] arr = SortUtil.genRandArr(1000, 0, 10);
        Integer[] arrCopy = Arrays.copyOf(arr,arr.length);
        Integer[] arrCopy2 = Arrays.copyOf(arr,arr.length);
        Integer[] arrCopy3 = Arrays.copyOf(arr,arr.length);
        long start1 = System.currentTimeMillis();
        SelectionSort.selectionSort(arr);
        long end1 = System.currentTimeMillis();
        System.out.println("select = " + (end1 - start1) +"ms");
        long start2 = System.currentTimeMillis();
        InsertSort.insertSort(arrCopy,0, arr.length - 1);
        long end2 = System.currentTimeMillis();
        System.out.println("insertSort = " + (end2 - start2) +"ms");
        long start3 = System.currentTimeMillis();
        MergeSort.mergeSort(arrCopy2);
        long end3 = System.currentTimeMillis();
        System.out.println("merge = " + (end3 - start3) + "ms");
        long start4 = System.currentTimeMillis();
        QuickSort.quickSort(arrCopy3,arrCopy3.length);
        long end4 = System.currentTimeMillis();
        System.out.println("quickSort = " + (end4 - start4) + "ms");

    }
}
