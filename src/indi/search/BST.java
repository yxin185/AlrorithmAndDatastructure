package indi.search;

import java.util.LinkedList;
import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value extends Comparable<Value>> {
    // 先创建一个 Node 节点类，存放键值对
    private class Node {
        Key key;    // 节点值
        Value value;
        Node left;  // 左孩子
        Node right; // 右孩子

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    // 记录树中元素的个数
    private int count;

    public BST() {
        root = null;
        count = 0;
    }
    public int size() {
        return count;
    }
    public boolean isEmpty() {
        return count == 0;
    }
    // 向二分搜索树中插入元素 e
    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }
    // 向以 node 为根节点的树中插入元素 e，返回插入该元素后二叉搜索树的根
    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            count ++;
            return new Node(key, value);
        }

        if (node.key.equals(key)) {
            node.key = key;
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        return node;
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }
    private boolean contains(Node node, Key key) {
        if (node == null)
            return false;

        if (key.equals(node.key)) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else {
            return contains(node.right, key);
        }
    }

    // 根据 键 查找对应的值
    public Value search(Key key) {
        return search(root, key);
    }
    private Value search(Node node, Key key) {
        if (node == null)
            return null;

        if (key.equals(node.key)) {
            return node.value;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else
            return search(node.right, key);
    }

    public void preOrder() {
        preOrder(root);
    }
    // 对以 node 为根的二叉树进行前序遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }
    public void postOrder() {
        postOrder(root);
    }
    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.key);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
    // 寻找最小的键值
    public Key getMinimum() {
        if (count <= 0) {
            throw new IllegalArgumentException("Empty Tree.");
        }
        Node node = getMinimum(root);
        return node.key;
    }
    // 返回最小键值所在的节点
    private Node getMinimum(Node node) {

        if (node.left == null)
            return node;

        return getMinimum(node.left);
    }

    public Key getMaximum() {
//        assert count != 0;
        if (count <= 0) {
            throw new IllegalArgumentException("Empty Tree.");
        }
        Node node = getMaximum(root);
        return node.key;
    }
    private Node getMaximum(Node node) {
        if (node.right == null)
            return node;
        return getMaximum(node.right);
    }
    public Key removeMin() {
        Key ret = getMinimum();
        root = removeMin(root);
        return ret;
    }
    // 删除以 node 为根节点的树中的最小节点，并返回这个新树的根节点
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            count --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public Key removeMax() {
        Key ret = getMaximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            count --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }
    // O(log n)
    public void remove(Key key) {
        root = remove(root, key);
    }

    private Node remove(Node node, Key key) {
        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { // key = node.key
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                count --;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                count --;
                return leftNode;
            }
            // 进入下面部分说明左右子树均不为空
            // 将右子树中最小节点赋值 给 successor,然后将这个最小节点删除
            Node successor = getMinimum(node.right);
            // 删除这个最小节点之后，新的子树节点赋值给 successor 的右孩子
            successor.right = removeMin(node.right);
            successor.left = node.left;
            count --;
            return successor;
        }
    }


}
