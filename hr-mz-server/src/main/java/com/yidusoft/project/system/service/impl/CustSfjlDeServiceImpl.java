package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.CustSfjlDeMapper;
import com.yidusoft.project.system.domain.CustSfjlDe;
import com.yidusoft.project.system.service.CustSfjlDeService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class CustSfjlDeServiceImpl extends AbstractService<CustSfjlDe> implements CustSfjlDeService {
    @Resource
    private CustSfjlDeMapper custSfjlDeMapper;

}
