package com.yidusoft.project.system.operation;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.yidusoft.project.identity.utils.RedisHandle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zcb on 2018/4/20.
 */
public class OperationUtil {

    /***
     * 获取对应的数据名称
     * @param tableName
     * @param propertyName
     * @return
     */
    public  static String  getChineseName(String tableName,String propertyName){

        //将数据转换成map
        Map map=getNameMap(tableName);
        if( map.get(propertyName)!=null){
          return map.get(propertyName).toString();
        }else {
            return "";
        }
    }

    /***
     * 获取对应的数据名称   多条
     * @param tableName
     * @param propertyName
     * @return
     */
    public  static List<String> getChineseNameList(String tableName, String...  propertyName){

        List<String> list=new ArrayList<>();
        Map map=getNameMap(tableName);
        String [] propertyNameArray=propertyName;
        for(String propertyNames: propertyNameArray){
            if( map.get(propertyNames)!=null){
                list.add(map.get(propertyNames).toString());
            }
        }
       return list ;
    }

    /***
     * 判断缓存，做数据缓存
     * @param tableName
     * @return
     */
    private static  Map getNameMap(String tableName){
        Handler redisHandler = new  RedisHandler();
        String nameMap="";
        //判断缓存里面是否存在数据
        if(StringUtils.isEmpty(redisHandler.getName(tableName))){
            //没有就查询数据库
            Handler dataBaseHandler = new  DataBaseHandler();
            nameMap=dataBaseHandler.getName(tableName);
            RedisHandle redisHandle=RedisHandle.getRedisHandle();
            //将数据得到的数据放入缓存
            redisHandle.save(tableName,nameMap);
        }else {
            nameMap=redisHandler.getName(tableName);
        }
        //将数据转换成map
        Map map=(Map) JSONObject.parse(nameMap);
        return   map;
    }



}
