package com.jas.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.*;

import java.util.Collection;
import java.util.Set;

public class collection {
    public static void main(String[] args) {
        // 不可变集合 不可对集合中的元素进行add和remove
        Set<String> immutablesSet1 = ImmutableSet.of("a", "b", "c", "d");
        Set<String> immutablesSet2 = ImmutableSet.copyOf(new String[]{"aa", "bb", "cc", "dd"});
        System.out.println(immutablesSet1 + "-----" + immutablesSet2);

        //MultiSet 可以向Multiset中添加重复的元素，Multiset会对添加的元素做一个计数。
        Multiset<String> multiset = HashMultiset.create();
        String str = "this is a story there is a good girl in the story";
        Iterable<String> split = Splitter.onPattern(" ").omitEmptyStrings().trimResults().split(str);
        for (String s : split) {
            multiset.add(s);
        }
        for (String s : multiset) {
            System.out.println(s + "，数量为：" + multiset.count(s));
        }

        // 双向Map
        // BiMap 能通过 键=>值 也能 值=>键
        BiMap<String, String> map = HashBiMap.create();
        map.put("周一", "星期一");
        map.put("周二", "星期二");
        map.put("周三", "星期三");
        map.put("周四", "星期四");
        map.put("周五", "星期五");
        System.out.println("周一：" + map.get("周一"));
        System.out.println("星期一：" + map.inverse().get("星期一"));

        //Multimaps:一键多值的Map
        // ArrayListMultimap HashMultimap LinkedListMultimap LinkedHashMultimap TreeMultimap ImmutableListMultimap ImmutableSetMultimap
        Multimap<String,String> multimap = ArrayListMultimap.create();
        multimap.put("a","aa");
        multimap.put("a","bb");
        multimap.put("a","cc");
        multimap.put("a","dd");
        multimap.put("b","ee");
        multimap.put("b","ff");
        multimap.put("b","gg");
        Collection<String> collecitona = multimap.get("a");
        Collection<String> collectionb = multimap.get("b");
        System.out.println(collecitona);
        System.out.println(collectionb);
        //遍历集合
        for (String strVal : multimap.values()){
            System.out.println(strVal);
        }
        // 删除某个元素
        multimap.remove("a","aa");
        System.out.println(multimap.get("a"));
        //删除某个key下的所有值
        multimap.removeAll("a");
        System.out.println(multimap.get("a"));

        //使用Table可以实现二维矩阵的数据结构
        Table<Integer,Integer,String> table = HashBasedTable.create();
        for (int i = 0; i < 4; i++){
            for (int j = 0; j< 3; j++){
                table.put(i,j,i+""+j);
            }
        }

    }
}
