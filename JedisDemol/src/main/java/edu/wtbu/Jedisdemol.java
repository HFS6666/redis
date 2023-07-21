package edu.wtbu;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class Jedisdemol {
    public static void main(String[] args) {
        Jedis Jedis=new Jedis("127.0.0.1",6379);
        String value=Jedis.ping();
       Set<String> keys=Jedis.keys("*");
       for(String key:keys){
           System.out.println(key);
       }
    }
}
