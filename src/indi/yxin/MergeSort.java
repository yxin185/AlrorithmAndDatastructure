package indi.yxin;

/*
使用自顶向下的递归来实现归并
 */
public class MergeSort {
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        int length  = arr.length;
        // 定义一个辅助数组，相当于是 arr 的一个拷贝
        // 因为后面要使用到比较，所以这个数组需要是可以比较的
        T[] aux = (T[]) new Comparable[length];
        mergeSortHelper(arr, aux,0, length - 1);

//        mergeSortFromBottomToHead(arr, aux);
    }

    /**
     * 递归使用归并排序，对 arr[left,right] 的范围进行排序,前闭后闭的空间
     * @param arr:待排序数组
     * @param left:左边界
     * @param right:右边界
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void mergeSortHelper(T[] arr, T[] aux, int left, int right) {
        // 先考虑递归终止的情况
//        if (left >= right) {
//            return;
//        }
        /**
         * 当 需要排序的数量很少时，考虑采用插入排序来提高性能
         * 因为数量少，接近顺序的概率就大，且 O(n^2) 与 O(n log n) 当 n 很小时的差距就恨不明显，各自前面还有一个常数系数
         * 归并优化第二点
         */
        if (right - left <= 15) {
            InsertSort.insertSort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSortHelper(arr, aux,left, mid);
        mergeSortHelper(arr, aux,mid + 1, right);
        // 将左右两个数组 merge 起来
        // 考虑在什么情况下才需要 merge,以此提高归并性能？
        // 当 arr[mid] < arr[mid+1] 时，这个数组已经是排好序的，不需要归并
        // 所以如下能够提高性能，但是这种情况还是低于 插入排序
        // 归并优化第一点
        if (arr[mid].compareTo(arr[mid + 1]) > 0)
            merge(arr, aux, left, mid, right);
    }

    private static <T extends Comparable<? super T>> void merge(T[] arr, T[] aux, int left, int mid, int right) {

        for (int i = left; i <= right;i ++) {
            aux[i - left] = arr[i];
        }
        // 定义在辅助数组上移动的两个指针的索引
        int i = left;
        int j = mid + 1;
        // k 记录在 arr 上移动的指针的索引
        for (int k = left;k <= right;k ++) {
            // 首先对两个索引进行判断是否越界
            if (i > mid) {
                arr[k] = aux[j - left];
                j ++;
            } else if (j > right) {
                arr[k] = aux[i - left];
                i ++;
            } else if (aux[i - left].compareTo(aux[j - left]) < 0) {
                arr[k] = aux[i - left];
                i ++;
            } else {
                arr[k] = aux[j - left];
                j ++;
            }
        }
    }

    /**
     * 自底向上实现归并排序，效率低于自顶向下
     * 但是针对链表类数组，因为该函数没有涉及到数组索引，索引针对链表类数组有很好的性能
     * @param arr
     * @param aux
     * @param <T>
     */

    public static <T extends Comparable<? super T>> void mergeSortFromBottomToHead(T[] arr, T[] aux) {
        int length = arr.length;
        for (int sz = 1; sz <= length; sz += sz) {
            for (int i = 0; i + sz < length; i += sz + sz) {
                merge(arr, aux, i, i + sz - 1, Math.min(i + sz + sz - 1, length - 1));
            }
        }
    }


    public static void main(String[] args) {
        Integer[] arr = SortUtil.genRandArr(10000, 0, 10000);
//        SortUtil.printArr(arr);
        mergeSort(arr);
        SortUtil.printArr(arr);
        System.out.println(SortUtil.isSorted(arr));
    }
}
