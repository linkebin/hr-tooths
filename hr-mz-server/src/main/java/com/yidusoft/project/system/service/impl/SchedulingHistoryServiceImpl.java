package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SchedulingHistoryMapper;
import com.yidusoft.project.system.domain.SchedulingHistory;
import com.yidusoft.project.system.service.SchedulingHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/28.
 */
@Service
@Transactional
public class SchedulingHistoryServiceImpl extends AbstractService<SchedulingHistory> implements SchedulingHistoryService {
    @Resource
    private SchedulingHistoryMapper schedulingHistoryMapper;

}
