package com.yidusoft.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smy on 2018/1/18.
 */
public class HttpRequestUtils {

    //获取请求中全部的参数名与参数值键值对
    public static Map<String,Object> getParams(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }
}
