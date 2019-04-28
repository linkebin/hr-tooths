package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.CustJzMapper;
import com.yidusoft.project.system.domain.CustJz;
import com.yidusoft.project.system.service.CustJzService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class CustJzServiceImpl extends AbstractService<CustJz> implements CustJzService {
    @Resource
    private CustJzMapper custJzMapper;

}
