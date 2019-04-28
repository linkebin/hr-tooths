package com.yidusoft.project.system.operation;

import com.alibaba.druid.util.StringUtils;

/**
 * Created by zcb on 2018/4/19.
 */
public class DataBaseHandler extends  Handler {

    @Override
    protected String getName(String tableName) {
       String name= DatabaseUtil.getColumnNamesComments(tableName);
        return  name;
    }
}
