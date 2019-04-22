package com.jas.test;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int mod = (l1.val + l2.val) % 10;
        int res = (l1.val + l2.val) / 10;
        ListNode header = new ListNode(mod);
        l1 = l1.next;
        l2 = l2.next;
        ListNode temp = header;
        for (; l1 != null || l2 != null; ) {
            int v1 = 0;
            int v2 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            mod = (v1 + v2 + res) % 10;
            res = (v1 + v2 + res) / 10;
            ListNode newNode = new ListNode(mod);
            temp.next = newNode;
            temp = newNode;
        }
        if (res > 0) {
            temp.next = new ListNode(res);
        }
        return header;
    }

    public int lengthOfLongestSubstring(String s){
        if (s == null || s.equals("")){
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxLength = 0;
        LinkedList<Character> characterList = new LinkedList<>();
        for (int i = 0; i < chars.length; i++){
            if (characterList.contains(chars[i])){
                maxLength = Math.max(maxLength, characterList.size());
                Character character = characterList.removeFirst();
                while (character != null){
                    if (Character.compare(character, chars[i]) == 0){
                        break;
                    }
                    character = characterList.removeFirst();
                }
            }
            characterList.addLast(chars[i]);
        }
        maxLength = Math.max(maxLength, characterList.size());
        return maxLength;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int k = 0;
        for (int i = 0, j = 0; i < nums1.length || j < nums2.length;){
            if (i == nums1.length){
                nums[k++] = nums2[j++];
                continue;
            }
            if (j == nums2.length){
                nums[k++] = nums1[i++];
                continue;
            }
            if (nums1[i] < nums2[j]){
                nums[k++] = nums1[i++];
            }else if (nums1[i] > nums2[j]){
                nums[k++] = nums2[j++];
            }else {
                nums[k++] = nums1[i++];
                nums[k++] = nums2[j++];
            }
        }
        return (nums[nums.length/2]+nums[(nums.length-1)/2])/2.0;
    }

    public static int cut1(int[] p, int n){
        if (n <= 0){
            return 0;
        }
        int maxValue = -1;
        for (int i = 1; i <= p.length; i++){
            maxValue = Math.max(maxValue, p[i-1] + cut1(p, n-i));
        }
        return maxValue;
    }

    public static int cut2(int[] p, int n){
        int[] r = new int[p.length + 1];
        for (int i = 0; i <= p.length; i++){
            r[i] = -1;
        }
        return cutProcess2(p, n, r);
    }

    private static int cutProcess2(int[] p, int i, int[] r) {
        if (r[i] != -1){
            return r[i];
        }
        int maxValue = -1;
        if (i == 0){
            maxValue = 0;
        }
        for (int j = 1; j <= i; j++){
            maxValue = Math.max(maxValue, p[j - 1] + cutProcess2(p, i - j, r));
        }
        r[i] = maxValue;
        return maxValue;
    }

    public static int cut3(int[] p, int n){
        int[] r = new int[p.length + 1];
        for (int i = 1; i <= n; i++ ){
            int maxValue = -1;
            for (int j = 1; j <= i; j++){
                maxValue = Math.max(maxValue, p[j-1] + r[i-j]);
            }
            r[i] = maxValue;
        }
        return r[n];
    }

    /**
     * 最长回文子字符串
     * 线性规划
     * @param s
     * @return
     */
    public static String longestPalindrome(String s){
        int[][] db = new int[s.length()][s.length()];
        char[] chars = s.toCharArray();
        String maxStr = "";
        for (int i = 0; i < chars.length; i++){
            db[i][i] = 1;
            if(i != chars.length -1 && Character.compare(chars[i], chars[i+1]) == 0){
                db[i][i+1] = 1;
                maxStr = s.substring(i, i+2);
            }
        }
        for (int L = 3; L <= chars.length; L++){
            for (int i = 0; i + L -1 < chars.length; i++){
                int j = L + i -1;
                if (Character.compare(chars[j], chars[i]) == 0 && db[i+1][j-1] == 1){
                    db[i][j] = 1;
                    maxStr = s.substring(i, j + 1);
                }
            }
        }
        return maxStr;
    }
    public static boolean s(String s, String p) {
        if (p.isEmpty()){
            return s.isEmpty();
        }
        boolean firstMatch = (!s.isEmpty() &&
                (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'));
        if (p.length() >= 2 && p.charAt(1) == '*'){
            return (s(s, p.substring(2)) ||
                    (firstMatch && s(s.substring(1), p)));
        }else {
            return firstMatch && s(s.substring(1), p.substring(1));
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        boolean aa = s("aa", "a*");
        System.out.println(aa);
    }

}
