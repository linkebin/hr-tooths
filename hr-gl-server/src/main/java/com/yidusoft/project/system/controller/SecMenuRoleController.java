package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.domain.SecMenuRole;
import com.yidusoft.project.system.service.SecMenuRoleService;
import com.yidusoft.project.system.service.SecMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/menu/role")
public class SecMenuRoleController {
    @Resource
    private SecMenuRoleService secMenuRoleService;

    @Resource
    private SecMenuService secMenuService;

    @PostMapping("/add")
    public Result add(SecMenuRole secMenuRole) {
        secMenuRoleService.save(secMenuRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        secMenuRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SecMenuRole secMenuRole) {
        secMenuRoleService.update(secMenuRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SecMenuRole secMenuRole = secMenuRoleService.findById(id);
        return ResultGenerator.genSuccessResult(secMenuRole);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecMenuRole> list = secMenuRoleService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 更新菜单权限
     */
    @PostMapping("/updateMenuPower")
    public Result updateMenuPower(String nodes,String roleId){
        if(StringUtils.isNotBlank(nodes)&&StringUtils.isNotBlank(roleId)){
            List<Map> list = (List<Map>) JSONObject.parse(nodes);
            for (int i = 0; i < list.size(); i++) {
                Map map = list.get(i);
                //添加权限
                if("true".equals(map.get("checked").toString())){
                    SecMenuRole secMenuRole = new SecMenuRole();
                    secMenuRole.setId(UUID.randomUUID().toString());
                    secMenuRole.setMenuId(map.get("id").toString());
                    secMenuRole.setRoleId(roleId);
                    secMenuRoleService.save(secMenuRole);
                }else {//取消权限
                    Condition condition = new Condition(SecMenuRole.class);
                    Example.Criteria criteria = condition.createCriteria();
                    criteria.andEqualTo("roleId",roleId);
                    criteria.andEqualTo("menuId",map.get("id").toString());
                    List<SecMenuRole> byCondition = secMenuRoleService.findByCondition(condition);
                    String ids =byCondition.stream().map(SecMenuRole::getIdString).collect(Collectors.joining(","));
                    secMenuRoleService.deleteByIds(ids);
                }
            }
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("参数不能为空");
        }
    }

    /**
     * 更新按钮权限
     */
    @PostMapping("/updateButtonPower")
    public Result updateButtonPower(String buttonId,String roleId,String menuId,String checked){
            if(StringUtils.isNotBlank(roleId)){
                //判断是否是全选
                if(StringUtils.isNotBlank(menuId)){
                    Condition condition = new Condition(SecMenu.class);
                    Example.Criteria criteria = condition.createCriteria();
                    criteria.andEqualTo("deleted",0);
                    criteria.andEqualTo("parentId",menuId);
                    criteria.andEqualTo("menuType",2);
                    List<SecMenu> list = secMenuService.findByCondition(condition);

                    List<String> menuIds =list.stream().map(SecMenu::getId).collect(Collectors.toList());
                    Condition condition1 = new Condition(SecMenuRole.class);
                    Example.Criteria criteria1 = condition1.createCriteria();
                    criteria1.andEqualTo("roleId",roleId);
                    criteria1.andIn("menuId",menuIds);
                    List<SecMenuRole> byCondition = secMenuRoleService.findByCondition(condition1);
                    String ids =byCondition.stream().map(SecMenuRole::getIdString).collect(Collectors.joining(","));
                    //全取消
                    if(StringUtils.isNotBlank(ids)){
                        secMenuRoleService.deleteByIds(ids);
                    }
                    if("true".equals(checked)){
                        List addList  = new ArrayList();
                        for (int i = 0; i < list.size(); i++) {
                            SecMenu secMenu = list.get(i);
                            SecMenuRole secMenuRole = new SecMenuRole();
                            secMenuRole.setId(UUID.randomUUID().toString());
                            secMenuRole.setRoleId(roleId);
                            secMenuRole.setMenuId(secMenu.getId());
                            secMenuRoleService.save(secMenuRole);
                        }

                    }

                }else {
                    if("true".equals(checked)){
                        SecMenuRole secMenuRole = new SecMenuRole();
                        secMenuRole.setId(UUID.randomUUID().toString());
                        secMenuRole.setRoleId(roleId);
                        secMenuRole.setMenuId(buttonId);
                        secMenuRoleService.save(secMenuRole);
                    }else {

                        Condition condition = new Condition(SecMenuRole.class);
                        Example.Criteria criteria = condition.createCriteria();
                        criteria.andEqualTo("roleId",roleId);
                        criteria.andEqualTo("menuId",buttonId);
                        List<SecMenuRole> byCondition = secMenuRoleService.findByCondition(condition);
                        String ids =byCondition.stream().map(SecMenuRole::getIdString).collect(Collectors.joining(","));
                        secMenuRoleService.deleteByIds(ids);
                    }
                }

                return ResultGenerator.genSuccessResult();
            }else {
                return ResultGenerator.genFailResult("参数不能为空");
            }
    }
}
