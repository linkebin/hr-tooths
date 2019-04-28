package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.CustReturnMapper;
import com.yidusoft.project.system.domain.CustReturn;
import com.yidusoft.project.system.service.CustReturnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2019/03/02.
 */
@Service
@Transactional
public class CustReturnServiceImpl extends AbstractService<CustReturn> implements CustReturnService {
    @Resource
    private CustReturnMapper custReturnMapper;

    @Override
    public  List<Map<String,Object>> getReturnS(String orgId, String custId){
      return   custReturnMapper.getReturnS(orgId,custId);
    }

}
