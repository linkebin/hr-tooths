package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SysOptionMapper;
import com.yidusoft.project.system.domain.SysOption;
import com.yidusoft.project.system.service.SysOptionService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SysOptionServiceImpl extends AbstractService<SysOption> implements SysOptionService {
    @Resource
    private SysOptionMapper sysOptionMapper;

}
