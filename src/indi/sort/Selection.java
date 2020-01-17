package indi.sort;

public class Selection {
    // 使用 Comparable 接口可以实现多种实现 Comparable 接口类型的元素比较
    public static void sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = 0; i < length;i ++) {
            int minIndex = i;
            for (int j = i;j < length;j ++) {
                if (SortUtil.less(arr[j], arr[minIndex])) {
                    minIndex = j;
                }
            }
            SortUtil.swap(arr, i, minIndex);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortUtil.generateRandomArray(10000, 0, 10000);
        sort(arr);
        SortUtil.printArr(arr);
        System.out.println(SortUtil.isSorted(arr));
    }
}
