package com.yidusoft.project.system.dao;

import java.util.List;
import java.util.Map;

/**
 * Name:陈文杰
 * Date: 2018/8/29
 */
public interface DaoMapper {
    //自定义sql 返回map
    Map<String, Object> getDivMap(String sql);
    //自定义sql 返回list
    List<Map<String, Object>> getDivList(String sql);
}
