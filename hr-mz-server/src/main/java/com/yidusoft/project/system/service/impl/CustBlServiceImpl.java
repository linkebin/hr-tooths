package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.CustBlMapper;
import com.yidusoft.project.system.domain.CustBl;
import com.yidusoft.project.system.service.CustBlService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class CustBlServiceImpl extends AbstractService<CustBl> implements CustBlService {
    @Resource
    private CustBlMapper custBlMapper;

}
