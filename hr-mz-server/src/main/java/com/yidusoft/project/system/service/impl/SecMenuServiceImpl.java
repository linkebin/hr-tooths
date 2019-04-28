package com.yidusoft.project.system.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecMenuMapper;
import com.yidusoft.project.system.domain.SecMenu;
import com.yidusoft.project.system.service.SecMenuService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import com.yidusoft.utils.TreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecMenuServiceImpl extends AbstractService<SecMenu> implements SecMenuService {
    @Resource
    private SecMenuMapper secMenuMapper;

    /***
     *分页查询菜单列表
     * @param secMenu 条件
     * @return
     */
    @Override
    public  List<Map<String,Object>>  menuList(SecMenu secMenu) {
        return secMenuMapper.menuList(secMenu);
    }

    @Override
    public List<SecMenu> queryAll() {
        return secMenuMapper.queryAll();
    }

    /**
     * 查询某个用户的权限（包含角色和组织的权限）
     * @param userId
     * @return
     */
    @Override
    public List<SecMenu> findMenuByUser(String userId) {
        List<SecMenu> menuList = secMenuMapper.findMenuByUser(userId);
        return menuList;
    }

    @Override
    public List findMenuForButtonByUser(String userId) {

        return secMenuMapper.findMenuForButtonByUser(userId);
    }

    //树结构
    @Override
    public List<TreeNode> getTree(String userId) {
        return secMenuMapper.getTree(userId);
    }


    /**
     * 查询父的的子菜单的总数
     * @param secMenu
     * @return
     */
    @Override
    public List<Integer> findByParentIdNum(SecMenu secMenu) {
        return secMenuMapper.findByParentIdNum(secMenu);
    }

    /**
     * 查询父的的子菜单
     * @param secMenu
     * @return
     */
    @Override
    public List<Map<String, Object>> findByParentId(SecMenu secMenu) {
        return secMenuMapper.findByParentId(secMenu);
    }

    /***
     * 添加或者修改菜单
     * @param secMenu
     * @throws Exception
     */
    @Override
    public void   addOrUpdateMenu(SecMenu secMenu)throws Exception{

        /*
        if(StringUtils.isEmpty(secMenu.getParentId()) ){
            secMenu.setParentId("0");
        }else {
            getSort(secMenu.getSort(),secMenu.getParentId());
        }*/
        if(StringUtils.isEmpty(secMenu.getId())){
            secMenu.setId(UUID.randomUUID().toString());
            secMenu.setMenuCode(CodeHelper.getCode("CD"));
            secMenu.setCreator(Security.getUser().getUserName());
            secMenu.setCreateTime(new Date(System.currentTimeMillis()));
            getSort(secMenu.getSort(),-1,secMenu.getParentId());
            this.save(secMenu);
        }else {
            SecMenu menu=this.findById(secMenu.getId());
            int oldSort=menu.getSort();
            getSort(secMenu.getSort(),oldSort,secMenu.getParentId());
            if(secMenu.getEnabled()==null){
                secMenu.setEnabled(0);
            }
            this.update(secMenu);
        }

    }

    /***
     * 排序
     * @param sort 新增菜单的序号
     * @param parentId  父级的id
     */
    @Override
    public void  getSort(int sort,int oldSort,String parentId)throws Exception{
        SecMenu secMenu=   new SecMenu();
        secMenu.setId(parentId);
        //通过parentId 父级id找到所有的子级
        List<Map<String, Object>> parentList=findByParentId(secMenu);
            for(Map<String, Object> map :parentList){
                int menuSort=Integer.valueOf(map.get("SORT").toString());
                    SecMenu secMenus=   new SecMenu();
                    secMenus.setId(map.get("ID_").toString());
                     //oldSort 为-1 表示新增只用递减后面的序号
                    if(oldSort==-1){
                        if(menuSort>=sort){
                            secMenus.setSort(menuSort+1);
                        }else {
                            continue;
                        }
                    }else if(oldSort==-2 ){
                        //删除 时 排序
                        if(menuSort>sort){
                            secMenus.setSort(menuSort-1);
                        }else {
                            continue;
                        }
                    } else if ( sort<oldSort && sort<= menuSort && menuSort<oldSort){
                       // sort=< menuSort  && menuSort<oldSort
                        secMenus.setSort(menuSort+1);
                    } else if(sort>oldSort && oldSort< menuSort &&  menuSort<=sort ){
                        // oldSort< menuSort  && menuSort<=sort
                        secMenus.setSort(menuSort-1);
                    }else if(sort==oldSort ){
                        break;
                    }else{
                        continue;
                    }

                    this.update(secMenus);
            }


    }

    /**
     * 查看菜单的详情
     * @param menuId
     * @return
     */
    @Override
    public Map<String, Object> menuInfo(String menuId) {
        return secMenuMapper.menuInfo(menuId);
    }

    /**
     * 多单选修改菜单状态
     * @param secMenuList
     */
    @Override
    public void updateMenu(List<SecMenu> secMenuList) throws Exception  {
        for(SecMenu sm:secMenuList){
            this.update(sm);
            //判断是否是删除
            if(sm.getDeleted()!=null){
                SecMenu secMenu=this.findById(sm.getId());
                //排序
                getSort(secMenu.getSort(),-2,secMenu.getParentId());
                //查询是否存在子菜单
                List<Map<String,Object>>  list=  findByParentId(sm);
                if(list.size()>0){
                    for(Map<String,Object> map: list){
                        sm.setId(map.get("ID_").toString());
                        this.update(sm);
                    }
                }
            }

        }


    }

    @Override
    public List getButtonMenuByRole(Map map ) {
            return  secMenuMapper.getButtonMenuByRole(map);
    }

    @Override
    public List<Map> getMenuTreeByRole(String roleId) {
        return secMenuMapper.getMenuTreeByRole(roleId);
    }
    /**
     * 查询用户的所有菜单，角色菜单,组织菜单,用户菜单
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getMenuForUserAll(String userId) {
        return secMenuMapper.getMenuForUserAll(userId);
    }

    /***
     *查询用户的菜单,现在做二级菜单，上面一层左边一层
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getMenuForUser(String userId) {
        List<SecMenu>  list=secMenuMapper.findMenuByUser(Security.getUserId());
        List<Map<String, Object>> mapList=new ArrayList<>();
        for(SecMenu secMenu:list){
            if("0".equals(secMenu.getParentId()) && secMenu.getMenuType()==1){
                Map<String,Object> map=new HashMap<>();
                map.put("name",secMenu.getMenuName());
                map.put("icon",secMenu.getIcon());
                map.put("id",secMenu.getId());
                //二级子菜单
                List<Map<String, Object>>  childList=new ArrayList<>();
                for(SecMenu s:list){
                    if(secMenu.getId().equals(s.getParentId()) && s.getMenuType()==1){
                        Map<String,Object>  childs= new HashMap<>();
                        childs.put("name",s.getMenuName());
                        childs.put("icon",s.getIcon());
                        childs.put("id",s.getId());
                        childs.put("url",s.getUrl());
                        childList.add(childs);
                    }
                }
                map.put("childs",childList);
                mapList.add(map);
            }
        }

        return mapList;
    }

    /***
     *查询用户所选菜单的所有子按钮《角色菜单,组织菜单,用户菜单》
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> getMenuForUserBntAll(Map<String, Object> map) {
        return secMenuMapper.getMenuForUserBntAll(map);
    }


}
