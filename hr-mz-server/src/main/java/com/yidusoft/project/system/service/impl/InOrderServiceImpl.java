package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.InOrderMapper;
import com.yidusoft.project.system.domain.InOrder;
import com.yidusoft.project.system.service.InOrderService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class InOrderServiceImpl extends AbstractService<InOrder> implements InOrderService {
    @Resource
    private InOrderMapper inOrderMapper;

}
