package com.jas.test;

import com.jas.bean.person;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2017/10/17.
 */
public class test {

    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)){
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        int stackSize = 0;
        LinkedList<Character> characters = new LinkedList<>();
        for (int i = 0 ;i < chars.length; i++){
            if (characters.contains(chars[i])){
                stackSize = characters.size();
                maxLength = Math.max(stackSize, maxLength);
                Character character = characters.removeFirst();
                while (character != null){
                    if (Character.compare(character, chars[i]) == 0){
                        break;
                    }
                    character = characters.removeFirst();
                }
            }
            characters.addLast(chars[i]);
        }
        maxLength = Math.max(characters.size(), maxLength);
        return maxLength;
    }

    /**
     * 寻找两个数组的中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] newArr = new int[length];
        int k = 0;
        for (int i = 0,j = 0; i < nums1.length || j < nums2.length;){
            if (i == nums1.length){
                newArr[k++] = nums2[j];
                j++;
                continue;
            }
            if (j == nums2.length){
                newArr[k++] = nums1[i];
                i++;
                continue;
            }
            if (nums1[i] < nums2[j]){
                newArr[k++] = nums1[i];
                i++;
            }else if (nums1[i] > nums2[j]){
                newArr[k++] = nums2[j];
                j++;
            }else {
                newArr[k++] = nums2[j];
                newArr[k++] = nums1[i];
                i++;
                j++;
            }
        }
        if (length % 2 == 0){
            int index1 = length/2;
            int index2 = index1-1;
            return (newArr[index1] + newArr[index2])/2.0;
        }else {
            int index = length/2;
            return newArr[index];
        }

    }

    public String longestPalindrome(String s) {
        return "";
    }

    public static void main(String[] args) {
        test t = new test();
        String str = t.longestPalindrome("aabbccdd");
        System.out.println(str);
    }
    @Test
    public void aa(){
        List<person> lists = new ArrayList<person>();
        lists.add(new person(10,"black",1,178));
        lists.add(new person(11,"red",0,180));
        lists.add(new person(12,"yellow",1,190));
        lists.add(new person(13,"blue",0,168));
        lists.add(new person(14,"white",1,172));
    }

    @Test
    public void bb(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        String s7 = s3 + "ming";
        System.out.println(s1 == s2);               //false 10.
        System.out.println(s1 == s5);               //true 11.
        System.out.println(s1 == s6);              //false 12.
        System.out.println(s1 == s7);              //false
        System.out.println(s1 == s6.intern());   //true 13.
        System.out.println(s2 == s2.intern());    //
    }

    @Test
    public void cc(){
        Set<String> set = new HashSet<String>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        System.out.println(set);
    }

    public List<String> sortStr(String str){
        char[] chars = str.toCharArray();
        List<String> stringList = new LinkedList<String>();
        if(chars.length == 1){
            stringList.add(str);
            return stringList;
        }
        if (chars.length == 2){
            String str1 = chars[1]+""+chars[0];
            stringList.add(str);
            stringList.add(str1);
            return stringList;
        }
        char firstChar = str.charAt(0);
        String subStr = str.substring(1);
        List<String> strArr = sortStr(subStr);
        for (String string : strArr){
            List<String> stringList1 = group(string,firstChar);
            stringList.addAll(stringList1);
        }
        return stringList;
    }
    public List<String> group(String str, char char1){
        List<String> list = new LinkedList<String>();
        for (int i = 0 ; i <= str.length(); i++){
            String str1 = str.substring(0, i);
            String str2 = str.substring(i);
            list.add(str1+char1+str2);
        }
        return list;
    }

    public  void printArr(int[] arr, int i){
        if (i == arr.length-1){
            System.out.println(arr[i]);
            return;
        }
        System.out.println(arr[i]);
        printArr(arr, i + 1);
    }

    @Test
    public  void test2() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("aaa");
            }
        });
        cyclicBarrier.await();
        cyclicBarrier.await();
    }

    @Test
    public void test3(){
        int cpus = Runtime.getRuntime().availableProcessors();
        System.out.println(cpus);
    }

    @Test
    public void test04(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
