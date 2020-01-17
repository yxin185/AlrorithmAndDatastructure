package indi.sort;

public class Insertion {
    public static void sort(Comparable[] arr) {
        int L = arr.length;
        for (int i = 1;i < L;i ++) {
            for (int j = i; j > 0 && SortUtil.less(arr[j], arr[j - 1]); j --) {
                SortUtil.swap(arr, j, j - 1);
            }
        }
    }

    // arr[left, right] 闭区间进行排序
    public static void sort(Comparable[] arr, int left, int right) {
        for (int i = left + 1;i <= right;i ++) {
            // 每一次都让 j 去与他的前一个元素进行比较，交换
            for (int j = i;j > left && SortUtil.less(arr[j], arr[j - 1]); j --) {
                SortUtil.swap(arr, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArray(1000, 0, 1000);
        // 只对前10个数字进行排序
        sort(arr, 0, 9);
        System.out.println(SortUtil.isSorted(arr));
        SortUtil.printArr(arr);
    }

}
