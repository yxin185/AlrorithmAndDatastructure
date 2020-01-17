package indi.sort;

public class IndexMinHeap<Item extends Comparable> {
    private Item[] data;    // 记录堆数据
    private int[] indexes;  // 记录堆元素的索引
    private int[] reverse;  // 反向索引
    private int count;      // 记录堆元素个数
    private int capacity;   // 记录堆容量
    // 堆索引从 1 开始
    public IndexMinHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        count = 0;
        this.capacity = capacity;
        for (int i = 1;i < indexes.length;i ++) {
            indexes[i] = i;
        }
        for (int i = 0;i < reverse.length;i ++) {
            reverse[i] = 0;
        }
    }

    // 在位置 i 插入元素 item
    public void insert(int i, Item item) {
        assert i > 0 && i + 1 <= capacity;
        i += 1; // 对外界来说，索引是从 0 开始的
        data[i] = item;
        count ++;
        // 插入元素的位置就位新的索引的值
        indexes[count] = i;
        reverse[i] = count;
        // 这个 count 是 indexes 数组中的第 count 个元素
        shiftUp(count);
    }
    // 抽取最小堆中的顶点元素
    public Item extractMin() {
        assert count > 0;
        Item ret = data[indexes[1]];
        swapIndex(indexes, 1, count);
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0;
        count --;
        // 这个 1 是 indexes 数组中的第一个元素
        shiftDown(1);
        return ret;
    }
    // 获取最小元素的索引
    public int getMinIndex() {
        assert count > 0;
        return indexes[1] - 1;
    }
    // 获取索引为 index 处的元素
    public Item getItem(int index) {
        assert contain(index);
        return data[index + 1];
    }

    /*
    最小索引堆核心辅助函数
     */
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * count;
            if (j + 1 <= count && data[indexes[j + 1]].compareTo(data[indexes[j]]) < 0); {
                j += 1;
            }
            if (data[indexes[k]].compareTo(data[indexes[j]]) > 0) {
                swapIndex(indexes, k, j);
            }
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;
            k = j;
        }
    }
    private void shiftUp(int k) {
        while (k > 1) {
            if (data[indexes[k / 2]].compareTo(data[indexes[k]]) > 0) {
                // 交换索引位置，相当于交换了 data 元素的位置
                swapIndex(indexes, k / 2, k);
            }
            reverse[indexes[k / 2]] = k / 2;
            reverse[indexes[k]] = k;
        }
    }

    private void swapIndex(int[] indexes, int i, int j) {
        assert i > 0 && i < count + 1;
        assert j > 0 && j < count + 1;

        int t = indexes[i];
        indexes[i] = indexes[j];
        indexes[j] = t;
    }

    public boolean contain(int index) {
        assert index + 1 >= 1 && index + 1 <= capacity;
        return reverse[index + 1] != 0;
    }

    public void change(int i, Item item) {
        assert contain(i);
        i += 1;
        data[i] = item;
        // 找到 indexes[j] = i,j代表data[i]在堆中的位置
        int j = reverse[i];
        shiftDown(j);
        shiftUp(j);
    }
}
