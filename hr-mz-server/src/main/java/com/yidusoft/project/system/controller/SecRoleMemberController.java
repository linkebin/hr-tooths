package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecRoleMember;
import com.yidusoft.project.system.service.SecRoleMemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/role/member")
public class SecRoleMemberController {
    @Resource
    private SecRoleMemberService secRoleMemberService;


    /**
     * 删除用户的多个角色
     * @param userId
     * @param roleIds
     * @return
     */
    @PostMapping("/delRoleForUser")
    public Result delRoleForUser(String userId,String roleIds) {
        try {
            secRoleMemberService.delRoleForUser(userId,roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult( e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }


    /**
     * 用户添加多个角色
     * @param userId
     * @param roleIds
     * @return
     */
    @PostMapping("/addRoleForUser")
    public Result addRoleForUser(String userId,String roleIds) {
        try {
            secRoleMemberService.addRoleForUser(userId,roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult( e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/add")
    public Result add(String idStr,@RequestParam String roleId) {

        String[] ids = null;
        if(StringUtils.isNotBlank(idStr)&&StringUtils.isNotBlank(roleId)){
            ids = idStr.split(",");
        }else {
            return ResultGenerator.genFailResult("参数不能为空");
        }
        if(ids!=null&&ids.length>0){
            for (int i = 0; i <ids.length ; i++) {
                if(StringUtils.isNotBlank(ids[i])){
                    SecRoleMember secRoleMember = new SecRoleMember();
                    secRoleMember.setRoleId(roleId);
                    secRoleMember.setId(UUID.randomUUID().toString());
                    secRoleMember.setUserId(ids[i]);
                    secRoleMemberService.save(secRoleMember);
                }
            }
        }
        return  ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String idStr,@RequestParam String roleId) {
        Map map = new HashMap<>();
        if(StringUtils.isNotBlank(idStr)&&StringUtils.isNotBlank(roleId)){
            List<String> userList = Arrays.asList(idStr.split(","));
            map.put("userList",userList);
            map.put("roleId",roleId);
            secRoleMemberService.removeUser(map);
        }else {
            return ResultGenerator.genFailResult("参数不能为空");
        }
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/update")
    public Result update(SecRoleMember secRoleMember) {
        secRoleMemberService.update(secRoleMember);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SecRoleMember secRoleMember = secRoleMemberService.findById(id);
        return ResultGenerator.genSuccessResult(secRoleMember);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecRoleMember> list = secRoleMemberService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
