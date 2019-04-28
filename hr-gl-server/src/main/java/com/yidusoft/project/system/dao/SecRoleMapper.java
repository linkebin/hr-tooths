package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SecRoleMapper extends Mapper<SecRole> {

    /***
     * 查询用户的所有角色和不属于用户的角色
     * @param userId
     * @return
     */
     List<Map<String,Object>> getRoleForUser(@Param(value = "userId") String userId);
}