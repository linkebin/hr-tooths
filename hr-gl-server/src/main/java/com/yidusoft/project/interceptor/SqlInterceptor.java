package com.yidusoft.project.interceptor;

import com.yidusoft.utils.ReflectHelper;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.generator.config.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by zcb on 2018/4/20.
 */

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class,Integer.class})})
@Component
public class SqlInterceptor implements Interceptor {

    //mapper.xml中需要拦截的ID(正则匹配)
    private String filterSqlId = "";

    public Object intercept(Invocation invocation) throws Throwable {
        if(invocation.getTarget() instanceof RoutingStatementHandler){
            RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectHelper.getFieldValue(statementHandler, "delegate");
            BoundSql boundSql = delegate.getBoundSql();
            String sql=boundSql.getSql();
            sql=sql.replace("[#ddd#]","") ;
            //利用反射设置当前BoundSql对应的sql属性为我们建立好的Sql语句
            ReflectHelper.setFieldValue(boundSql, "sql", sql);

           // Configuration configuration=(Configuration)delegate;
        }
            return invocation.proceed();
    }


    public Object plugin(Object arg0) {
        if (arg0 instanceof StatementHandler) {
            return Plugin.wrap(arg0, this);
        } else {
            return arg0;
        }
    }


    public void setProperties(Properties properties) {

    }

    public String getFilterSqlId() {
        return filterSqlId;
    }

    public void setFilterSqlId(String filterSqlId) {
        this.filterSqlId = filterSqlId;
    }
}
