package com.yidusoft.service;

import com.yidusoft.core.Service;
import com.yidusoft.domain.ChargeProject;

import java.util.List;


/**
 * User: libf
 * Time: 2018-12-10 09:51
 */
public interface ChargeProjectService  extends Service<ChargeProject> {
	List<ChargeProject> findProjectAll();
}
