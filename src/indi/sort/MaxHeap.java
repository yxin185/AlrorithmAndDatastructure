package indi.sort;


public class MaxHeap {

    private Comparable[] data;
    private int count;
    private int capacity;

    public MaxHeap(){}

    public MaxHeap(int capacity) {
        // 堆索引从 1 开始
        data = new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }
    // 给定一个数组，让其形成堆的形状的过程： heapify
    public MaxHeap(Comparable[] arr, int n) {
        data = new Comparable[n + 1];
        for (int i = 0;i < n;i ++) {
            data[i + 1] = arr[i];
        }
        count = n;
        // 从最后一个非叶子节点开始进行下沉操作
        for (int i = count / 2;i >= 1;i --) {
            shiftDown(i);
        }
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Comparable v) {
        // 确保这一次能够添加成功，最好能够动态分配空间
        assert count + 1 <= capacity;
        data[count + 1] = v;
        count ++;
        // 因为新元素的添加可能导致整个堆的性质发生改变，所以我们将 count 这个位置的元素进行“上移”操作
        shiftUp(count);
    }

    private void shiftUp(int index) {
        // 要确保索引范围，在这里，索引为2时，就是去和索引为1的节点(根节点)进行比较
        while (index > 1 && SortUtil.less(data[parent(index)], data[index])) {
            SortUtil.swap(data, parent(index), index);
            index /= 2;
        }
    }
    // 取出堆中最大的元素
    public Comparable extractMax() {
        assert count > 0;
        Comparable ret = data[1];
        SortUtil.swap(data, 1, count);
        count --;
        shiftDown(1);
        return ret;
    }
    // 对索引为 index 的元素执行下沉操作
    private void shiftDown(int index) {
        // 若子节点存在且找出最大的子节点
        while (2 * index <= count) {
            int j = 2 * index;// 此轮循环中，data[index] 应该和 data[j] 交换位置
            if (j + 1 <= count && SortUtil.less(data[j], data[j + 1])) {
                // 右孩子存在且右孩子大于左孩子
                j += 1;
            }
            if (!SortUtil.less(data[index], data[j])) break;
            SortUtil.swap(data, index, j);
            index = j;
        }
    }

    // 返回索引为 index 的元素的父节点位置，堆索引从 1 开始
    private int parent(int index) {
        return index / 2;
    }
    public void heapSort1(Comparable[] arr, int n) {
        MaxHeap maxHeap = new MaxHeap(n);
        for (int i = 0;i < n;i ++) {
            maxHeap.insert(arr[i]);
        }

        for (int i = n - 1;i >= 0;i --) {
            arr[i] = maxHeap.extractMax();
        }
    }
    public void heapSort2(Comparable[] arr, int n) {
        // 使用构造函数让这个堆直接成为一个最大堆
        MaxHeap maxHeap = new MaxHeap(arr, n);
        for (int i = n - 1;i >= 0;i --) {
            arr[i] = maxHeap.extractMax();
        }
    }
    public void heapSort(Comparable[] arr, int n) {
        // 索引从 0 开始 heapify
        for (int i = (n - 1) / 2; i >= 0;i --) {
            shiftDown(arr, n, i);
        }
        for (int i = n - 1;i > 0;i --) {
            // 把当前堆中最大元素放到最后
            SortUtil.swap(arr, 0, i);
            // 对堆中剩下的 i 个元素中的第 0 个元素进行下沉操作
            shiftDown(arr, i, 0);
        }
    }
    // 对arr中n个元素中的第 index 个元素进行下沉
    private void shiftDown(Comparable[] arr, int n, int index) {
        while (2 * index + 1 < n) {
            int j = 2 * index + 1; // 在此轮循环中，arr[index] 和 arr[j] 交换位置
            if (j + 1 < n && SortUtil.less(arr[j], arr[j + 1]))
                j += 1;

            if (!SortUtil.less(arr[index], arr[j])) {
                break;
            }
            SortUtil.swap(arr, j, index);
            index = j;
        }
    }

    public static void main(String[] args) {
        Comparable[] arr1 = SortUtil.generateRandomArray(1000000, 0, 1000000);
        Comparable[] arr2 = SortUtil.getCopy(arr1);
        Comparable[] arr3 = SortUtil.getCopy(arr1);
        MaxHeap maxHeap1 = new MaxHeap();
        MaxHeap maxHeap2 = new MaxHeap(100000);
        MaxHeap maxHeap3 = new MaxHeap(arr3, arr3.length);

        long start1 = System.currentTimeMillis();
        maxHeap1.heapSort(arr1, arr1.length);
        long end1 = System.currentTimeMillis();
        System.out.println("最优化：" + (end1 - start1) + "ms");
        System.out.println(SortUtil.isSorted(arr1));

        long start2 = System.currentTimeMillis();
        maxHeap2.heapSort1(arr2, arr2.length);
        long end2 = System.currentTimeMillis();
        System.out.println("没优化：" + (end2 - start2) + "ms");
        System.out.println(SortUtil.isSorted(arr2));

        long start3 = System.currentTimeMillis();
        maxHeap3.heapSort2(arr3, arr3.length);
        long end3 = System.currentTimeMillis();
        System.out.println("次优化：" + (end3 - start3) + "ms");
        System.out.println(SortUtil.isSorted(arr3));
    }
}
