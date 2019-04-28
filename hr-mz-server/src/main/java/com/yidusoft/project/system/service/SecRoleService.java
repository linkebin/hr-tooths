package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.core.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface SecRoleService extends Service<SecRole> {
    void delRule(String delIds) throws Exception;

    /***
     * 查询用户的所有角色和不属于用户的角色
     * @param userId
     * @return
     */
    List<Map<String,Object>> getRoleForUser(String userId);

}
