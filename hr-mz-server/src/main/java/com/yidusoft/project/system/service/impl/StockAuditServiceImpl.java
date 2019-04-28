package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.StockAuditMapper;
import com.yidusoft.project.system.domain.StockAudit;
import com.yidusoft.project.system.service.StockAuditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class StockAuditServiceImpl extends AbstractService<StockAudit> implements StockAuditService {
    @Resource
    private StockAuditMapper stockAuditMapper;

}
