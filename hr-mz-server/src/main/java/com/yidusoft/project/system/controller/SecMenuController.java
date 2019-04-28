package com.yidusoft.project.system.controller;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.SecMenuService;
import com.yidusoft.utils.Security;
import com.yidusoft.utils.TreeBuilder;
import com.yidusoft.utils.TreeNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/menu")
public class SecMenuController {
    @Resource
    private SecMenuService secMenuService;
    @Resource
    private DaoService daoService;

    /***
     * 分页查询所有的菜单信息
     * @param param  查询条件
     * @param size 显示的条数
     * @param page 当前页
     * @return
     */
    @GetMapping("/menuList")
    public Result menuList(String param,Integer  page,Integer size) {
        SecMenu secMenu =null;
        if(!StringUtils.isEmpty(param)){
            secMenu= JSON.parseObject(param,SecMenu.class);
        }else {
            secMenu =new SecMenu();
        }
        PageHelper.startPage(page,size);
        List<Map<String,Object>> secMenuList=secMenuService.menuList(secMenu);
        PageInfo pageInfo = new PageInfo(secMenuList);

        return ResultGenerator.genTableListResult(pageInfo);
    }

    /***
     * 添加菜单
     * @param param
     * @return
     */
    @PostMapping("/addMenu")
    public Result addMenu(String param) {
        try {
            SecMenu  secMenu= JSON.parseObject(param,SecMenu.class);
            secMenu.setStanbyId1("1");
            secMenuService.addOrUpdateMenu(secMenu);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
    /***
     * 修改菜单
     * @param param
     * @return
     */
    @PostMapping("/updateMenu")
    public Result updateMenu(String param) {
        try {
            SecMenu  secMenu= JSON.parseObject(param,SecMenu.class);
            secMenuService.addOrUpdateMenu(secMenu);
        }catch (Exception ex){
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /***
     * 树形结构
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/tree")
    public Result tree(HttpServletResponse response, HttpServletRequest request) {
        List<TreeNode> nodes = secMenuService.getTree(Security.getUserId());
        List<TreeNode> treeNodes = TreeBuilder.buildByRecursive(nodes);
        Result result = ResultGenerator.genSuccessResult(treeNodes);
        return result;
    }


    /**
     * 查询父的的子菜单的总数
     * @param id
     * @return
     */
    @GetMapping("/findByParentIdNum")
    public Result findByParentIdNum(String id) {
        SecMenu secMenu=new SecMenu();
        secMenu.setId(id);
        List<Integer> nums=secMenuService.findByParentIdNum(secMenu);
        return ResultGenerator.genSuccessResult(nums);
    }

    /**
     * 分页查询父的的子菜单
     * @param id
     * @return
     */
    @GetMapping("/findByParentId")
    public Result findByParentId(String id,Integer  page,Integer size) {
        SecMenu secMenu =new SecMenu();
        secMenu.setId(id);
        PageHelper.startPage(page,size);
        List<Map<String,Object>> secMenuList=secMenuService.findByParentId(secMenu);
        PageInfo pageInfo = new PageInfo(secMenuList);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    /**
     * 查看菜单的详情
     * @param menuId
     * @return
     */
    @GetMapping("/menuInfo")
    public Result menuInfo(String menuId) {
         Map<String,Object> map=secMenuService.menuInfo(menuId);
        return ResultGenerator.genSuccessResult(map);
    }

    /***
     * 删除菜单
     * @param param  多个id
     * @return
     */
   @PostMapping("/delMenu")
   public Result delMenu(String param) {
       try {
           List<SecMenu> secMenuList=   JSON.parseArray(param,SecMenu.class);
           secMenuService.updateMenu(secMenuList);
       } catch (Exception e) {
           e.printStackTrace();
           return ResultGenerator.genFailResult(e.getMessage());
       }

       return ResultGenerator.genSuccessResult();
   }
    /***
     * 获取所有按钮权限
     * @param
     * @return
     */
    @PostMapping("/getAllButtonMenu")
   public Result getButtonMenuByRole(String roleId,String menuId){
        Map map = new HashMap();
        map.put("roleId",roleId);
        map.put("menuId",menuId);
       return ResultGenerator.genSuccessResult(secMenuService.getButtonMenuByRole(map));
   }

    /**
     * 查询用户的所有菜单，角色菜单,组织菜单,用户菜单
     * 返回的tree结构
     * @param userId
     * @return
     */
    @GetMapping("/getMenuForUserAll")
    public String getMenuForUserAll(String userId) {
        List<Map<String,Object>>  list=secMenuService.getMenuForUserAll(userId);
        return JSON.toJSONString(list) ;
    }

    /**
     * 查询当前用户的所有菜单，角色菜单,组织菜单,用户菜单
     * @return
     */
    @GetMapping("/findMenuByUser")
    public String findMenuByUser() {
        List<Map<String,Object>>  list=secMenuService.getMenuForUser(Security.getUserId());
        System.out.println(list);
        return JSON.toJSONString(list) ;
    }


   /***
     * 根据角色获取角色权限树
     * @param
     * @return
     */
    @PostMapping("/getMenuTreeByRole")
   public List<Map> getMenuTreeByRole(String roleId){
       return secMenuService.getMenuTreeByRole(roleId);
   }

    /***
     * 查询用户所选菜单的所有子按钮《角色菜单,组织菜单,用户菜单》
     * @param userId 用户id
     * @param parentId  菜单id
     * @return
     */
    @GetMapping("/getMenuForUserBntAll")
    public Result getMenuForUserBntAll(String userId,String parentId) {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("parentId",parentId);
        List<Map<String,Object>>  list=secMenuService.getMenuForUserBntAll(map);
        return ResultGenerator.genSuccessResult(list) ;
    }

    /**
    * @Author: didi_c
    * @Description: 获取菜单对应url
    * @Date: 2018/11/14 11:44
    * @Param: id
    * @returns: secMenu
    */
    @PostMapping("/getUrl")
    public SecMenu getUrl(String id){
        SecMenu secMenu=daoService.getObject("select * from sec_menu where ID_='"+id+"'",SecMenu.class);
        return secMenu;
    }

}
