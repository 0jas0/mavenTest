package com.jas.algorithm;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class BinarySearchTree<Key extends Comparable<? super Key>, Value>{
    private class Node{
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private Integer N;

        public Node(Key key, Value value, Integer n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }
    private Node root;

    public int size(){
        return size(root);
    }
    public int size(Node node){
        if (node == null){
            return 0;
        }else {
            return node.N;
        }
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public Value get(Key key){
        return get(key, root);
    }

    public Value get(Key key, Node node){
        if (node == null){
            return null;
        }
        int res = key.compareTo(node.key);
        if (res < 0){
            get(key, node.left);
        }else if (res > 0){
            get(key, node.right);
        }
        return node.value;
    }

    public Node getMinNode(Node node){
        if (node == null || node.left == null){
            return node;
        }
        return getMinNode(node.left);
    }

    public void put(Key key, Value value){
        root = put(key, value, root);
    }
    public Node put(Key key, Value value, Node node){
        if (node == null){
            return new Node(key, value, 1);
        }
        int res = key.compareTo(node.key);
        if (res < 0){
            node.left = put(key, value, node.left);
        }else if (res > 0){
            node.right = put(key, value, node.right);
        }else {
            node.value = value;
        }
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    public void del(Key key){
        root = del(key, root);
    }
    public Node del(Key key, Node node){
        if (node == null){
            return null;
        }
        int res = key.compareTo(node.key);
        if (res > 0){
            node.right = del(key, node.right);
        }else if (res < 0){
            node.left = del(key, node.left);
        }else if (node.left == null || node.right == null){ //删除的节点下最多只有一个子节点
            node = node.left == null ? node.right : node.left;
        }else {
            Node minNode = getMinNode(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = del(minNode.key, node.right);
        }
        node.N = size(node.left) + 1 + size(node.right);
        return node;
    }

    public int rank(Key key){
        return rank(key, root);
    }

    /**
     * 查找健的排名
     * @param key
     * @param node
     * @return
     */
    public int rank(Key key, Node node){
        if (node == null){
            return 0;
        }
        int res = key.compareTo(node.key);
        if (res < 0){
            return rank(key, node.left);
        }else if (res > 0){
            return size(node.left) + 1 + rank(key, node.right);
        }else{
            return size(node.left);
        }
    }

    public Key select(int n){
        Node node = select(root, n);
        return node == null ? null : node.key;
    }

    /**
     * 查找排名为n的节点
     * @param node
     * @param n
     * @return
     */
    public Node select(Node node, int n){
        if (node == null){
            return null;
        }
        int rank = rank(node.key);
        if (rank > n){
            return select(node.left, n);
        }else if (rank < n){
            return select(node.right, n - rank - 1);
        }
        return node;
    }
}
