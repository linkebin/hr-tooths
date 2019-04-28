package com.yidusoft.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.domain.ProjectManage;

import java.util.List;

public interface ProjectManageMapper extends Mapper<ProjectManage> {
	List<ProjectManage> findProjectAll();
}