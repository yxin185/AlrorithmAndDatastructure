package indi.sort;

/**
 * @Description 冒泡排序
 * @Date 2020/1/17 14:30
 * @Author yxin
 **/
public class Bubble {

    public static void sort(Comparable[] arr) {
        if (arr == null || arr.length <= 1) return;
        int L = arr.length;
        // i 代表一共要进行排序的次数为 L 次
        for (int i = 0;i < L - 1;i ++) {
            // j 代表每一次我都从第一个数开始往后遍历，比较大小
            for (int j = 0;j < L - 1 - i;j ++) {
                if (SortUtil.less(arr[j + 1], arr[j]))
                    SortUtil.swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArray(1000, 0, 2000);
        sort(arr);
        boolean sorted = SortUtil.isSorted(arr);
        System.out.println(sorted);
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
    }
}
