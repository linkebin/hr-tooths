package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SysMessageMapper;
import com.yidusoft.project.system.domain.SysMessage;
import com.yidusoft.project.system.service.SysMessageService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class SysMessageServiceImpl extends AbstractService<SysMessage> implements SysMessageService {
    @Resource
    private SysMessageMapper sysMessageMapper;

}
