package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SysAccessoryMapper;
import com.yidusoft.project.system.domain.SysAccessory;
import com.yidusoft.project.system.service.SysAccessoryService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class SysAccessoryServiceImpl extends AbstractService<SysAccessory> implements SysAccessoryService {
    @Resource
    private SysAccessoryMapper sysAccessoryMapper;

}
