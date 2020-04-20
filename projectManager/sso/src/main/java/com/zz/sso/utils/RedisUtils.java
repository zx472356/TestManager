package com.zz.sso.utils;

import redis.clients.jedis.Jedis;
public class RedisUtils {
    private static String redisIp = "localhost";
    private static int port = 6379;
    private static Jedis jedis;

    public static Jedis getJedis(){
        if(jedis==null){
            jedis=new Jedis(redisIp,port);
        }
        return jedis;
    }
    public void close(){
        jedis.close();
    }
}
