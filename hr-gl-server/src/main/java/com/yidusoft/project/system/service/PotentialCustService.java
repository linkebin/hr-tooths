package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.PotentialCust;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface PotentialCustService extends Service<PotentialCust> {
	List<Map<String,Object>> findPotentialCust(String source, String clinic, String starTime, String endTime, String customer, int limit, int page);

	List<Map<String,Object>> findPotentialCustAll(String source, String clinic, String starTime, String endTime, String customer);

	int findPotentialCustCount(String source, String clinic, String starTime, String endTime, String customer);
}
