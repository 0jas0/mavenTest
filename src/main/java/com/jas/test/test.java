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
}
