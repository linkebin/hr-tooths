package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.StockWarn;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockWarnService extends Service<StockWarn> {
	List<Map<String,Object>> findStockWarn(String clinic,String code,String typeId,int limit,int page);

	List<Map<String,Object>> findStockWarnAll(String clinic,String code,String typeId);

	int findStockWarnCount(String clinic,String code,String typeId);
}
