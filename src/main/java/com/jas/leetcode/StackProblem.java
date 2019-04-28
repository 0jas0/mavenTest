package com.jas.leetcode;

import java.util.Stack;

/**
 * leetCode上有关栈的问题
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class StackProblem {
    /**
     *
     *
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     有效字符串需满足：
     左括号必须用相同类型的右括号闭合。
     左括号必须以正确的顺序闭合。
     注意空字符串可被认为是有效字符串。
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null || "".equals(s)){
            return true;
        }
        Stack<String> stack = new Stack<>();
        for (Character c : s.toCharArray()){
            if (Character.compare(c,'}') == 0){
                if (stack.isEmpty() || !"{".equals(stack.pop())){
                    return false;
                }
            }else if (Character.compare(c,')') == 0){
                if (stack.isEmpty() || !"(".equals(stack.pop())){
                    return false;
                }
            }else if (Character.compare(c,']') == 0){
                if (stack.isEmpty() || !"[".equals(stack.pop())){
                    return false;
                }
            }else {
                stack.push(c+"");
            }
        }
        return stack.isEmpty();
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        int val = 0;
        ListNode listNode = new ListNode(val);
        ListNode tmp = listNode;
        for (;l1 != null && l2 != null;){
            if (l1.val > l2.val){
                tmp.next = new ListNode(l2.val);
                tmp = tmp.next;
                l2 = l2.next;
            }else {
                tmp.next = new ListNode(l1.val);
                tmp = tmp.next;
                l1 = l1.next;
            }
        }
        if (l1 == null){
            tmp.next = l2;
        }else if (l2 == null){
            tmp.next = l1;
        }
        return listNode;
    }


    public static void main(String[] args) {
        ListNode l1 = null;

        ListNode l2 = new ListNode(0);

        ListNode listNode = mergeTwoLists(l1, l2);
        System.out.println(listNode);

    }
}
