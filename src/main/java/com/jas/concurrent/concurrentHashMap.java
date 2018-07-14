package com.jas.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/12/5.
 */
public class concurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> concurrent = new ConcurrentHashMap<>();
        concurrent.put("a","aaa");
        concurrent.put("b","bbb");
        concurrent.put("c","ccc");
        concurrent.put("d","ddd");
        for(Map.Entry<String, String> entries : concurrent.entrySet()){
            System.out.println(entries.getKey()+"----"+entries.getValue());
        }

    }
}
