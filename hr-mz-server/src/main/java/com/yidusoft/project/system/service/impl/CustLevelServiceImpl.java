package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.CustLevelMapper;
import com.yidusoft.project.system.domain.CustLevel;
import com.yidusoft.project.system.service.CustLevelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class CustLevelServiceImpl extends AbstractService<CustLevel> implements CustLevelService {
    @Resource
    private CustLevelMapper custLevelMapper;

    @Override
    public List<Map<String,Object>> getCustLevel() {
        return custLevelMapper.getCustLevel();
    }

    @Override
    public List<CustLevel> findCustLevel(String parentId,int limit,int page) {
        return custLevelMapper.findCustLevel(parentId,limit,page);
    }

    @Override
    public int findCustLevelCount(String parentId) {
        return custLevelMapper.findCustLevelCount(parentId);
    }
}
