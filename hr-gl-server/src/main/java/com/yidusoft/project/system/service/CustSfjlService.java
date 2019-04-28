package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.CustSfjl;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface CustSfjlService extends Service<CustSfjl> {
	List<Map<String,Object>> findCustSfjl(String clinic,String doctor,String starTime,String endTime);

	Map<String,Object> findCustSfjlSum(String clinic,String doctor,String starTime,String endTime);

	Map<String,Object> findCustSfjlCostSum(String clinic,String doctor,String starTime,String endTime);

	List<Map<String,Object>> findDrugRevenue(String clinic,String starTime,String endTime);

	List<Map<String,Object>> findProjectRevenue(String clinic,String starTime,String endTime);

	int findDrugRevenueSum(String clinic,String starTime,String endTime);

	int findProjectRevenueSum(String clinic,String starTime,String endTime);

	int patients();
}
