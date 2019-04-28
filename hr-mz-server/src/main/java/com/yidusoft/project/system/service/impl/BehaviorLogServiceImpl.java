package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.BehaviorLogMapper;
import com.yidusoft.project.system.domain.BehaviorLog;
import com.yidusoft.project.system.service.BehaviorLogService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class BehaviorLogServiceImpl extends AbstractService<BehaviorLog> implements BehaviorLogService {
    @Resource
    private BehaviorLogMapper behaviorLogMapper;

}
