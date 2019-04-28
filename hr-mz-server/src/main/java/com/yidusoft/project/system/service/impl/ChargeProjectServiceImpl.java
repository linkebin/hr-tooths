package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.ChargeProjectMapper;
import com.yidusoft.project.system.domain.ChargeProject;
import com.yidusoft.project.system.service.ChargeProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/11/27.
 */
@Service
@Transactional
public class ChargeProjectServiceImpl extends AbstractService<ChargeProject> implements ChargeProjectService {
    @Resource
    private ChargeProjectMapper chargeProjectMapper;


    @Override
    public List<ChargeProject> list(String orgId) {
        return chargeProjectMapper.list(orgId);
    }
}
