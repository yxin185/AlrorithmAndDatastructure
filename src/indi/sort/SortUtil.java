package indi.sort;

import java.util.Arrays;
import java.util.Random;

public class SortUtil {
    // 判断 元素v与元素w的大小，v < w 返回 true
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    // 交换数组中两个索引处的元素
    public static void swap(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    // 判断数组是否是升序
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1;i < arr.length;i ++) {
            if (less(arr[i],arr[i - 1])) {
                return false;
            }
        }
        return true;
    }
    // 打印一个数组
    public static void printArr(Comparable[] arr) {
        for (int i = 0;i < arr.length;i ++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("数组打印完成。");
    }
    // 产生一个个数为 n，左右边界为闭区间的随机整型数组
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL < rangeR;
        Random random = new Random(System.currentTimeMillis());
        Integer[] arr = new Integer[n];
        for (int i = 0;i < n;i ++) {
            arr[i] = random.nextInt(rangeR + 1) % (rangeR - rangeL + 1) + rangeL;
        }
        return arr;
    }
    // 产生一个接近排序完成的整型数组
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {
        Integer[] arr = new Integer[n];
        // 先产生一个顺序数组
        for (int i = 0;i < n;i ++) {
            arr[i] = i;
        }
        Random random = new Random(System.currentTimeMillis());
        // 对顺序数组进行 swapTimes 次随机交换位置
        for (int i = 0;i < swapTimes;i ++) {
            int posx = random.nextInt(n);
            int posy = random.nextInt(n);
            SortUtil.swap(arr, posx, posy);
        }
        return arr;
    }
    // 获得该数组的一个拷贝数组
    public static Comparable[] getCopy(Comparable[] arr) {
        return Arrays.copyOf(arr,arr.length);
    }
    // 计时函数
    public static long timer(String sortName, Comparable[] arr) {
        long start = System.currentTimeMillis();
        if (sortName.equals("Insertion")) {
            Insertion.sort(arr);
        }
        if (sortName.equals("Selection")) {
            Selection.sort(arr);
        }
        if (sortName.equals("Merge")) {
            Merge.sort(arr);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) {
        Integer[] arr = generateNearlyOrderedArray(10,2);
        SortUtil.printArr(arr);
    }
}
