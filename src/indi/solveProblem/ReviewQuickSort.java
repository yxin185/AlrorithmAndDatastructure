package indi.solveProblem;

import java.util.Random;

import static indi.sort.SortUtil.swap;

/**
 * @Description 回忆一下快速排序，只对整型进行排序。对 Comparable 还要写比较函数
 * @Date 2019/11/29 15:25
 **/
public class ReviewQuickSort {
    public static void sort(Integer[] arr) {
        if (arr.length == 0) return;
        // 辅助数组，对0~N-1进行快排
        sortHelp(arr, 0, arr.length - 1);
    }

    private static void sortHelp(Integer[] arr, int L, int R) {
        if (L >= R) return;

        int p = partition(arr, L, R);
        sortHelp(arr, L, p - 1);
        sortHelp(arr, p + 1, R);
    }

    private static int partition(Integer[] arr, int L, int R) {
        // 在数组中出现大量重复元素，导致效率降低
        Random random = new Random(System.currentTimeMillis());
        swap(arr, L, random.nextInt(R + 1) % (R - L + 1) + L);
        Integer v = arr[L];
        int i = L + 1;
        int j = R;
        while (true) {
            while (i <= R && arr[i] < v) i ++;
            while (j >= L && arr[j] > v) j --;
            if (i >= j) break;
            swap(arr, i, j);
            i ++;
            j --;
        }
        swap(arr, L, j);
        // 返回标定点的索引
        return j;
    }

    public static void print(Integer[] arr) {
        for (int i = 0;i < arr.length;i ++) {
            System.out.print(arr[i] + " ");
        }
    }

    // 三路快速排序
    public static void sort3Ways(Integer[] arr) {
        sort3WaysHelper(arr, 0, arr.length - 1);
    }

    private static void sort3WaysHelper(Integer[] arr, int L, int R) {
        if (L >= R) return;
        Random random = new Random(System.currentTimeMillis());
        swap(arr, L, random.nextInt(R + 1) % (R - L + 1) + L );
        Integer v = arr[L];
        int lt = L;
        int gt = R + 1;
        int i = L + 1;
        while (i < gt) {
            if (arr[i] < v) {
                swap(arr, i, lt + 1);
                lt ++;
                i ++;
            } else if (arr[i] > v) {
                swap(arr, i, gt - 1);
                gt --;
            } else {
                i ++;
            }
        }
        swap(arr, L, lt);
        sort3WaysHelper(arr, L, lt - 1);
        sort3WaysHelper(arr, gt, R);
    }

    public static void main(String[] args) {
        Integer[] arr = {1,5,2,4,8,4,6,3};
        sort3Ways(arr);
        print(arr);
    }
}
