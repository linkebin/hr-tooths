package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SecMenuOrgMapper;
import com.yidusoft.project.system.domain.SecMenuOrg;
import com.yidusoft.project.system.service.SecMenuOrgService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecMenuOrgServiceImpl extends AbstractService<SecMenuOrg> implements SecMenuOrgService {
    @Resource
    private SecMenuOrgMapper secMenuOrgMapper;

}
