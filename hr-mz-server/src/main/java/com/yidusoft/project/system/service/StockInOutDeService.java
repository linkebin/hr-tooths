package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.StockInOutDe;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockInOutDeService extends Service<StockInOutDe> {
	List<StockInOutDe> findStockInOutDe(String clinic,int limit,int page);

	List<Map<String,Object>> findStockItems(String clinic, String typeId,String code, int limit, int page);

	int findStockItemsCount(String clinic, String typeId,String code);

	List<Map<String,Object>> findUndoStock(String clinic, String typeId,String code, int limit, int page);

	int findUndoStockCount(String clinic, String typeId,String code);

	int findUndoStockNum(String code,String batchId,String clinic);

	List<StockInOutDe> findUndoStockList(String code,String batchId,String clinic);

	StockInOutDe findStockInfo(String code,String batchId,String orderId);

	List<StockInOutDe> findStockDetail(String orderId,String operationType, int limit, int page);

	int findStockDetailCount(String orderId,String operationType);

	List<Map<String,Object>> findInternalOrder (String clinic,String code,String time, int limit, int page);

	int findInternalOrderCount(String clinic,String code,String time);

	List<Map<String,Object>> getStockSummary(String orderId,String clinicId);

	List<StockInOutDe> getProductStock(String clinic,String code);

	List<StockInOutDe> getProductStockDesc(String clinic,String code);

	int getProductStockAll(String clinic, String code);

	List<Map<String,Object>> findReturnStock(String clinic,String typeId,String code,int limit,int page);

	Integer findReturnStockCount(String clinic,String typeId,String code);

	Integer findReturnStockNum (String clinic,String orderId, String code);

	StockInOutDe getReturnStock (String clinic, String orderId,String code);
}
