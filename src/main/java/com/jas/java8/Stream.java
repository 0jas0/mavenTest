package com.jas.java8;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Stream {
    public static void main(String[] args) {
        // forEach 迭代流中的每个数据
        List<String> foreachList = Arrays.asList("a", "b", "c", "d", "e", "f");
        foreachList.stream().forEach(Stream::print);
        System.out.println();
        foreachList.forEach(Stream::print);

        // map 用于映射每个元素的对应的结果
        List<Integer> mapList = Arrays.asList(1, 2, 3, 4);
        List<String> collect = mapList.stream().map(Stream::delMap).distinct().collect(Collectors.toList());
        System.out.println(collect);

        // filter 用于条件筛选过滤元素
        List<String> filterList = Arrays.asList("a", "b", "c", "d", "");
        List<String> filterLists = filterList.stream().filter(str -> !StringUtils.isEmpty(str)).collect(Collectors.toList());
        System.out.println(filterLists);

        // limit 获取指定数量的流
        // sorted 进行流的排序
        List<Integer> list = Arrays.asList(12, 11, 10, 9, 13, 15, 8, 21, 22);
        List<Integer> limitList = list.stream().sorted().limit(5).collect(Collectors.toList());
        System.out.println(limitList);

        // parallelStream 流的并行处理
        List<String> parallStrList = Arrays.asList("aa", "bb", "cc", "dd", "ee");
        List<String> removeList = parallStrList.parallelStream().filter(str -> {
            if (str.equals("aa")) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        System.out.println(removeList);

        // Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素。
        List<String> collectorsList = Arrays.asList("aa", "ab", "ac", "ad", "ae", "af");
        String collect1 = collectorsList.stream().filter(str -> !StringUtils.isEmpty(str)).collect(Collectors.joining(","));
        System.out.println(collect1);
        // Collectors 的 groupingBy
        Person person1 = new Person("安徽","张三",23);
        Person person2 = new Person("安徽","李四",25);
        Person person3 = new Person("浙江","王武",23);
        Person person4 = new Person("浙江","李蛋",24);
        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        Map<String, List<Person>> listMap = personList.stream().collect(Collectors.groupingBy(person -> person.getAreaId()));

        // summaryStatistics 主要用于int、double、long等基本类型,统计结果的收集器也非常有用
        List<Double> numbers = Arrays.asList(1.2, 11.0, 12.1, 1.3, 4.6);
        DoubleSummaryStatistics intSummaryStatistics = numbers.stream().mapToDouble(x -> x).summaryStatistics();
        System.out.println("最大值：" + intSummaryStatistics.getMax());
        System.out.println("最小值：" + intSummaryStatistics.getMin());
        System.out.println("平均值：" + intSummaryStatistics.getAverage());
        System.out.println("求和：" + intSummaryStatistics.getSum());

    }
    private static void print(String str){
        System.out.print(str + " ");
    }

    private static String delMap(Integer a){
        return "steam:" + a +" ";
    }

    static class Person{
        private String areaId;
        private String name;
        private int age;

        public Person(String areaId, String name, int age) {
            this.areaId = areaId;
            this.name = name;
            this.age = age;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
