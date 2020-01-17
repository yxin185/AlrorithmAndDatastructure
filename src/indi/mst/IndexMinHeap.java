package indi.mst;

public class IndexMinHeap<Item extends Comparable> {
    private Item[] data;    // 最小索引堆中的数据
    private int[] indexes;  // 最小堆中的索引, indexes[x] = i 代表索引 i 在 x 的位置
    private int[] reverse;  // 最小堆中的反向索引, reverse[i] = x 代表索引 i 在 x 的位置
    private int count;
    private int capacity;

    public IndexMinHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0;i < capacity + 1;i ++) {
            reverse[i] = 0;
        }
        count = 0;
        this.capacity = capacity;
    }

    public int getSize() {
        return count;
    }
    public boolean isEmpty() {
        return count == 0;
    }
    // 向最小索引堆中插入一个新元素，新元素索引为 i,元素为item
    // 传入的i对用户而言，是从0索引开始的
    public void insert(int i, Item item) {
        assert count + 1 <= capacity;
        assert i + 1 >= 1 && i + 1 <= capacity;
        // 在插入一个元素之前，确保那个位置没有元素
        assert !contain(i);
        i += 1;
        data[i] = item;
        indexes[count + 1] = i;
        reverse[i] = count + 1;
        count ++;
        shiftUp(count);
    }
    // 从堆顶取出最小元素
    public Item extractMin(){
        assert count > 0;
        Item ret = data[indexes[1]];
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count --;
        shiftDown(1);
        return ret;
    }
    public int extractMinIndex() {
        assert count > 0;
        int ret = indexes[1] - 1;
        swapIndexes(1, count);
        reverse[indexes[count]] = 0;
        count --;
        shiftDown(1);
        return ret;
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) < 0) {
                j ++;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) <= 0) {
                break;
            }
            swapIndexes(k, j);
            k = j;
        }
    }

    // 索引堆中，数据之间的比较根据 data 的大小进行比较，但实际操作的是索引
    private void shiftUp(int k) {
        while (k > 1) {
            if (data[indexes[k / 2]].compareTo(data[indexes[k]]) > 0) {
                swapIndexes(k / 2, k);
            }
            k /= 2;
        }
    }

    private void swapIndexes(int i, int j) {
        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;

        reverse[indexes[i]] = i;
        reverse[indexes[j]] = j;
    }
    // 看索引i所在的位置是否存在元素
    private boolean contain(int i) {
        assert i + 1 >= 1 && i + 1 <= capacity;
        return reverse[i + 1] != 0;
    }
    // 获取最小堆中的最小元素
    public Item getMin() {
        assert count > 0;
        return data[indexes[1]];
    }
    // 获取最小元素即堆顶元素的索引
    public int getMinIndex() {
        assert count > 0;
        return indexes[1] - 1;
    }
    // 获取堆中索引为 i 的元素
    public Item getItem(int i) {
        assert contain(i);
        // 外部人看来的 i 是从 0 开始的
        return data[i + 1];
    }
    // 将最小索引堆中索引为i的元素修改为newItem
    public void change(int i, Item newItem) {
        assert contain(i);
        i += 1;
        data[i] = newItem;
        // 有了 reverse 以后
        // 我们可以非常简单的通过 reverse直接定位索引 i 在 indexes 中的位置
        shiftUp(reverse[i]);
        shiftDown(reverse[i]);
    }
}
