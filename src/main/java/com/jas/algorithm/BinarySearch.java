package com.jas.algorithm;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class BinarySearch<Key extends Comparable<? super Key>, Val>{

    private Key[] keys;
    private Val[] vals;
    private int N;

    public BinarySearch(int maxNum) {
        keys = (Key[])new Comparable[maxNum];
        vals = (Val[])new Object();
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public Val get(Key key){
        if (isEmpty()){
            return null;
        }
        int index = rank(key);
        if (index < N && keys[index].compareTo(key) == 0){
            return vals[index];
        }else {
            return null;
        }
    }

    public void put(Key key, Val val){
        int index = rank(key);
        if (index < N && keys[index].compareTo(key) == 0){
            vals[index] = val;
            return;
        }

        for (int j = N; j > index; j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[index] = key;
        vals[index] = val;
        N++;
    }

    /**
     * 二分查找非递归方式
     * @param key
     * @return
     */
    public int rank(Key key){
        int lo = 0;
        int hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (key.compareTo(keys[mid]) < 0){
                hi = mid - 1;
            }else if (key.compareTo(keys[mid]) > 0){
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        return lo;
    }

}
