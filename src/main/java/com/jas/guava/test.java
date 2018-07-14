package com.jas.guava;

import com.google.common.base.*;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        // 判断一个字符串是空还是null
        boolean empty = Strings.isNullOrEmpty("");
        System.out.println(empty);

        System.out.println("---------------------------");
        // 放回重复的字符串
        String aa = Strings.commonPrefix("com.aa.bb", "com.aa.cc");
        System.out.println(aa);

        System.out.println("---------------------------");
        // Strings.padStar（str,min,padChar）在str前添加minLength个padChar字符
        String padStartResult = Strings.padStart("1", 3, 'a');
        // Strings.padEnd(str,min,padChar)  在str后添加minLength个padChar字符
        String padEndResult = Strings.padEnd("123", 6, '0');
        System.out.println("padStartResult is " + padStartResult + "--" + padEndResult);

        System.out.println("---------------------------");
        //Splitter的onPattern方法传入的是一个正则表达式 omitEmptyStrings()表示忽略空字符串，split方法会执行拆分操作。
        Iterable<String> strings = Splitter.onPattern(",").omitEmptyStrings().split("aa,bb,cc,,dd");
        for (String str : strings){
            System.out.println(str);
        }

        System.out.println("---------------------------");
        //首先是使用onPattern做第一次的拆分，然后再通过withKeyValueSeperator('')方法做第二次的拆分
        Map<String, String> split = Splitter.onPattern(";").withKeyValueSeparator("=").split("a=b;c=d");
        for (Map.Entry<String,String> entry : split.entrySet()){
            System.out.println("key:"+entry.getKey()+"--value:"+entry.getValue());
        }

        System.out.println("---------------------------");
        //使用Joiner.on(" ").join(xx)来合并字符串
        String join = Joiner.on(",").join(new String[]{"hello", "world"});
        System.out.println(join);

        System.out.println("---------------------------");
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        // Splitter方法可以对字符串做二次的拆分，对应的Joiner也可以逆向操作，将Map<String,String>做合并
        // 使用withKeyValueSeparator方法可以对map做合并。
        String s = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(s);

        System.out.println("---------------------------");
        Object a = null;
        Object b = new Object();
        //判断两个对象是否相当，不用判断是否为null
        boolean isEqual = Objects.equal(b, a);
        System.out.println(isEqual);

        System.out.println("---------------------------");
        test  t = new test();
        //判断一个对象是否为null
        Optional<test> optional = Optional.of(t);
        test test = optional.get();
        boolean equal = Objects.equal(t, test);
        System.out.println(equal);

    }
}
