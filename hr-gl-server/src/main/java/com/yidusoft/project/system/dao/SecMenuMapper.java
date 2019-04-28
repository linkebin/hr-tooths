package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.utils.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SecMenuMapper extends Mapper<SecMenu> {
    List<SecMenu> queryAll();

    List<SecMenu> findMenuByUser(String userId);

    //树结构
    List<TreeNode> getTree(String userId);


    List findMenuForButtonByUser(String userId);


    /***
     *分页查询菜单列表
     * @param secMenu 条件
     * @return
     */
    List<Map<String,Object>>  menuList(SecMenu secMenu);

    /**
     * 分页查询父的的子菜单的总数
     * @param secMenu
     * @return
     */
    List<Integer> findByParentIdNum(SecMenu secMenu);

    /**
     * 分页查询父的的子菜单
     * @param secMenu
     * @return
     */
    List<Map<String,Object>>   findByParentId(SecMenu secMenu);

    /**
     * 查看菜单的详情
     * @param menuId
     * @return
     */
    Map<String,Object>  menuInfo (@Param(value = "menuId") String   menuId);

     List<Map> getButtonMenuByRole(Map map);

     List<Map> getMenuTreeByRole(String roleId);

    /**
     * 查询用户的所有菜单，角色菜单,组织菜单,用户菜单
     * @param userId
     * @return
     */
    List<Map<String,Object>> getMenuForUserAll(@Param(value = "userId") String   userId);

    /***
     *查询用户所选菜单的所有子按钮《角色菜单,组织菜单,用户菜单》
     * @param map
     * @return
     */
    List<Map<String,Object>> getMenuForUserBntAll(Map<String,Object> map);
}