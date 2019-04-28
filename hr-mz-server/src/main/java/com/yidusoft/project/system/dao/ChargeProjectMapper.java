package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.ChargeProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChargeProjectMapper extends Mapper<ChargeProject> {
	List<ChargeProject> list(@Param("orgId")String orgId);
}