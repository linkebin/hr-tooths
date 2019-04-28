package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.ProjectManageMapper;
import com.yidusoft.project.system.domain.ProjectManage;
import com.yidusoft.project.system.service.ProjectManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2018/11/26.
 */
@Service
@Transactional
public class ProjectManageServiceImpl extends AbstractService<ProjectManage> implements ProjectManageService {
    @Resource
    private ProjectManageMapper projectManageMapper;

}
