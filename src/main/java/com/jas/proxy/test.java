package com.jas.proxy;

/**
 * Created by Administrator on 2017/11/7.
 */
public class test {
    public static boolean Find(int target, int [][] array) {
        for(int i=0;i<array.length;i++){
            for(int j=0;j < array[i].length;j++){
                if(target==array[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
    public String replaceSpace(StringBuffer str) {
        return str.toString().replaceAll(" ","%20");
    }
    public static void main(String[] args) {
        int[][] arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        boolean find = Find(15, arr);
        System.out.print(find);
    }
}
