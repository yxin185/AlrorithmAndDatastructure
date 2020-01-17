package indi.yxin;

/**
 * 插入排序对几乎排序好的数组进行排序的效率很高，时间复杂度接近 O(n)
 */
public class InsertSort {

    public static <T extends Comparable<? super T>> void insertSort(T[] arr) {
        int length = arr.length;
        for (int i = 1;i < length;i ++) {
            for (int j = i;j > 0 && arr[j].compareTo(arr[j - 1]) < 0;j --) {
                SortUtil.swap(arr, j, j - 1);
            }
        }
    }

    /**
     * 对 arr[left,right]进行插入排序
     * @param arr
     * @param left
     * @param right
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] arr, int left, int right) {
        for (int i = left + 1;i <= right;i ++) {
            T tmp = arr[i];
            int j;
            for (j = i;j > left && arr[j - 1].compareTo(tmp) > 0;j --) {
                arr[j] = arr[j - 1];
            }
            arr[j] = tmp;
        }
    }


    public static void main(String[] args) {
        Integer[] arr = SortUtil.genRandArr(10000, 0, 10000);
        insertSort(arr);
        SortUtil.printArr(arr);
        System.out.println(SortUtil.isSorted(arr));
    }
}
