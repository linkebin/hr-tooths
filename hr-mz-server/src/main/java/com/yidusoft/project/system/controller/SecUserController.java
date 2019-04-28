package com.yidusoft.project.system.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.PasswordHelper;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/user")
public class SecUserController {
    @Resource
    private SecUserService secUserService;

    @Resource
    private DaoService daoService;
    /***
     * 分页查询所有的用户信息
     * @param param  查询条件
     * @param size 显示的条数
     * @param page 当前页
     * @return
     */
    @GetMapping("/getSecUserForPaging")
    public Result getSecUserForPaging(String param,Integer size,Integer page) {

        SecUser secUser =null;
        if(!StringUtils.isEmpty(param)){
            secUser= JSON.parseObject(param,SecUser.class);
        }else {
            secUser =new SecUser();
        }
        secUser.setOrgId(Security.getUser().getOrgId());
        PageHelper.startPage(page,size);
        List<Map<String,Object>> secUserList=secUserService.getSecUserForPaging(secUser);
        PageInfo pageInfo = new PageInfo(secUserList);

        return ResultGenerator.genTableListResult(pageInfo);
    }

    /***
     * 分页查询某个部门的所有用户信息
     * @param orgId  组织id
     * @param size 显示的条数
     * @param page 当前页
     * @return
     */
    @GetMapping("/getSecUserForOrg")
    public Result getSecUserForOrg(String orgId,Integer size,Integer page) {

        PageHelper.startPage(page,size);
        List<Map<String,Object>> secUserList=secUserService.getSecUserForOrg(orgId);
        PageInfo pageInfo = new PageInfo(secUserList);

        return ResultGenerator.genTableListResult(pageInfo);
    }



    /***
     * 根据角色分页查询所有的用户
     * @param page 当前页
     * @return
     */
    @GetMapping("/getSecUserByRole")
    public Result getSecUserByRole(String roleId,String userName,String account,Integer size,Integer page) {
        if(org.apache.commons.lang.StringUtils.isNotBlank(roleId)){
            Map map = new HashMap();
            map.put("roleId",roleId);
            map.put("userName",userName);
            map.put("account",account);
            PageHelper.startPage(page,size);
            List<Map<String,Object>> secUserList=secUserService.getSecUserByRole(map);
            PageInfo pageInfo = new PageInfo(secUserList);
            return ResultGenerator.genTableListResult(pageInfo);
        }
        return ResultGenerator.genFailResult("角色不能为空");
    }

    @GetMapping("/getSecUserByRoleAll")
    public Result getSecUserByRoleAll(String roleId) {
        if(org.apache.commons.lang.StringUtils.isNotBlank(roleId)){
            Map map = new HashMap();
            map.put("roleId",roleId);
            List<Map<String,Object>> secUserList=secUserService.getSecUserByRole(map);
            return ResultGenerator.genSuccessResult(secUserList);
        }
        return ResultGenerator.genFailResult("角色不能为空");
    }

    /***
     * 角色下其他用户
     * @param page 当前页
     * @return
     */
    @GetMapping("/getSecUserByNotRole")
    public Result getSecUserByNotRole(String roleId,String userName,String account,String dpId,Integer size,Integer page) {
        if(org.apache.commons.lang.StringUtils.isNotBlank(roleId)){
            Map map = new HashMap();
            map.put("dpId",dpId);
            map.put("roleId",roleId);
            map.put("userName",userName);
            map.put("account",account);
            PageHelper.startPage(page,size);
            List<Map<String,Object>> secUserList=secUserService.getSecUserByNotRole(map);
            PageInfo pageInfo = new PageInfo(secUserList);
            return ResultGenerator.genTableListResult(pageInfo);
        }
        return ResultGenerator.genFailResult("角色不能为空");
    }

