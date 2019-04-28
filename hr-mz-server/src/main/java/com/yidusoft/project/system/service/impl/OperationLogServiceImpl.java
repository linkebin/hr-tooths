package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.OperationLogMapper;
import com.yidusoft.project.system.domain.OperationLog;
import com.yidusoft.project.system.service.OperationLogService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class OperationLogServiceImpl extends AbstractService<OperationLog> implements OperationLogService {
    @Resource
    private OperationLogMapper operationLogMapper;

}
