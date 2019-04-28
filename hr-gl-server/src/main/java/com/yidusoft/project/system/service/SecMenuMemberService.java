package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.SecMenuMember;
import com.yidusoft.core.Service;

import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface SecMenuMemberService extends Service<SecMenuMember> {


    /***
     * 用户添加菜单
     * @param userId
     * @param menuIds  多个菜单id 用逗号隔开
     */
    void addMenuMember(String userId,String menuIds) throws Exception;

    /**
     * 删除菜单
     *
     */
    void  delMemberMapper(String userId, String menuIds)throws Exception;
}
