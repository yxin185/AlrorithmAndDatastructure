package indi.yxin;

/**
 * 选择排序，实现简单，时间复杂度为 O(n^2)
 */
public class SelectionSort{

    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        int length = arr.length;
        assert length > 0;
        for (int i = 0;i < length;i ++) {
            int minIndex = i;
            for (int j = i;j < length;j ++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // 交换两个索引为 i 和索引为 minIndex 的两个值
            SortUtil.swap(arr, i, minIndex);
        }
    }


    public static void main(String[] args) {
//        Integer[] arr = SortUtil.genRandArr(10000, 0, 10000);
//        // 泛型的本质是 Object，所以传入的参数应该是引用类型，int 不是引用类型，所以 int 数组要报错
//        long start = System.currentTimeMillis();
//        selectionSort(arr);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start + "ms");
//        SortUtil.printArr(arr);

        String[] str = {"A","b","C","D"};
        selectionSort(str);
        SortUtil.printArr(str);

        Double[] doubles = {1.1, 2.2, 3.2, 3.21};
        selectionSort(doubles);
        SortUtil.printArr(doubles);

    }


}
