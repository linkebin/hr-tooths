package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SecUserMapper extends Mapper<SecUser> {
    SecUser getSecUserInfo(String account);
    /**
     * 通过账号查询用户
     * @param account
     * @return
     */
    SecUser  findUserForName (@Param(value = "account") String account);

    /***
     * 分页查询所有的用户
     * @param secUser 条件
     * @return
     */
    List<Map<String,Object>> getSecUserForPaging(SecUser secUser);


    /***
     * 查询所有的部门和用户 id，name，parentId 做树形结构
     * @param secUser  条件
     * @return
     */
    List<Map<String,Object>> getSecUserTreeForOrg(SecUser secUser);

    /***
     * 分页查询某组织下的所有的用户
     * @param orgId 组织id
     * @return
     */
    List<Map<String,Object>>  getSecUserForOrg(@Param(value = "orgId") String orgId);

    /**
     *查询用户的详细信息
     * @param userId 用户ID
     * @return
     */
     Map<String, Object> getSecUserBYID(@Param(value = "userId") String userId);

    /***
     * 查询账号是否存在
     * @param account
     * @return
     */
    int isExistForAccount(@Param(value = "account") String account)  ;

    List  getSecUserByRole(Map map);

    List  getSecUserByNotRole(Map map);

}
