package com.jas.algorithm;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class ArraySort {

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length -1; i++){
            int min = i;
            for (int j = i + 1; j < arr.length; j++){
                if (judgeVal(arr[min], arr[j])){
                    swap(arr, min, j);
                }
            }
        }
    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            int temp = arr[i];
            int j = i;
            for (;j > 0 && judgeVal(arr[j-1],temp); j--){
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }

    /**
     * 希尔排序
     * @param arr
     */
    public static void shellSort(int[] arr){
        int h = 1;
        while (h < arr.length/3){
            h = 3*h + 1;
        }
        while (h >= 1){
            for (int i = h; i < arr.length; i++){
                int temp = arr[i];
                int j = i;
                for (;j >= h && judgeVal(arr[j - h], temp);){
                    arr[j] = arr[j- h];
                    j = j - h;
                }
                arr[j] = temp;
            }
            h = h/3;
        }
    }

    /**
     * 自顶向下 归并排序数组
     * @param arr
     * @param lo
     * @param hi
     */
    public static void mergeSort1(int arr[], int lo, int hi){
        int mid = lo + (hi - lo)/2;
        if (lo >= hi){
            return;
        }
        mergeSort1(arr, lo, mid); // 左半归并排序
        mergeSort1(arr, mid + 1, hi); // 右半归并排序
        merge(arr, lo, mid, hi); //合并左边和右边
    }

    /**
     * 自低向上 归并排序
     * @param arr
     */
    public static void mergeSort2(int arr[]){
        int length = arr.length;
        for (int size = 1; size < length; size += size){ //子数组的长度
            for (int lo = 0; lo < length - size; lo = lo + size*2){ //子数组的低位索引的下标
                merge(arr, lo, lo + size - 1, Math.min(lo + size*2 -1, length - 1));
            }
        }
    }

    /**
     * 快速排序
     * @param arr
     * @param lo
     * @param hi
     */
    public static void fastSort(int[] arr, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int i = lo, j = hi + 1;
        int val = arr[lo];
        while (true){
            while (judgeVal(arr[--j],val)){
                if (j == lo){
                    break;
                }
            }
            while (judgeVal(val,arr[++i])){
                if (i == hi){
                    break;
                }
            }
            if (i >= j){
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        fastSort(arr, lo, j - 1);
        fastSort(arr, j + 1, hi);
    }

    /**
     * 堆排序
     * @param arr
     */
    public static void stackSort(int[] arr){
        // 1、构建大顶堆
        for (int i = arr.length/2 -1 ; i >= 0; i--){
            adjustArr(arr, i, arr.length); // 从第一个非叶子结点从下至上，从右至左调整结构
        }
        // 2、调整堆的结构和交换
        for (int i = arr.length - 1; i > 0; i--){
            swap(arr, 0, i);
            adjustArr(arr, 0, i);
        }
    }

    public static void adjustArr(int[] arr, int i, int length){
        int temp = arr[i];
        for (int k = 2*i + 1; k < length; k = 2*k + 1){
            if (k + 1< length && arr[k] < arr[k+1]){
                k = k+1;
            }
            if (temp < arr[k]){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 合并两个有序的数组
     * @param arr
     * @param lo
     * @param mid
     * @param hi
     */
    public static void merge(int[] arr, int lo, int mid, int hi){
        int[] tempArr = new int[arr.length];
        // 把数据存在临时数组中
        for (int i = 0; i < arr.length; i++){
            tempArr[i] = arr[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            if (i > mid){
                arr[k] = tempArr[j++];
            }else if (j > hi){
                arr[k] = tempArr[i++];
            }else if (judgeVal(tempArr[i],tempArr[j])){
                arr[k] = tempArr[j++];
            }else {
                arr[k] = tempArr[i++];
            }
        }
    }

    /**
     * 判断a 是否大于 b
     * @param a
     * @param b
     * @return
     */
    private static boolean judgeVal(int a, int b){
        return a > b;
    }

    /**
     * 交换两个下标的值
     * @param arr
     * @param indexA
     * @param indexB
     */
    public static void swap(int[] arr, int indexA, int indexB){
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 0, 2, 9};
        stackSort(arr);
        System.out.println(arr);
    }
}
