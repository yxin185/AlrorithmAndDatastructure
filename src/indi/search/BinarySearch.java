package indi.search;


public class BinarySearch {
    // 找到 arr 中 target 的索引
    public static int binarySearch(Comparable[] arr, int n, Comparable target) {
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            // 避免整数越界
            int mid = l + (r - l) / 2;
            if (arr[mid].equals(target)) {
                return mid;
            }
            if (SearchUtil.less(arr[mid], target)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
    // 递归实现二分查找法，性能稍差，都是 log N
    public static int binarySearchRec(Comparable[] arr, int n, Comparable target) {
        return binarySearchRec(arr, 0, n - 1, target);
    }
    // arr[l,r]上查找
    private static int binarySearchRec(Comparable[] arr, int l, int r, Comparable target) {
        int mid = l + (r - l) / 2;
        if (l > r)
            return -1;
        if (arr[mid].equals(target))
            return mid;
        // 根据 mid 位置元素与 target 的大小调整左右边界，递归运行程序
        if (SearchUtil.less(arr[mid], target)) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
        return binarySearchRec(arr, l, r, target);
    }

    public static void main(String[] args) {
        Integer[] arr = {1,2,3,4,5,6};
        int j = binarySearchRec(arr, arr.length, 5);
        System.out.println(j);
    }
}
