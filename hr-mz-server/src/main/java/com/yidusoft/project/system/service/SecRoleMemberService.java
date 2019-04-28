package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecRoleMember;

import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface SecRoleMemberService extends Service<SecRoleMember> {
    void removeUser(Map map);

    /**
     * 删除用户的多个角色
     * @param userId 多个用户的id
     * @param roleIds 角色id
     * @throws Exception
     */
    void delRoleForUser(String userId,String roleIds) throws Exception;

    /**
     * 用户添加多个角色
     * @param userId 多个用户的id
     * @param roleIds 角色id
     * @throws Exception
     */
    void addRoleForUser(String userId,String roleIds) throws Exception;
}
