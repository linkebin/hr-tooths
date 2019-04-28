package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SysTypeMapper;
import com.yidusoft.project.system.domain.SysType;
import com.yidusoft.project.system.service.SysTypeService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class SysTypeServiceImpl extends AbstractService<SysType> implements SysTypeService {
    @Resource
    private SysTypeMapper sysTypeMapper;

}
