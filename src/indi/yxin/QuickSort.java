package indi.yxin;

import java.util.Arrays;
import java.util.Random;

/**
 * 快排的基本实现
 * 快速排序最差情况，近乎有序的时候，退化为 O(n^2)
 * 当数组中重复元素很多时，快速排序也会退化为 O(n^2)
 */
public class QuickSort {

    // 加强版快速排序，三路排序
    // n 代表排序的数目
    public static <T extends Comparable<? super T>> void quickSort3Ways(T[] arr, int n) {
        quickSort3Ways(arr, 0, n - 1);
    }

    /**
     * 三路快排处理 arr[left,right]
     * 将arr[left,right]分为 <v; ==v; > v 三部分
     * 之后递归对 <v; >v 两部分进行三路快排，省去了对 ==v 进行判断的过程
     * @param arr
     * @param left
     * @param right
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void quickSort3Ways(T[] arr, int left, int right) {
        if (right - left <= 15) {
            InsertSort.insertSort(arr, left, right);
            return;
        }
        // 从下面开始 partition 的步骤
        Random random = new Random(System.currentTimeMillis());
        SortUtil.swap(arr, left, random.nextInt(right - 1) % (right - left + 1) + left);
        T v = arr[left];

        int lt = left; // arr[left + 1, lt] < v
        int gt = right + 1; // arr[gt, right] > v
        int i = left + 1; // arr[lt + 1, i) = v
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                SortUtil.swap(arr, i, left + 1);
                lt ++;
                i ++;
            } else if (arr[i].compareTo(v) > 0) {
                SortUtil.swap(arr, i, gt - 1);
                gt --;
            } else { // arr[i] == v
                i ++;
            }
        }
        SortUtil.swap(arr, left, lt);
        quickSort3Ways(arr, left, lt - 1);
        quickSort3Ways(arr, gt, right);
    }

    public static <T extends Comparable<? super T>> void quickSort(T[] arr, int n) {
        quickSort(arr, 0, n - 1);
    }

    /**
     * 对 arr[left,right] 进行快速排序
     * @param arr
     * @param left
     * @param right
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void quickSort(T[] arr, int left, int right) {
//        if (left >= right) {
//            return;
//        }
        /**
         * 高级排序在数据量较少时可以采用插入排序来提高效率
         * 所以把上述代码改为下面的，同归并排序
         * 第 ① 个优化点
         */
        if (right - left <= 15) {
            InsertSort.insertSort(arr, left, right);
            return;
        }

//        int p = partition(arr, left, right);
//        quickSort(arr, left, p - 1);
//        quickSort(arr, p + 1, right);
        // 使用双路快排
        int p = partition2(arr, left, right);
        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    /**
     * 对 arr[left,right] 进行 partition
     * 返回 p,使得 arr[l,p-1] < arr[p],arr[p+1,r]>arr[p]
     * @param arr
     * @param left
     * @param right
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> int partition(T[] arr, int left, int right) {

        /**
         * 优化点 ②
         * 能够解决当数组近乎有序时，快速排序退化为 o(n^2) 的问题
         * 不是完全解决，但是退化为 O(n^2) 的概率大大减小，几乎为0
         * 效率能够接近于归并排序，但是还是不如归并排序的
         */
        Random random = new Random(System.currentTimeMillis());
        SortUtil.swap(arr, left, random.nextInt(right + 1) % (right - left + 1) + left);

        T tmp = arr[left];
        // arr[l+1,p] < tmp, arr[p+1,r)>tmp
        int p = left;
        for (int i = left + 1; i <= right; i ++) {
            if (arr[i].compareTo(tmp) < 0) {
                SortUtil.swap(arr, p + 1, i);
                p ++;
            }
        }

        SortUtil.swap(arr, left, p);
        return p;
    }

    /**
     * 改进上一版 partition，能够解决数组中存在大量重复元素带来的，时间复杂度退化的问题
     * 双路快排
     * @param arr
     * @param left
     * @param right
     * @param <T>
     */
    private static <T extends Comparable<? super T>> int partition2(T[] arr, int left, int right) {
        Random random = new Random(System.currentTimeMillis());
        SortUtil.swap(arr, left, random.nextInt(right + 1) % (right - left + 1) +left);
        T tmp = arr[left];
        // arr[left + 1, i) <= tmp; arr(j,right] >= tmp
        // i,j 是需要考虑的那个位置
        int i = left + 1;
        int j = right;
        while (true) {
            while (i <= right && arr[i].compareTo(tmp) < 0) i ++;
            while (j >= left + 1 && arr[j].compareTo(tmp) > 0) j --;
            if (i > j) break;
            SortUtil.swap(arr, i, j);
            i ++;
            j --;
        }
        SortUtil.swap(arr, left, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtil.genRandArr(10000, 0, 10000);
        Integer[] arrCopy = Arrays.copyOf(arr,arr.length);
        long start1 = System.currentTimeMillis();
        QuickSort.quickSort(arr, arr.length);
        long end1 = System.currentTimeMillis();
        System.out.println(SortUtil.isSorted(arr));
        System.out.println("双路快排 = " + (end1 - start1) + "ms");

        long start2 = System.currentTimeMillis();
        QuickSort.quickSort3Ways(arrCopy, arr.length);
        long end2 = System.currentTimeMillis();
        System.out.println(SortUtil.isSorted(arr));

        System.out.println("三路快排 = " + (end2 - start2) + "ms");


    }
}
