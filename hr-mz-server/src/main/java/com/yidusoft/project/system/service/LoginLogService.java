package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.LoginLog;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface LoginLogService extends Service<LoginLog> {
	List<LoginLog> getLoginLog(String account, String success);
}
