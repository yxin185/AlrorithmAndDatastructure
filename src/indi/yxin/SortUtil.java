package indi.yxin;

import java.util.Random;

public class SortUtil {

    // 交换 arr 中索引为 i 和 j 的元素位置
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T> void printArr(T[] arr) {
        for (T t : arr) {
            System.out.print(t + " ");
        }
        System.out.println();
        System.out.println("打印完成");
    }

    // 产生一个大小为 n，边界为 rangeL,rangeR的整形数组，左右闭区间
    public static  Integer[] genRandArr(int n, int rangeL, int rangeR) {
        Random random = new Random(System.currentTimeMillis());
        // 因为不能创建泛型数组，所以进行强制类型转换
        Integer[] arr = new Integer[n];
        for (int i = 0;i < n;i ++) {
            int rand = random.nextInt(rangeR + 1) % (rangeR - rangeL + 1) + rangeL;
            arr[i] = rand;
        }
        return arr;
    }

    // 判断一个数组是否是排序完成的
    public static boolean isSorted(Integer[] arr) {
        for (int i = 1;i < arr.length;i ++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

}
