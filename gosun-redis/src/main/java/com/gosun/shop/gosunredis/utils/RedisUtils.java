package com.gosun.shop.gosunredis.utils;

import com.gosun.shop.gosunredis.model.Person;
import org.junit.Test;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description 简单的redis连接池
 * @Author lxg
 * @Date 2019/10/25
 */
@Component
public class RedisUtils {

    //创建redis连接池
    public static JedisPool connection(){

        //创建Jedis配置对象
        JedisPoolConfig jpc = new JedisPoolConfig();
        //最大连接空闲数
        jpc.setMaxIdle(1);
        //最大连接数
        jpc.setMaxTotal(1);

        JedisPool jedisPool = new JedisPool(jpc, "127.0.0.1", 6379);

        return jedisPool;
    }

    //归还连接
    public static void shutdown(Jedis jedis){
        if (jedis != null){
            jedis.close();
            //jedisPool.returnResource(jedis);
        }
    }

    @Test
    public static void main(String[] args) {
        JedisPool connection = RedisUtils.connection();
        Jedis resource = connection.getResource();
        resource.set("name","张三");
        String name = resource.get("name");
        System.out.println(name);


//        Jedis resource1 = connection.getResource();
//        resource1.set("sex","女");
//        System.out.println(resource1.get("sex"));
        RedisUtils.shutdown(resource);
        resource.set("age","23");
        System.out.println(resource.get("age"));
        //connection.close();关闭连接池（连接池不能使用了，想要使用必须重新创建）
        resource.disconnect();
        //RedisUtils.shutdown(resource);
        Jedis resource2 = connection.getResource();
        resource2.set("sex","女");
        System.out.println(resource2.get("sex"));
        //resource.close();
        resource.set("sex","女");
        System.out.println(resource.get("sex"));


    }
}
