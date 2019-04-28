package com.yidusoft.project.system.service;
import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.utils.TreeNode;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface SecMenuService extends Service<SecMenu> {

    /***
     *分页查询菜单列表
     * @param secMenu 条件
     * @return
     */
    List<Map<String,Object>>  menuList(SecMenu secMenu);


    List<SecMenu> queryAll();
    /**
     * 查询某个用户的权限
     * @param userId
     * @return
     */
    List<SecMenu> findMenuByUser(String userId);

  /**
     * 查询某个用户的权限
     * @param userId
     * @return
     */
    List findMenuForButtonByUser(String userId);

    /**
     * 树结构
     * @param userId
     * @return
     */
    List<TreeNode> getTree(String userId);

    /**
     * 查询父的的子菜单的总数
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

    /***
     * 或者修改菜单
     * @param secMenu
     * @throws Exception
     */
    public void   addOrUpdateMenu(SecMenu secMenu)throws Exception;

    /***
     * 排序
     * @param sort 新增菜单的序号
     * @param parentId  父级的id
     */
     void  getSort(int sort,int oldSort,String parentId)throws Exception;


    /**
     * 查看菜单的详情
     * @param menuId
     * @return
     */
    Map<String,Object>  menuInfo ( String   menuId);

    /**
     * 多单选修改菜单状态
     * @param secMenuList
     */
    void  updateMenu( List<SecMenu> secMenuList) throws Exception ;

    /**
     * 查询用户的所有菜单，角色菜单,组织菜单,用户菜单
     * @param userId
     * @return
     */
    List<Map<String,Object>> getMenuForUserAll( String   userId);

    /***
     * 查询用户的菜单
     * @param userId
     * @return
     */
    List<Map<String, Object>> getMenuForUser(String userId);


    /***
     *查询用户所选菜单的所有子按钮《角色菜单,组织菜单,用户菜单》
     * @param map
     * @return
     */
    List<Map<String,Object>> getMenuForUserBntAll(Map<String,Object> map);

    List  getButtonMenuByRole( Map map);

    List<Map> getMenuTreeByRole(String roleId);
}
