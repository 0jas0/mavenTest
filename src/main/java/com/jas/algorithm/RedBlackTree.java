package com.jas.algorithm;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class RedBlackTree <K extends Comparable<? super K>, V>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;
        private Integer N;
        private boolean color;

        public Node(K key, V value, Integer num, boolean color) {
            this.key = key;
            this.value = value;
            this.N = num;
            this.color = color;
        }
    }

    /**
     * 插入一个节点
     * @param key
     * @param val
     */
    public void put(K key, V val){
        root = put(key, val, root);
        root.color = BLACK;
    }

    /**
     * 差人一个节点具体操作
     * @param key
     * @param val
     * @param node
     * @return
     */
    private Node put(K key, V val, Node node){
        if (node == null){
            return new Node(key, val, 1, RED);
        }
        int res = key.compareTo(node.key);
        if (res < 0){
            node.left = put(key, val, node.left);
        }else if (res > 0){
            node.right = put(key, val, node.right);
        }else {
            node.value = val;
        }
        if (isRed(node.left) && isRed(node.right)){
            node.left.color = BLACK;
            node.right.color = BLACK;
        }else if (isRed(node.left) && isRed(node.left.left)){
            rotateRight(node);
        }else if (isRed(node.right) && !isRed(node.left)){
            rotateLeft(node);
        }
        return node;
    }

    /**
     * 获取节点的数量
     * @return
     */
    public int size(){
        return size(root);
    }


    /**
     * 左旋
     * @param node
     * @return
     */
    private Node rotateLeft(Node node){
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.color = node.color;
        node.color = RED;
        rightNode.N = node.N;
        node.N = 1 + size(node.left) + size(node.right);
        return rightNode;
    }

    /**
     * 右旋
     * @param node
     * @return
     */
    private Node rotateRight(Node node){
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.color = node.color;
        node.color = RED;
        leftNode.N = node.N;
        node.N = 1 + size(node.left) + size(node.right);
        return leftNode;
    }

    /**
     * 判断 节点是否是红节点
     * @param node
     * @return
     */
    private boolean isRed(Node node){
        return node.color == RED;
    }

    /**
     * 获取一个节点下的子节点数（包含自己）
   * @param node
     * @return
     */
    private int size(Node node){
        if (node == null){
            return 0;
        }else {
            return node.N;
        }
    }
}
