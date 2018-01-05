package com.jas.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.xml.bind.SchemaOutputResolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/2.
 */
public class testJedis {
    private static Jedis jedis = null;
    static {
        jedis = new Jedis("192.168.0.14",6379);
    }
    @Test
    public void StringTest(){
        // 设置redis值
        jedis.set("name", "jas");
        jedis.setnx("name","sss");
        jedis.setex("ss",60,"sss");
        jedis.append("name","dsfsd");
        jedis.mset("a","aa","b","bb");
        jedis.incr("num");
        // 获取值
        String a = jedis.get("a");
        List<String> mget = jedis.mget("name", "a", "b","num");
        System.out.println(a);
        System.out.println(mget.toString());
        // 删除key
        jedis.del("name");
    }
    @Test
    public void hashTest(){
        // 设置值
        jedis.hset("user","name","like");
        Map<String,String> user = new HashMap<String, String>();
        user.put("age","20");
        user.put("sex","man");
        user.put("school","beida");
        jedis.hmset("user",user);
        Boolean bool = jedis.hexists("user", "mingzu");
        System.out.println(bool);
        Set<String> field = jedis.hkeys("user");
        System.out.println(field);
        List<String> colValues = jedis.hvals("user");
        System.out.println(colValues);
        Long length = jedis.hlen("user");
        System.out.println(length);
        Long num = jedis.hdel("user", "school");
        System.out.println(num);
        Map<String, String> map = jedis.hgetAll("user");
        System.out.println(map);
        //获取值
        String hget = jedis.hget("user", "name");
        System.out.println(hget);
        List<String> hmget = jedis.hmget("user", "sex", "age");
        System.out.println(hmget);
    }
    @Test
    public void listTest(){
        jedis.rpush("grade","87");
        jedis.lpush("grade","20");
        String grade = jedis.lpop("grade");
        List<String> grade1 = jedis.lrange("grade", 0, -1);
        System.out.println(grade);
        System.out.println(grade1);
        Long grade2 = jedis.llen("grade");
        System.out.println(grade2);
    }
    @Test
    public void setTest(){
        jedis.sadd("xiaowang","z3","l4","w5");
        jedis.sadd("xiaohong","zs","ls","w5");
        // 交集
        Set<String> sinter = jedis.sinter("xiaowang", "xiaohong");
        // 差集
        Set<String> sdiff = jedis.sdiff("xiaowang", "xiaohong");
        // 并集
        Set<String> sunion = jedis.sunion("xiaowang", "xiaohong");
        System.out.println(sinter);
        System.out.println(sdiff);
        System.out.println(sunion);
        // 所有成员
        Set<String> xiaowang = jedis.smembers("xiaowang");
        System.out.println(xiaowang);
        // 成员个数
        Long num = jedis.scard("xiaohong");
        System.out.println(num);
        // 删除元素
        jedis.srem("xiaohong", "w5");
        Set<String> xiaohong = jedis.smembers("xiaohong");
        System.out.println(xiaohong);
    }
    @Test
    public void zsetTest(){
        jedis.zadd("saleNum",101d,"xiaomi");
        Map<String,Double> saleNum = new HashMap<String, Double>();
        saleNum.put("huawei",200d);
        saleNum.put("iphone",300d);
        saleNum.put("leshi",20d);
        saleNum.put("chuizi",5d);
        jedis.zadd("saleNum",saleNum);
        // 数量
        Long saleNum1 = jedis.zcard("saleNum");
        System.out.println(saleNum1);
        // 索引位置（升序）
        Long zrank = jedis.zrank("saleNum", "huawei");
        System.out.println(zrank);
        // 索引位置（降序）
        Long zrevrank = jedis.zrevrank("saleNum", "huawei");
        System.out.println(zrevrank);
        //排序
        Set<String> saleNum2 = jedis.zrangeByScore("saleNum", 20d, 300d);
        Set<String> saleNum3 = jedis.zrevrangeByScore("saleNum", 300d, 20d);
        System.out.println(saleNum2);
        System.out.println(saleNum3);
        // 删除
        jedis.zrem("saleNum","leshi");
    }
}
