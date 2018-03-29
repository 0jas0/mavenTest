package com.jas.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jas.bean.person;
import com.sun.javafx.collections.MappingChange;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

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
        String s = JSONArray.toJSONString(lists);
        System.out.printf(s);
        List<person> people = JSONArray.parseArray(s, person.class);
        System.out.print(people);

    }
    @Test
    public void bb(){
    }
}
