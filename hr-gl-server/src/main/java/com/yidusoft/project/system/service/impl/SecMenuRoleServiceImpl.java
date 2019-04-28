package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SecMenuRoleMapper;
import com.yidusoft.project.system.domain.SecMenuRole;
import com.yidusoft.project.system.service.SecMenuRoleService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecMenuRoleServiceImpl extends AbstractService<SecMenuRole> implements SecMenuRoleService {
    @Resource
    private SecMenuRoleMapper secMenuRoleMapper;

}
