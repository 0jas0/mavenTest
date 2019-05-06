package com.jas.algorithm;

/**
 * 优先队列
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class MaxPQ<T extends Comparable<? super T>>{
    private T[] pq;
    private int length = 0;

    public MaxPQ(int maxNum) {
        pq = (T[]) new Comparable[maxNum];
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public int size(){
        return length;
    }

    public void insert(T val){
        pq[++length] = val;
        swim(length);

    }

    public T delMax(){
        T max = pq[1]; // 根结点获取最大值
        exch(1, length);// 交换最大值和最后一个节点
        pq[length--] = null;
        sink(1);// 灰度堆的熟悉
        return max;
    }

    /**
     * 上浮
     * @param k
     */
    public void swim(int k){
        while (k > 1){
            if (pq[k].compareTo(pq[k/2]) > 0){
                exch(k, k/2);
            }
            k = k/2;
        }

    }

    /**
     * 下沉
     * @param k
     */
    public void sink(int k){
        while (2*k < length){
            int j = 2*k;
            if (pq[2*k].compareTo(pq[2*k+1]) < 0){
                j++;
            }
            if (pq[k].compareTo(pq[j]) < 0){
                exch(k, j);
            }
            k = j;
        }
    }

    /**
     * 交换 位置i 和 位置j的值
     * @param i
     * @param j
     */
    public void exch(int i, int j){
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

}
