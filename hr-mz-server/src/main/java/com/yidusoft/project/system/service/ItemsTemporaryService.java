package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.ItemsTemporary;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface ItemsTemporaryService extends Service<ItemsTemporary> {
	List<Map<String,Object>> getItemsTemp(String orderId,int limit,int page);

	int getItemsTempCount(String orderId);

	void deleteCode(String code,String orderId);

	Map<String,Object> getItemsTempSum(String orderId);

	List<ItemsTemporary> findItemsTemp(String orderId);

	int getItemsNum(String orderId, String code);

	List<Map<String,Object>> findStockDetail(String orderId,int limit,int page);

	int findStockDetailCount(String orderId);

}
