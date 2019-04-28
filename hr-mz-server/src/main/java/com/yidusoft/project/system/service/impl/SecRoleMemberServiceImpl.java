package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SecRoleMemberMapper;
import com.yidusoft.project.system.domain.SecRoleMember;
import com.yidusoft.project.system.service.SecRoleMemberService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecRoleMemberServiceImpl extends AbstractService<SecRoleMember> implements SecRoleMemberService {
    @Resource
    private SecRoleMemberMapper secRoleMemberMapper;

    @Override
    public void removeUser(Map map) {
        secRoleMemberMapper.removeUser(map);
    }
    /**
     * 删除用户的多个角色
     * @param userId 用户的id
     * @param roleIds 多个角色id
     * @throws Exception
     */
    @Override
    public void delRoleForUser(String userId, String roleIds) throws Exception {
         if(!("").equals(userId) && !("").equals(roleIds) ){
              String []  roleIdArray=roleIds.split(",");
              for (int i=0;i< roleIdArray.length;i++){
                  Map<String,Object> map=new HashMap<>();
                  List<String> userList =new ArrayList<>();
                  userList.add(userId);
                  map.put("userList",userList);
                  map.put("roleId",roleIdArray[i]);
                  this.removeUser(map);
              }
         }else {
             throw  new Exception("数据为空！");
         }
    }

    /**
     * 用户添加多个角色
     * @param userId 多个用户的id
     * @param roleIds 角色id
     * @throws Exception
     */
    @Override
    public void addRoleForUser(String userId, String roleIds) throws Exception {
        if(!("").equals(userId) && !("").equals(roleIds) ){
            String []  roleIdArray=roleIds.split(",");
            SecRoleMember secRoleMember=new SecRoleMember();
            secRoleMember.setUserId(userId);
            for (int i=0;i< roleIdArray.length;i++){
                secRoleMember.setId(UUID.randomUUID().toString());
                secRoleMember.setRoleId(roleIdArray[i]);
                this.save(secRoleMember);
            }
        }else {
            throw  new Exception("数据为空！");
        }
    }
}
