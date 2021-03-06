package indi.sort;

import java.util.Random;

public class
Quick {

    public static void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
//        if (right - left <= 15) {
//            Insertion.sort(arr, left, right);
//        }
        int p = partition(arr, left, right);
        sort(arr, left, p - 1);
        sort(arr, p + 1, right);
    }

    private static int partition(Comparable[] arr, int left, int right) {
        // 随机化标定元素，避免了近乎有序的数组带来的最差时间复杂度 O(n^2)
        Random random = new Random();
        SortUtil.swap(arr, left, random.nextInt(right + 1) % (right - left + 1) + left);
        Comparable v = arr[left];
        int i = left + 1;
        int j = right;

        while (true) {
            while (i <= right && SortUtil.less(arr[i], v)) i ++;
            // left 已经被占领了，所以 j >= left + 1
            while (j >= left + 1 && arr[j].compareTo(v) > 0) j --;
            if (i >= j) break;
            SortUtil.swap(arr, i, j);
            i ++;
            j --;
        }
        SortUtil.swap(arr, left, j);
        return j;
    }

    // 实现三路快排
    public static void sort3Ways(Comparable[] arr) {
        sort3Ways(arr, 0, arr.length - 1);
    }

    private static void sort3Ways(Comparable[] arr, int left, int right) {
        if (right - left <= 15) {
            Insertion.sort(arr, left, right);
            return;
        }
//        if (left >= right) return;
        Random random = new Random(System.currentTimeMillis());
        int k = random.nextInt(right + 1) % (right - left + 1) + left;
        SortUtil.swap(arr, left, k);
        Comparable v = arr[left];
        int lt = left;  // arr[left + 1, lt] < v
        int gt = right + 1; // arr(gt, right] > v
        int i = left + 1;   // arr[lt + 1, i) = v
        while (i < gt) {
            if (SortUtil.less(arr[i], v)) {
                SortUtil.swap(arr, i, lt + 1);
                lt ++;
                i ++;
            } else if (arr[i].compareTo(v) > 0) {
                SortUtil.swap(arr, i, gt - 1);
                gt --;
            } else {
                i ++;
            }
        }
        SortUtil.swap(arr, left, lt);
        sort3Ways(arr, left, lt - 1);
        sort3Ways(arr, gt, right);
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArray(1000,0,1000);
//        sort3Ways(arr);
        sort(arr);
        System.out.println(SortUtil.isSorted(arr));
        SortUtil.printArr(arr);
    }

}
