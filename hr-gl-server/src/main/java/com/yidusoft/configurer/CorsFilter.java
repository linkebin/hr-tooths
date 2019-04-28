package com.yidusoft.configurer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cors（跨域）过滤器
 * Created by Zcansheng on 2018/3/13.
 */
@Component
@WebFilter(urlPatterns = "*//**",filterName = "corsFilter")
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        String origin = request.getHeader("Origin");
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");//解决js请求跨域session问题
        //预检时判断哪些头部是允许(跨域)的
        response.setHeader("Access-Control-Allow-Headers", "loginAgain,SESSIONID,JSESSIONID,Origin, X-Requested-With, Content-Type, Accept");
        //哪些头部可以响应给外部(外部可以获取到哪些响应头)
        response.setHeader("Access-Control-Expose-Headers", "loginAgain,SESSIONID,JSESSIONID,Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE,PUT");
        filterChain.doFilter(request,response);
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        String cookie = httpRequest.getHeader("Cookie");
//        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//        if (url.startsWith("/") && url.length() > 1) {
//            url = url.substring(1);
//        }
//        //放行登录请求
//        if(StringUtils.equals(url,"login/authorize")){
//            filterChain.doFilter(httpRequest, response);
//            return;
//        }
//        if (checkAuth(cookie)) {
//            //已经验证过，直接访问
//            filterChain.doFilter(httpRequest, response);
//            return;
//        } else {
//            //会话不存在，返回sessionId进行登录
//            httpResponse.setCharacterEncoding("utf-8");
//            httpResponse.setContentType("text/html;charset=utf-8");
//            PrintWriter writer = httpResponse.getWriter();
//            Result result = ResultGenerator.genFailResult("您已在别的地方登录，如非本人操作，请及时修改密码或联系管理员处理！").setCode(401);
//            writer.write(result.toString());
//            writer.flush();
//        }
    }

    @Override
    public void destroy() {

    }
    @Autowired
    private JedisPool jedisPool;

    private boolean checkAuth(String cookie) {
        Jedis resource = jedisPool.getResource();
        try {
            if ("".equals(cookie) || cookie == null) {
                return false;
            } else {
                String[] vals = cookie.split(";");
                for (int i = 0; i < vals.length; i++) {
                    if (vals[i].toString().indexOf("JSESSIONID") != -1) {
                        //在这里做唯一登录校验
                        vals = vals[i].split("=");
                        String nowSessionId = vals[1].replace("\"", "");
                        String userId = resource.get(nowSessionId);
                        String onlySessionId = resource.get(userId);
                        if(StringUtils.equals(onlySessionId,nowSessionId)){
                            return true;
                        }else {
                            return false;
                        }
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resource.close();
        }
        return false;
    }
}
