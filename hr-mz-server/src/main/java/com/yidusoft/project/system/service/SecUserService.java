package com.yidusoft.project.system.service;
import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface SecUserService extends Service<SecUser> {
    /**
     * 通过账号查询用户
     * @param account
     * @return
     */
    SecUser  findUserForName (String account);
    SecUser getSecUserInfo(String account);


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
    List<Map<String,Object>>  getSecUserForOrg(String orgId);

    /**
     *查询用户的详细信息
     * @param userId 用户ID
     * @return
     */
    Map<String,Object> getSecUserBYID(String userId);

    /**
     * 添加用户
     * @param secUser 对象信息
     * @throws Exception
     */
    void addSecUser(SecUser secUser) throws  Exception;
    /***
     * 查询账号是否存在
     * @param account
     * @return
     */
    int isExistForAccount( String account)  ;
    List   getSecUserByRole(Map map);

    List   getSecUserByNotRole(Map map);



}
