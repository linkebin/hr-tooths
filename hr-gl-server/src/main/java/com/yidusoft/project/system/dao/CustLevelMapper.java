package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.CustLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustLevelMapper extends Mapper<CustLevel> {
	List<Map<String,Object>> getCustLevel();

	List<CustLevel> findCustLevel(@Param("parentId") String parentId, @Param("limit") int limit, @Param("page") int page);

	int findCustLevelCount(@Param("parentId") String parentId);
}