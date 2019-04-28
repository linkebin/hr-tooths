package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.ChargeProject;

import java.util.List;

/**
 * Created by CodeGenerator on 2018/11/27.
 */
public interface ChargeProjectService extends Service<ChargeProject> {
	List<ChargeProject> list(String orgId);
}
