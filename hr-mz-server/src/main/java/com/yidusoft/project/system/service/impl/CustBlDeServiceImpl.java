package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.CustBlDeMapper;
import com.yidusoft.project.system.domain.CustBlDe;
import com.yidusoft.project.system.service.CustBlDeService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class CustBlDeServiceImpl extends AbstractService<CustBlDe> implements CustBlDeService {
    @Resource
    private CustBlDeMapper custBlDeMapper;

}
