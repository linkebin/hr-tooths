package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SecMenuMemberMapper;
import com.yidusoft.project.system.domain.SecMenuMember;
import com.yidusoft.project.system.service.SecMenuMemberService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecMenuMemberServiceImpl extends AbstractService<SecMenuMember> implements SecMenuMemberService {
    @Resource
    private SecMenuMemberMapper secMenuMemberMapper;


    /***
     * 用户添加菜单
     * @param userId
     * @param menuIds  多个菜单id 用逗号隔开
     */
    @Override
    public void addMenuMember(String userId, String menuIds) throws Exception {
        String [] menuIdArray=menuIds.split(",");
        for(int i=0;i<menuIdArray.length;i++){
            SecMenuMember secMenuMember=new SecMenuMember();
            secMenuMember.setId(UUID.randomUUID().toString());
            secMenuMember.setMenuId(menuIdArray[i]);
            secMenuMember.setUserId(userId);
            this.save(secMenuMember);
        }
    }

    /**
     * 删除菜单
     * @param userId
     * @param menuIds
     */
    @Override
    public void delMemberMapper(String userId, String menuIds) throws Exception {
          Map<String,Object> map=new HashMap<>();
          map.put("userId",userId);
          map.put("menuIds",menuIds.split(","));
        secMenuMemberMapper.delMemberMapper(map);
    }
}
