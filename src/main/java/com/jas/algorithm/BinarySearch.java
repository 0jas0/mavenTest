package com.jas.algorithm;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class BinarySearch {
    /**
     * 二分查找非递归方式
     * @param a
     * @param key
     * @return
     */
    public static int rank(int a[], int key){
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (key < a[mid]){
                hi = mid - 1;
            }else if (key > a[mid]){
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5 , 8 ,10};
        int rank = rank(a, 8);
        System.out.println(rank);
    }
}
