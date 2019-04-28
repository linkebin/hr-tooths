package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.AccountRecordMapper;
import com.yidusoft.project.system.domain.AccountRecord;
import com.yidusoft.project.system.service.AccountRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
@Service
@Transactional
public class AccountRecordServiceImpl extends AbstractService<AccountRecord> implements AccountRecordService {
    @Resource
    private AccountRecordMapper accountRecordMapper;

    @Override
    public List<Map<String,Object>> getList(Map<String, Object> map){
        return accountRecordMapper.getList(map);
    }
}
