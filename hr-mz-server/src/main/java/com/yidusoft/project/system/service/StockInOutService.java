package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.StockInOut;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockInOutService extends Service<StockInOut> {
	List<Map<String,Object>> findInOrder(String clinic, String time, String type, String code, int limit, int page);

	List<Map<String,Object>> findInOrderAll(String clinic,String time,String type,String code);

	int findInOrderCount(String clinic,String time,String type,String code);

	List<Map<String,Object>> findOutOrder(String clinic,String time,String type,String code, int limit, int page);

	List<Map<String,Object>> findOutOrderAll(String clinic,String time,String type,String code);

	int findOutOrderCount(String clinic,String time,String type,String code);

	StockInOut findStockOrder(String clinic,String code);

	List<Map<String,Object>> findInternalOrder(String clinic,String code,String time,String type,int limit,int page);

	List<Map<String,Object>> findInternalOrderAll(String clinic,String code,String time,String type);

	int findInternalOrderCount(String clinic,String code,String time,String type);
}
