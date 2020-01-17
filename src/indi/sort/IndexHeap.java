package indi.sort;

public class IndexHeap {
    private Comparable[] data;
    private Integer[] indexes; // 索引数组
    // reverse[i] 表示索引 i 在 indexes 中的位置
    // indexes[i] = j, reverse[j] = i
    // indexes[reverse[i]] = i; reverse[indexes[i]] = i
    // 反向查找
    private Integer[] reverse;
    private int count;
    private int capacity;
    // k 描述的是索引数组的位置
    private void shiftUp(int k) {
        while (k > 1 && SortUtil.less(data[indexes[k / 2]], data[indexes[k]])) {
            SortUtil.swap(indexes, k / 2, k);
            reverse[indexes[k / 2]] = k / 2;
            reverse[indexes[k]] = k;
            k /= 2;
        }
    }
    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k; // 此轮循环中，indexes[k] 和 indexes[j]交换位置
            if (j + 1 <= count && SortUtil.less(data[indexes[j]], data[indexes[j + 1]])) {
                j += 1;
            }
            if (!SortUtil.less(data[indexes[k]], data[indexes[j]]))
                break;
            SortUtil.swap(indexes, k, j);
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;
            k = j;
        }
    }
    private boolean contain(int index) {
        return reverse[index + 1] != 0;
    }


    public IndexHeap(int capacity) {
        // 第 0 个位置浪费
        data = new Comparable[capacity + 1];
        indexes = new Integer[capacity + 1];
        reverse = new Integer[capacity + 1];
        for (int i = 0;i <= capacity;i ++) {
            // 为 0 表示不存在
            reverse[i] = 0;
        }
        count = 0;
        this.capacity = capacity;
    }
    public int size() {
        return count;
    }
    public boolean isEmpty() {
        return count == 0;
    }
    // 传入的 i 对于用户而言是从 0 索引开始的
    public void insert(int index, Comparable v) {
        if (count + 1 > capacity) {
            System.out.println("堆已满，无法插入！");
            return;
        }
        if (index + 1 <= 1 && index + 1 > capacity) {
            System.out.println("非法的索引！");
            return;
        }
        // 我们内部堆索引从 1 开始，所以先给 index + 1
        index += 1;
        data[index] = v;
        indexes[count + 1] = index;
        reverse[index] = count + 1;
        count ++;
        shiftUp(count);
    }
    public Comparable extractMax() {
        if (count <= 0) {
            System.out.println("堆为空！");
            return null;
        }
        // indexes[1] 记录最大元素所在的位置
        Comparable ret = data[indexes[1]];
        SortUtil.swap(indexes, 1, count);
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0;
        count --;
        shiftDown(1);
        return ret;
    }

    // 索引堆特有，找到最大元素的索引，那么根据这个索引，我们就能够找到这个最大的元素
    public Integer extractMaxIndex() {
        if (count <= 0) {
            System.out.println("堆为空！");
            return -1;
        }
        // 对外部来说，index是从0开始的
        Integer ret = indexes[1] - 1;
        SortUtil.swap(indexes, 1, count);
        reverse[indexes[1]] = 1;
        reverse[indexes[count]] = 0;
        count --;
        shiftDown(1);
        return ret;
    }

    public Comparable getValue(int index) {
        assert contain(index);
        return data[index + 1];
    }
    // 修改堆中索引 index 处的元素为 v
    public void change(int index, Comparable v) {
        // 确保这个堆包含该索引
        assert contain(index);

        index += 1;
        data[index] = v;
        // 找到 indexes[j] = index, j 代表 data[index] 在堆中的位置
        // 之后 shiftUp(j),再 shiftDown(j)
//        for (int j = 1;j <= count;j ++ ){
//            if (indexes[j] == index) {
//                shiftDown(j);
//                shiftUp(j);
//                break;
//            }
//        }
        int j = reverse[index];
        shiftUp(j);
        shiftDown(j);
    }
}
