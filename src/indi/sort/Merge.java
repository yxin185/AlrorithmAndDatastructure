package indi.sort;

import java.util.Arrays;

public class Merge {

    private static Comparable[] aux;

    public static void sort(Comparable[] arr) {
        int length = arr.length;
        aux = new Comparable[length];
        mergeHelper(arr, 0, length - 1);
    }

    public static void sortBU(Comparable[] arr) {
        // 进行 lgN 次两两归并
        int N = arr.length;
        aux = new Comparable[N];
        for (int sz = 1;sz <= N;sz = sz + sz) {
            for (int left = 0;left < N - sz; left += sz + sz) {
                Merge.merge(arr, left, left + sz - 1, Math.min(left + sz + sz - 1, N - 1));
            }
        }
    }
    // 递归使用归并排序，对 arr[left,right] 的元素进行归并排序
    private static void mergeHelper(Comparable[] arr, int left, int right) {
    //        if (left >= right) {
    //            return;
    //        }
        // 考虑到在数组元素较少时，数组的有序程度会更高，采用插入排序能提高性能
        if (right - left <= 15) {
            Insertion.sort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeHelper(arr, left, mid);
        mergeHelper(arr, mid + 1, right);
        // 优化思路：当中间的数小于后面第一个数时，就不需要 merge
        if (!SortUtil.less(arr[mid], arr[mid + 1]))
            merge(arr, left, mid, right);
    }

    public static void merge(Comparable[] arr, int left, int mid, int right) {
        // 先定义一个辅助数组，存储 arr 的一个拷贝
        // Comparable[] aux = new Comparable[arr.length];
        // 将 aux 设置成员全局变量能够有效减少每一次递归 merge 函数带来的新建对象的开销
        for (int i = left;i <= right;i ++) {
            aux[i - left] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        for (int k = left;k <= right;k ++) {
            // 首先对i,j两个索引的越界情况作出判断
            if (i > mid) {
                arr[k] = aux[j - left];
                j ++;
            } else if (j > right) {
                arr[k] = aux[i - left];
                i ++;
            } else if (SortUtil.less(aux[i - left], aux[j - left])) {
                arr[k] = aux[i - left];
                i ++;
            } else {
                arr[k] = aux[j - left];
                j ++;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArray(100,0,100);
        Merge.sort(arr);
        System.out.println(SortUtil.isSorted(arr));
        SortUtil.printArr(arr);
    }
}