    /***
     * 查询所有的部门和用户 id，name，parentId 做树形结构
     * @param param  条件
     * @return
     */
    @PostMapping("/getSecUserTreeForOrg")
    public String getSecUserTreeForOrg(String param) {
        SecUser secUser =null;
        if(!StringUtils.isEmpty(param)){
            secUser= JSON.parseObject(param,SecUser.class);
        }else {
            secUser =new SecUser();
        }
        List<Map<String,Object>> treeList=secUserService.getSecUserTreeForOrg(secUser);
        return JSON.toJSONString(treeList) ;
    }

    /**
     *查询用户的详细信息
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/getSecUserBYID")
    public Result getSecUserBYID(String userId) {
        if(StringUtils.isEmpty(userId)){
            userId = Security.getUserId();
        }
        Map<String,Object> map=secUserService.getSecUserBYID(userId);
        return ResultGenerator.genSuccessResult(map);
    }

    /**
     * 添加用户
     * @param
     * @throws Exception
     */
    @PostMapping("/addSecUser")
    public Result addSecUser(String param) {
        SecUser  secUser= JSON.parseObject(param,SecUser.class);
        try {
            PasswordHelper.encryptPassword(secUser);
            secUserService.addSecUser(secUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 查询账号是否存在
     * @param
     * @throws Exception
     */
    @GetMapping("/isExistForAccount")
    public Result isExistForAccount(String account) {
          int num= secUserService.isExistForAccount(account);
          if(num>0){
              return ResultGenerator.genFailResult("账号已存在，请重新输入");
          }
        return ResultGenerator.genSuccessResult();
    }


    /**
     * 修改用户
     * @param
     * @throws Exception
     */
    @PostMapping("/updateSecUser")
    public Result updateSecUser(String param) {
        try {
            List<SecUser> secUserList=   JSON.parseArray(param,SecUser.class);
            if(secUserList.size()>0){
                for (SecUser secUser :secUserList){
                    secUserService.update(secUser);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
    /**
     * 修改用户信息
     * @param
     * @throws Exception
     */
    @PostMapping("/updateSecUserInfo")
    public Result updateSecUserInfo(String param) {
        try {

             SecUser secUser=   JSON.parseObject(param,SecUser.class);
             if (!StringUtils.isEmpty(secUser.getUserPass())){
                 PasswordHelper.encryptPassword(secUser);
             }
             secUserService.update(secUser);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/add")
    public Result add(SecUser secUser) {
        secUserService.save(secUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        secUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SecUser secUser) {
        secUserService.update(secUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updates")
    public Result updates(String flag) {
        SecUser secUser=Security.getUser();
        secUser.setStandby2(flag);
        secUserService.update(secUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/getType")
    public Result getType() {
        SecUser secUser = secUserService.findById(Security.getUserId());
        return ResultGenerator.genSuccessResult(secUser.getStandby2());
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SecUser secUser = secUserService.findById(id);
        return ResultGenerator.genSuccessResult(secUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, String json, HttpServletRequest request) {
        PageHelper.startPage(page, size);
        String sessionid = request.getHeader("SESSIONID");
        Cookie[] cookies = request.getCookies();
        List<SecUser> list = secUserService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/getAllDoc")
    public  Result getAllDoc(){
        Condition condition = new Condition(SecUser.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orgId", Security.getUser().getOrgId());
        criteria.andEqualTo("standby1","医生");
        criteria.andEqualTo("deleted","0");
        criteria.andEqualTo("status","启用");
        List<SecUser> list = secUserService.findByCondition(condition);
        return  ResultGenerator.genSuccessResult(list).setTotal(list.size());
    }

    @PostMapping("/getDoctor")
    public  Result getDoctor(){
        SecUser secUser=Security.getUser();
        List<Map<String,Object>> list = daoService.getDivList("select id_ id,user_name name from sec_user where org_id ='" +secUser.getOrgId()+ "' and standby1='医生' and status = '启用' and deleted = 0");
        return  ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getUserName")
    public String getUserName(String userId){

        SecUser secUser = daoService.getObject("select * from sec_user where ID_='"+userId+"' ",SecUser.class);
        return  secUser.getUserName();
    }
}
