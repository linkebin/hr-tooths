package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.InOrderInMapper;
import com.yidusoft.project.system.domain.InOrderIn;
import com.yidusoft.project.system.service.InOrderInService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class InOrderInServiceImpl extends AbstractService<InOrderIn> implements InOrderInService {
    @Resource
    private InOrderInMapper inOrderInMapper;

}
