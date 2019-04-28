package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.ToothOrderMapper;
import com.yidusoft.project.system.domain.ToothOrder;
import com.yidusoft.project.system.service.ToothOrderService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class ToothOrderServiceImpl extends AbstractService<ToothOrder> implements ToothOrderService {
    @Resource
    private ToothOrderMapper toothOrderMapper;

}
