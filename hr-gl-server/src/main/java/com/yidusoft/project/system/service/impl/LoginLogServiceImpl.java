package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.LoginLogMapper;
import com.yidusoft.project.system.domain.LoginLog;
import com.yidusoft.project.system.service.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class LoginLogServiceImpl extends AbstractService<LoginLog> implements LoginLogService {
    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public List<LoginLog> getLoginLog(String account, String success) {
        return loginLogMapper.getLoginLog(account,success);
    }
}
