package indi.mst;

public class MinHeap<Item extends Comparable> {
    private Item[] data;
    private int count;
    private int capacity;

    public MinHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }
    // 构造函数，通过一个数组构造一个最小堆
    public MinHeap(Item[] arr) {
        int n = arr.length;
        data = (Item[]) new Comparable[n + 1];
        capacity = n;
        for (int i = 0;i < n;i ++) {
            // 堆索引从 1 开始
            data[i + 1] = arr[i];
        }
        count = n;
        for (int i = n / 2;i >= 1;i --) {
            shiftDown(i);
        }
    }
    // 向最小堆中插入一个元素，因为插入后会破坏最小堆性质，每插一次，就要调整一次
    public void insert(Item e) {
        // 确保还能够插入一个元素
        assert count + 1 <= capacity;
        data[count + 1] = e;
        count ++;
        // 对新添加的元素进行上浮操作
        shiftUp(count);
    }
    public int getSize() {return count;}
    public boolean isEmpty() {return count == 0;}

    // 最小堆核心操作
    private void shiftUp(int index) {
        // 因为索引从 1 开始，索引为2时就去和1比较
        while (index > 1) {
            if (data[index].compareTo(data[index / 2]) < 0) {
                swap(data, index, index / 2);
            }
            index = index / 2;
        }
    }
    // 最小堆核心操作
    private void shiftDown(int index) {
        assert index > 0 && index < count;
        while (2 * index <= count) {
            int j = 2 * index;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) < 0) {
                j += 1;
            }
            if (data[index].compareTo(data[j]) > 0) {
                // index 要是更大，就往下面走
                swap(data, index, j);
            }
            index = j;
        }
    }
    public Item extractMin() {
        assert count > 0;
        Item ret = data[1];
        // 因为拿走最小元素之后，堆性质改变，重新调整
        // 这一步交换的操作可以换成赋值吗？
        // data[1] = data[count]，这样似乎能够提高效率
        swap(data, 1, count);
        count --;
        shiftDown(1);
        return ret;
    }

    // 交换数组中两个索引位置的元素
    private void swap(Item[] data, int i, int j) {
        Item tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
    // 测试最小堆函数
    public static void main(String[] args) {
//        Integer[] arr = {3,7,2,4,5,6,1};
//        MinHeap minHeap = new MinHeap(arr);
//        for (int i = 0;i < arr.length;i ++) {
//            System.out.print(minHeap.extractMin() + "\t");
//        }

        MinHeap<Integer> minHeap = new MinHeap<>(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for( int i = 0 ; i < N ; i ++ )
            minHeap.insert( new Integer((int)(Math.random() * M)) );

        Integer[] arr = new Integer[N];
        // 将minheap中的数据逐渐使用extractMin取出来
        // 取出来的顺序应该是按照从小到大的顺序取出来的
        for( int i = 0 ; i < N ; i ++ ){
            arr[i] = minHeap.extractMin();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

    }
}
