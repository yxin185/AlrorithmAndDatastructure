package indi.solveProblem;

/**
 * @Description 使用归并排序思想解决小和问题
 * @Date 2019/11/29 11:02
 **/
public class smallSum {
    private static int[] help;  // 辅助数组

    public static int sort(int[] arr) {
        help = new int[arr.length];
        // 合并 arr[0,arr.length-1]
        return mergeHelper(arr, 0, arr.length - 1);
    }

    private static int mergeHelper(int[] arr, int L, int R) {
        // 还是一样，当个数小于15时，可以采用插入排序\
        // 解决这个问题，暂时可以不用
        // L >= R 说明只有一个元素待分治了，就可以先退出
        if (L >= R) return 0;
        // 移位运算符的优先级低于 + ，所以移位的先括起来！！！！！！！！！
        int mid = L + ((R - L) >> 1);
        return mergeHelper(arr, L, mid) +
                mergeHelper(arr, mid + 1, R) +
                merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int L, int mid, int R) {
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        int sum = 0;
        while (p1 <= mid && p2 <= R) {
            // 关键步骤
            sum += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i ++] = arr[p1] < arr[p2] ? arr[p1 ++] : arr[p2 ++];
        }
        while (p1 <= mid) {
            help[i ++] = arr[p1 ++];
        }
        while (p2 <= R) {
            help[i ++] = arr[p2 ++];
        }
        for (i = 0;i < R - L + 1;i ++) {
            arr[L + i] = help[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,6,5,4};
        int res = sort(arr);
        System.out.println(res);
    }
}
