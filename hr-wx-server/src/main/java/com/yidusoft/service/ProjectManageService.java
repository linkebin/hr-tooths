package com.yidusoft.service;

import com.yidusoft.core.Service;
import com.yidusoft.domain.ProjectManage;

import java.util.List;

/**
 * Created by CodeGenerator on 2018/11/26.
 */
public interface ProjectManageService extends Service<ProjectManage> {
	List<ProjectManage> findProjectAll();
}
