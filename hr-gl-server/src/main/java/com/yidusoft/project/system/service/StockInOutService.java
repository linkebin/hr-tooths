package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.StockInOut;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockInOutService extends Service<StockInOut> {
	List<Map<String,Object>> findOrder(String clinic, String time, String type, String code, int limit, int page);

	List<Map<String,Object>> findOrderAll(String clinic, String time, String type, String code);

	int findOrderCount(String clinic, String time, String type, String code);

	StockInOut findStockOrder(String clinic,String code);

	List<Map<String,Object>> findInternalOrder(String clinic,String code,String time,String type,int limit,int page);

	int findInternalOrderCount(String clinic,String code,String time,String type);

	List<Map<String,Object>> findApplyOrder(String clinic,String code,String time,String type,int limit,int page);

	List<Map<String,Object>> findInternalOrderAll(String clinic,String code,String time,String type);

	List<Map<String,Object>> findApplyOrderAll(String clinic,String code,String time,String type);

	int findApplyOrderCount(String clinic,String code,String time,String type);

	List<Map<String,Object>> findToDoOrder();
}
