package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginLogMapper extends Mapper<LoginLog> {
	List<LoginLog> getLoginLog(@Param("account") String account, @Param("success") String success);
}