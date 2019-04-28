package com.yidusoft.configurer;


import com.alibaba.fastjson.JSON;
import com.yidusoft.project.identity.utils.JavaWebToken;
import com.yidusoft.utils.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;


//@Aspect
//@Component
//@Order(8)
public class IdentityAccessAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //签名时效性过期时间（分钟）
    private long TIMESTAMP_OUT = 10000;


    @Around("webLog()")
    public Object changeDataSource(ProceedingJoinPoint pjp) throws Throwable {
        //SecUser user = Security.getUser();
        Object result=null;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        String uri = req.getRequestURI();
        if (!uri.contains("/login/authorize")) {
            String token = null;
            String token1 = req.getParameter("oauth_token");

            String token2 = req.getHeader("oauth_token");
            ServletOutputStream out = response.getOutputStream();
            if (token1 != null) {
                token = token1;
            }
            if (token2 != null) {
                token = token2;
            }
            if (token == null || token.equals("")) {
                out.write(JSON.toJSONString(SystemLevelErrorCode.getErrorCode(SystemLevelErrorCode.TOKEN_LACK
                        ,SystemLevelErrorCode.TOKEN_MSG_LACK)).getBytes("utf-8"));
                out.flush();
                out.close();
                return result;
            }
            Map<String,Object> tokenInfo = JavaWebToken.parserJavaWebToken(token);
            if (tokenInfo == null) {
                out.write(JSON.toJSONString(SystemLevelErrorCode.getErrorCode(SystemLevelErrorCode.TOKEN_ERROR,SystemLevelErrorCode.TOKEN_MSG_ERROR)).getBytes("utf-8"));
                out.flush();
                out.close();
                return result;
            }
            String timestamp = req.getParameter("timestamp");


            if (timestamp == null || timestamp.equals("") || DateUtils.timeStamp2Date(timestamp,null) == null) {
                out.write(JSON.toJSONString(SystemLevelErrorCode.getErrorCode(SystemLevelErrorCode.TIMESTAMP_LACK,SystemLevelErrorCode.TIMESTAMP_LACK_MSG)).getBytes("utf-8"));
                out.flush();
                out.close();
                return result;
            }

            long thisTime = new Date().getTime()/1000;
            long min = (thisTime - Long.parseLong(timestamp))/60;
            if (min > TIMESTAMP_OUT) {
                out.write(JSON.toJSONString(SystemLevelErrorCode.getErrorCode(SystemLevelErrorCode.TIMESTAMP_LACK,SystemLevelErrorCode.TIMESTAMP_LACK_MSG)).getBytes("utf-8"));
                out.flush();
                out.close();
                return result;
            }
            result= pjp.proceed();
            return result;
        }else {
            //登录操作
            result= pjp.proceed();
            return result;
        }
    }

    @Pointcut("execution(* com.yidusoft.project.*.controller..*.*(..))")
    public void webLog(){}
}
