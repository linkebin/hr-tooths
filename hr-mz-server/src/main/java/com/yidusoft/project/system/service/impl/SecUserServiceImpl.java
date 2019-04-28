package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecUserMapper;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by CodeGenerator on 2018/03/28.
 */
@Service
@Transactional
public class SecUserServiceImpl extends AbstractService<SecUser> implements SecUserService {
    @Resource
    private SecUserMapper secUserMapper;

    /**
     * 通过账号查询用户
     * @param account
     * @return
     */
    @Override
    public SecUser findUserForName(String account) {
        return secUserMapper.findUserForName(account);
    }
    @Override
    public SecUser getSecUserInfo(String account) {
        return secUserMapper.getSecUserInfo(account);
    }

    /***
     * 分页查询所有的用户
     * @param secUser 条件
     * @return
     */
    @Override
    public List<Map<String, Object>> getSecUserForPaging(SecUser secUser) {
        return secUserMapper.getSecUserForPaging(secUser);
    }
    /***
     * 查询所有的部门和用户 id，name，parentId 做树形结构
     * @param secUser  条件
     * @return
     */
    @Override
    public List<Map<String, Object>> getSecUserTreeForOrg(SecUser secUser) {
        return secUserMapper.getSecUserTreeForOrg(secUser);
    }
    /***
     * 分页查询某组织下的所有的用户
     * @param orgId 组织id
     * @return
     */
    @Override
    public List<Map<String, Object>> getSecUserForOrg(String orgId) {
        return secUserMapper.getSecUserForOrg(orgId);
    }

    /**
     *查询用户的详细信息
     * @param userId 用户ID
     * @return
     */
    @Override
    public Map<String, Object> getSecUserBYID(String userId) {

         return secUserMapper.getSecUserBYID(userId);
    }
    /**
     * 添加用户
     * @param secUser 对象信息
     * @throws Exception
     */
    @Override
    public void addSecUser(SecUser secUser) throws Exception {
        secUser.setId(UUID.randomUUID().toString());
        secUser.setDeleted(0);
        secUser.setCreateTime(new Date(System.currentTimeMillis()));
        secUser.setCreator(Security.getUser().getUserName());
        secUser.setOrgId(Security.getUser().getOrgId());
        this.save(secUser);
    }
    /***
     * 查询账号是否存在
     * @param account
     * @return
     */
    @Override
    public int isExistForAccount(String account) {

        return    secUserMapper.isExistForAccount(account);
    }

    @Override
    public List getSecUserByRole(Map map) {
        return secUserMapper.getSecUserByRole(map);
    }

    @Override
    public List getSecUserByNotRole(Map map) {
        return secUserMapper.getSecUserByNotRole(map);
    }

}
