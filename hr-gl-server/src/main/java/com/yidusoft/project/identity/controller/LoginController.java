package com.yidusoft.project.identity.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultCode;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.LoginLog;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.LoginLogService;
import com.yidusoft.project.system.service.SecMenuService;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by smy on 2018/1/5.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private SecMenuService secMenuService;

    @Resource
    private LoginLogService loginLogService;
    @Resource
    private JedisPool jedisPool;


    /**
     * 重新登录：跳转到登录页面
     * @return
     */
    @RequestMapping("/loginOut")
    public Result loginAgain( )throws Exception{
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultGenerator.genFailResult("退出登录").setCode(ResultCode.UNAUTHORIZED);
    }
    /**
     * 重新登录：跳转到登录页面
     * @return
     */
    @RequestMapping("/unAuthority")
    public Result  authority( )throws Exception{
        return ResultGenerator.genFailResult("没有权限").setCode(ResultCode.UN_AUTHORITY);
    }
    /**
     * 验证身份并返回token
     * @return
     */
    @RequestMapping("/authorize")
    public Result authorize(HttpServletRequest request,String username,String password){
        Result result = new Result();
        result.setCode(ResultCode.FAIL);
        if (StringUtils.isEmpty(username)){
            return ResultGenerator.genFailResult("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return ResultGenerator.genFailResult("密码不能为空");
        }
        UsernamePasswordToken userToken=new UsernamePasswordToken(username,password);
        LoginLog loginLog = new LoginLog();
        loginLog.setLogId(UUID.randomUUID().toString());
        loginLog.setUserAccount(username);
        loginLog.setLoginType("管理端");
        loginLog.setLoginTime(new Date());
        loginLog.setLoginIp(getIpAddr(request));
        loginLog.setSuccess("是");
        try {
            //TODO 用户登录
            Subject subject = SecurityUtils.getSubject();
            subject.login(userToken);
        } catch (IncorrectCredentialsException ice){
            userToken.clear();
            String msg = "密码不正确！";
            loginLog.setSuccess("否");
            loginLog.setErrorMsg(msg);
            loginLogService.save(loginLog);
            result.setMessage(msg);
            return result;
        } catch (LockedAccountException lae) {
            userToken.clear();
            String msg = "账号已被锁定，请与管理员联系！";
            loginLog.setSuccess("否");
            loginLog.setErrorMsg(msg);
            loginLogService.save(loginLog);
            result.setMessage(msg);
            return result;
        } catch (UnknownAccountException uae) {
            userToken.clear();
            String msg = "账号不存在！";
            loginLog.setSuccess("否");
            loginLog.setErrorMsg(msg);
            loginLogService.save(loginLog);
            result.setMessage(msg);
            return result;
        } catch (AuthenticationException ae) {
            //用户名和密码不匹配时
            userToken.clear();
            String msg = "用户名与密码不匹配";
            loginLog.setSuccess("否");
            loginLog.setErrorMsg(msg);
            loginLogService.save(loginLog);
            result.setMessage(msg);
            return result;
        }

        SecUser secUser =  Security.getUser();

        //将用户拥有的权限返回给客户端
        List  menuList = secMenuService.findMenuForButtonByUser(secUser.getId());
        loginLog.setUserId(secUser.getId());
        loginLog.setUserName(secUser.getUserName());
        loginLog.setAccountType(secUser.getStandby1());

        secUser.setMenuList(menuList);
        result.setData(secUser);
        result.setMessage("登录成功");
        loginLogService.save(loginLog);
        result.setCode(200);
//        response.setHeader("JSESSIONID", request.getSession().getId());
        //        将用户ID作为键存入sessionID保证唯一登录
//        Jedis resource = jedisPool.getResource();
//        resource.set(Security.getUserId(),(String)  SecurityUtils.getSubject().getSession().getId());
//        resource.set((String)  SecurityUtils.getSubject().getSession().getId(),Security.getUserId());
        return result;
    }

    @PostMapping("/getButtonListByUser")
    public Result getButtonListByUser(){
        return ResultGenerator.genSuccessResult(Security.getUser().getMenuList());
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
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
        return ip;
    }
}
