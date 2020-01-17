package indi.solveProblem;

/**
 * @Description 打印一个数组中的逆序对
 * @Date 2019/11/29 13:31
 **/
public class PrintReversePair {
    private static int[] help;
    public static void print(int[] arr) {
        help = new int[arr.length];
        printHelper(arr, 0, arr.length - 1);
    }

    private static void printHelper(int[] arr, int L, int R) {
        if (L == R) return;
        int mid = L + ( (R - L) >> 1 );
        printHelper(arr, L, mid);
        printHelper(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int i = 0, p1 = L, p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
//            System.out.println("p1 = " + p1 + ", p2 = " + p2);
            // 如果 arr[p1] > arr[p2]，则 p1-mid的所有值与p2构成逆序对
            if (arr[p1] > arr[p2]) {
                for (int k = p1;k < mid + 1;k ++)
                    printPair(arr[k], arr[p2]);
            }
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
    }

    private static void printPair(int num1, int num2) {
        System.out.println("(" + num1 + "," + num2 + ")");
    }


    public static void main(String[] args) {
        int[] arr = {7,1,4,2,6,5,3};
        print(arr);
    }
}
