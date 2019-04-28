package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecMenuMember;

import java.util.Map;

public interface SecMenuMemberMapper extends Mapper<SecMenuMember> {
    /**
     * 删除菜单
     * @param map
     */
    void  delMemberMapper(Map<String,Object> map);
}