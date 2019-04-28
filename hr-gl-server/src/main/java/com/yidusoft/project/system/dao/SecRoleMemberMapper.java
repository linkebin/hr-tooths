package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecRoleMember;

import java.util.Map;

public interface SecRoleMemberMapper extends Mapper<SecRoleMember> {
    void removeUser(Map map);
}