package com.yidusoft.service;


import java.util.List;
import java.util.Map;

/**
 * Name:陈文杰
 * Date: 2018/8/29
 */
public interface DaoService {
    //自定义sql 返回map
    Map<String, Object> getDivMap(String sql);
    //自定义sql 返回list
    List<Map<String, Object>> getDivList(String sql);
    //自定义sql 返回对象
    <T> T getObject(String sql, Class<T> clazz);
    //map转对象
    <T> T getObject(Map map, Class<T> clazz);
//    //返回List
    <T> List<T> getbyList(String sql, Class<T> clazz);
}
