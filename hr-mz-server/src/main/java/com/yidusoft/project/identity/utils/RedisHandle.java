package com.yidusoft.project.identity.utils;

import com.yidusoft.configurer.RedisConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import redis.clients.jedis.Jedis;

/**
 * Created by zcb on 2018/3/29.
 * @author zhangchengbo
 *
 */

public class RedisHandle {
    //@Autowired
    RedisConfigurer redisConfigurer=new RedisConfigurer();
    private  static  Jedis jedis=null;
    private  static  RedisHandle redisHandle=null;
    private RedisHandle(){}
    public static RedisHandle getRedisHandle (){
        synchronized(RedisHandle.class){
            if(redisHandle==null){
                redisHandle=new RedisHandle();
            }
            if(jedis==null){
                jedis= new RedisHandle().getJedis();
            }
        }


        return  redisHandle;
    }
    private  Jedis  getJedis(){
       return redisConfigurer.redisPoolFactory().getResource();
    }

    /**
     * 添加(字符串)
     * @param key
     * @param value
     */
     public  void   save(String key,String value){
         jedis.set(key,value);
     }
    /***
     *通过key返回值(字符串)
     * @param key
     * @return
     */
      public    String getValue(String key){
         return jedis.get(key);
     }

    public static void main(String[] args) {
        RedisHandle redisHandle=RedisHandle.getRedisHandle();
        redisHandle.save("ce","测试");
       System.out.println(redisHandle.getValue("cess"));
    }

}
