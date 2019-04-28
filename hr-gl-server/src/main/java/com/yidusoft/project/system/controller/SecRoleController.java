package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.service.SecRoleService;
import com.yidusoft.utils.CodeHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/role")
public class SecRoleController {
    @Resource
    private SecRoleService secRoleService;

    /***
     * 查询用户的所有角色和不属于用户的角色
     * @param userId
     * @return
     */
    @GetMapping("/getRoleForUser")
    public  Result getRoleForUser(String userId) {
        List<Map<String,Object>> list=secRoleService.getRoleForUser(userId);
        return ResultGenerator.genSuccessResult(list);
    }


    @PostMapping("/add")
    public Result add(String json) {
        SecRole secRole= JSON.parseObject(json,SecRole.class);
        if(secRole.getState()==null){
            secRole.setState("0");
        }
        secRole.setDeleted(0);
        secRole.setRoleCode(CodeHelper.getCode("RL"));
        secRole.setId(UUID.randomUUID().toString());
        secRoleService.save(secRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String delIds) throws Exception {
        secRoleService.delRule(delIds);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String json) {
        SecRole secRole= JSON.parseObject(json,SecRole.class);
        if(secRole.getState()==null){
            secRole.setState("0");
        }
        secRoleService.update(secRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SecRole secRole = secRoleService.findById(id);
        return ResultGenerator.genSuccessResult(secRole);
    }

    @PostMapping("/openOrClose")
    public Result openOrClose(String idStr,String state) {
        String[] ids = null;
        if(StringUtils.isNotBlank(idStr)){
            ids = idStr.split(",");
        }
        if(ids!=null&&ids.length>0){
            for (int i = 0; i <ids.length ; i++) {
                if(StringUtils.isNotBlank(ids[i])){
                    SecRole secRole = secRoleService.findById(ids[i]);
                    secRole.setState(state);
                    secRoleService.update(secRole);
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,String roleName,String state) {
        Condition condition = new Condition(SecRole.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("deleted",0);
        if(StringUtils.isNotBlank(roleName)){
            criteria.andLike("roleName",roleName);
        }
        if(StringUtils.isNotBlank(state)){
            criteria.andEqualTo("state",state);
        }
        PageHelper.startPage(page, size);
        List<SecRole> list = secRoleService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }
}
