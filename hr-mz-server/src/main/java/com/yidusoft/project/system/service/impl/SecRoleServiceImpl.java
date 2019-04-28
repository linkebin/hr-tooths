package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.core.ServiceException;
import com.yidusoft.project.system.dao.SecRoleMapper;
import com.yidusoft.project.system.domain.SecRole;
import com.yidusoft.project.system.domain.SecRoleMember;
import com.yidusoft.project.system.service.SecRoleMemberService;
import com.yidusoft.project.system.service.SecRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecRoleServiceImpl extends AbstractService<SecRole> implements SecRoleService {
    @Resource
    private SecRoleMapper secRoleMapper;

    @Resource
    private SecRoleMemberService secRoleMemberService;

    @Override
    public void delRule(String delIds) throws Exception {
        String[] ids = null;
        if(StringUtils.isNotBlank(delIds)){
            ids = delIds.split(",");
        }
        if(ids!=null&&ids.length>0){
            for (int i = 0; i <ids.length ; i++) {
                if(StringUtils.isNotBlank(ids[i])){
                    SecRole secRole = this.findById(ids[i]);
                    //校验
                    Condition condition = new Condition(SecRoleMember.class);
                    Example.Criteria criteria = condition.createCriteria();
                    criteria.andEqualTo("roleId",secRole.getId());
                    List<SecRoleMember> byModel = secRoleMemberService.findByCondition(condition);
                    if(byModel!=null&&byModel.size()>0){
                        throw  new ServiceException("角色("+secRole.getRoleName()+")未清空，不允许删除");
                    }
                    secRole.setDeleted(1);
                    this.update(secRole);
                }
            }
        }
    }

    /***
     * 查询用户的所有角色和不属于用户的角色
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> getRoleForUser(String userId) {
        return secRoleMapper.getRoleForUser(userId);
    }
}
