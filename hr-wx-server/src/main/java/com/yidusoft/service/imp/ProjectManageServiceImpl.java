package com.yidusoft.service.imp;

import com.yidusoft.core.AbstractService;
import com.yidusoft.dao.ProjectManageMapper;
import com.yidusoft.domain.ProjectManage;
import com.yidusoft.service.ProjectManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by CodeGenerator on 2018/11/26.
 */
@Service
@Transactional
public class ProjectManageServiceImpl extends AbstractService<ProjectManage> implements ProjectManageService {
    @Resource
    private ProjectManageMapper projectManageMapper;

    @Override
    public List<ProjectManage> findProjectAll() {
        return projectManageMapper.findProjectAll();
    }
}
