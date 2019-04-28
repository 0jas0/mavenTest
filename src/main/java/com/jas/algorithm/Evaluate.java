package com.jas.algorithm;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Evaluate {
    /**
     * 可以计算多级括号的运算
     * @param s
     * @return
     */
    public static int cal(String s){
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++){
            stack.push(chars[i]);
            if (Character.compare(chars[i], ')') == 0){
                StringBuffer stringBuffer = new StringBuffer();
                while (Character.compare(stack.peek(),'(') != 0){
                    stringBuffer.insert(0,stack.pop());
                }
                stringBuffer.insert(0,stack.pop());
                int minRes = calMin(stringBuffer.toString());
                String minResStr = String.valueOf(minRes);
                for (int j = 0; j < minResStr.length(); j++){
                    stack.push(minResStr.charAt(j));
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.isEmpty()){
            stringBuffer.insert(0,stack.pop());
        }
        return Integer.valueOf(stringBuffer.toString());
    }

    /**
     * 只能计算一层括号的值
     * 例如：（2+3）= 5
     * @param min
     * @return
     */
    public static int calMin(String min){
        min = min.substring(1, min.length() -1);
        String[] vals = min.split("[^\\d]");
        Pattern pattern = Pattern.compile("(\\+|-|\\*|/)");
        Matcher matcher = pattern.matcher(min);
        String opt = "";
        if (matcher.find()){
            opt = matcher.group();
        }
        switch (opt){
            case "+":
                return Integer.valueOf(vals[0]) + Integer.valueOf(vals[1]);
            case "-":
                return Integer.valueOf(vals[0]) - Integer.valueOf(vals[1]);
            case "*":
                return Integer.valueOf(vals[0]) * Integer.valueOf(vals[1]);
            case "/":
                return Integer.valueOf(vals[0]) / Integer.valueOf(vals[1]);
        }
        return 0;
    }

    public static void main(String[] args) {
        int i = cal("(1+((2+3)*(4*5)))");
        System.out.println(i);
    }
}
