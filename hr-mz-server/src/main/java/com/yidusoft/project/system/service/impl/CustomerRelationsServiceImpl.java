package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.CustomerRelationsMapper;
import com.yidusoft.project.system.domain.CustomerRelations;
import com.yidusoft.project.system.service.CustomerRelationsService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/03/26.
 */
@Service
@Transactional
public class CustomerRelationsServiceImpl extends AbstractService<CustomerRelations> implements CustomerRelationsService {
    @Resource
    private CustomerRelationsMapper customerRelationsMapper;

}
