package com.zz.sso;

import redis.clients.jedis.Jedis;

public class jdistest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        jedis.set("name","123");
        System.out.println(jedis.get("name"));
        jedis.close();
    }
}
