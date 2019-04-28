package com.yidusoft.configurer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultCode;
import com.yidusoft.core.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //升级最新版本需加=============================================================
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        fastConverter.setSupportedMediaTypes(supportedMediaTypes);

        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        //WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
        //WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
        //DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
        //WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        //WriteMapNullValue：是否输出值为null的字段,默认为false
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String realPath = System.getProperty("user.dir");
        String path = realPath.replace("\\","/");
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" +path+"/upload/")
                .addResourceLocations("file:///" +path+"/upload/icon/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("80MB");
        config.setMaxRequestSize("100MB");
        return config.createMultipartConfig();
    }

    //统一异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                ModelAndView mv = new ModelAndView();
                Result result = new Result();
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    if (null != handlerMethod.getBean().getClass().getAnnotation(RestController.class)
                            || null != handlerMethod.getMethodAnnotation(ResponseBody.class)) {//JSON视图
                        if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                            logger.info(e.getMessage());
                        } else if (e instanceof ServletException) {
                            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                        } else {
                            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                            String message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                    request.getRequestURI(),
                                    handlerMethod.getBean().getClass().getName(),
                                    handlerMethod.getMethod().getName(),
                                    e.getMessage());
                            logger.error(message, e);
                        }

                    } else {//普通视图
                        if (response.getStatus() == 404) {
                            result.setCode(ResultCode.NOT_FOUND).setMessage(e.getMessage());
                        }else if (response.getStatus() == 403) {
                            result.setCode(ResultCode.UN_AUTHORITY).setMessage(e.getMessage());
                        }  else {
                            logger.error(e.getMessage(), e);
                            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
                        }
                    }
                } else {
                    logger.error(e.getMessage(), e);
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
                }
                responseResult(response, result);
                return  new ModelAndView();
            }
        });
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //registry.addMapping("/**");
    }

//    //添加拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor(new HandlerInterceptorAdapter() {
//                @Override
//                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                    //针对跨域进行响应头的设置，在这里设置效果不佳
                    /*String origin = request.getHeader("Origin");
                    response.addHeader("Access-Control-Allow-Origin", origin);
                    response.setHeader("Access-Control-Allow-Credentials", "true");//解决js请求跨域session问题
                    //预检时判断哪些头部是允许(跨域)的
                    response.setHeader("Access-Control-Allow-Headers", "SESSIONID,Origin, X-Requested-With, Content-Type, Accept");
                    //哪些头部可以响应给外部(外部可以获取到哪些响应头)
                    response.setHeader("Access-Control-Expose-Headers", "SESSIONID,Origin, X-Requested-With, Content-Type, Accept");
                    response.setHeader("Access-Control-Max-Age", "3600");
                    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE,PUT");*/
//                    return true;
//                }
//            });
//    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
    private boolean validateSign(HttpServletRequest request) {
        String requestSign = request.getParameter("sign");//获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        keys.remove("sign");//排除sign参数
        Collections.sort(keys);//排序

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//去除最后一个'&'

        String secret = "Potato";//密钥，自己修改
        String sign = DigestUtils.md5Hex(linkString + secret);//混合密钥md5

        return StringUtils.equals(sign, requestSign);//比较
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
}
