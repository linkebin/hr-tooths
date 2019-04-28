package com.yidusoft.project.system.operation;

import com.yidusoft.project.identity.utils.RedisHandle;

/**
 * Created by zcb on 2018/4/19.
 */
public class RedisHandler extends Handler {

    public  RedisHandler(){

    }
    @Override
    protected String getName(String tableName) {
        RedisHandle redisHandle=RedisHandle.getRedisHandle();
        String name=redisHandle.getValue(tableName);
        return name;
    }
}   
