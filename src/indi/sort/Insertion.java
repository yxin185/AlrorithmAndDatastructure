package indi.sort;

public class Insertion {
    public static void sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = 1;i < length;i ++) {
            for (int j = i;j > 0 && SortUtil.less(arr[j],arr[j - 1]); j --) {
                SortUtil.swap(arr, j, j - 1);
            }
        }
    }
    // 闭区间 arr[left, right] 的排序
    public static void sort(Comparable[] arr, int left, int right) {
        for (int i = left;i <= right;i ++) {
            for (int j = i;j > 0 && SortUtil.less(arr[j], arr[j - 1]); j --) {
                SortUtil.swap(arr, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArray(10000, 0, 10000);
        sort(arr, 0, 10);
        System.out.println(SortUtil.isSorted(arr));
        SortUtil.printArr(arr);
    }

}
