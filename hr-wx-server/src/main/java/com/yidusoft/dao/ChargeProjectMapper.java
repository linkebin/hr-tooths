package com.yidusoft.dao;


import com.yidusoft.core.Mapper;
import com.yidusoft.domain.ChargeProject;

import java.util.List;

public interface ChargeProjectMapper extends Mapper<ChargeProject> {
	List<ChargeProject> findProjectAll();
}