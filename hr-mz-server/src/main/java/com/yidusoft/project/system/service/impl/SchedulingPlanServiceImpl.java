package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.SchedulingPlanMapper;
import com.yidusoft.project.system.domain.SchedulingPlan;
import com.yidusoft.project.system.service.SchedulingPlanService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class SchedulingPlanServiceImpl extends AbstractService<SchedulingPlan> implements SchedulingPlanService {
    @Resource
    private SchedulingPlanMapper schedulingPlanMapper;

}
