package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.CustLevel;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface CustLevelService extends Service<CustLevel> {
	List<Map<String,Object>> getCustLevel();

	List<CustLevel> findCustLevel(String parentId, int limit, int page);

	int findCustLevelCount(String parentId);
}
