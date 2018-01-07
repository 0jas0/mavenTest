package com.jas.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/3.
 */
public class redisCluster {
    private Set<HostAndPort> sets = new HashSet<HostAndPort>();
    private JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    @Before
    public void init(){
        sets.add(new HostAndPort("123.206.53.60",7001));
        sets.add(new HostAndPort("123.206.53.60",7002));
        sets.add(new HostAndPort("123.206.53.60",7003));
        sets.add(new HostAndPort("123.206.53.60",7004));
        sets.add(new HostAndPort("123.206.53.60",7005));
        sets.add(new HostAndPort("123.206.53.60",7006));
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(0);
        jedisPoolConfig.setTestOnBorrow(true);
        System.out.println("---------初始化完成--------");
    }
    @Test
    public void test(){
        JedisCluster jedisCluster = new JedisCluster(sets,6000,100,jedisPoolConfig);
        jedisCluster.set("name","jas");
        String name = jedisCluster.get("name");
        System.out.println(name);
        
    }
}
