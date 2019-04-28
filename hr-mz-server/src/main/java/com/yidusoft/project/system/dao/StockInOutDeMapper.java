package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.StockInOutDe;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockInOutDeMapper extends Mapper<StockInOutDe> {
	List<StockInOutDe> findStockInOutDe(@Param("clinic")String clinic,@Param("limit")int limit,@Param("page")int page);

	List<Map<String,Object>> findStockItems(@Param("clinic") String clinic,@Param("typeId") String typeId,@Param("code") String code,@Param("limit")int limit, @Param("page")int page);

	Integer findStockItemsCount(@Param("clinic") String clinic,@Param("typeId") String typeId,@Param("code") String code);

	List<Map<String,Object>> findUndoStock(@Param("clinic") String clinic,@Param("typeId") String typeId,@Param("code") String code,@Param("limit")int limit, @Param("page")int page);

	Integer findUndoStockCount(@Param("clinic") String clinic,@Param("typeId") String typeId,@Param("code") String code);

	Integer findUndoStockNum(@Param("code") String code,@Param("batchId") String batchId,@Param("clinic") String clinic);

	List<StockInOutDe> findUndoStockList(@Param("code") String code,@Param("batchId") String batchId,@Param("clinic") String clinic);

	StockInOutDe findStockInfo(@Param("code") String code,@Param("batchId") String batchId,@Param("orderId") String orderId);

	List<StockInOutDe> findStockDetail(@Param("orderId") String orderId,@Param("operationType") String operationType,@Param("limit")int limit, @Param("page")int page);

	int findStockDetailCount(@Param("orderId") String orderId,@Param("operationType") String operationType);

	List<Map<String,Object>> findInternalOrder (@Param("clinic")String clinic,@Param("code")String code,@Param("time")String time, @Param("limit")int limit, @Param("page")int page);

	Integer findInternalOrderCount(@Param("clinic")String clinic,@Param("code")String code,@Param("time")String time);

	List<Map<String,Object>> getStockSummary (@Param("orderId")String orderId,@Param("clinicId")String clinicId);

	List<StockInOutDe> getProductStock(@Param("clinic")String clinic,@Param("code")String code);

	List<StockInOutDe> getProductStockDesc(@Param("clinic")String clinic,@Param("code")String code);

	Integer getProductStockAll(@Param("clinic")String clinic,@Param("code")String code);

	List<Map<String,Object>> findReturnStock(@Param("clinic") String clinic, @Param("typeId") String typeId,@Param("code") String code, @Param("limit") int limit, @Param("page") int page);

	Integer findReturnStockCount(@Param("clinic") String clinic, @Param("typeId") String typeId,@Param("code") String code);

	Integer findReturnStockNum (@Param("clinic") String clinic,@Param("orderId") String orderId,@Param("code") String code);

	StockInOutDe getReturnStock (@Param("clinic") String clinic,@Param("orderId") String orderId,@Param("code") String code);
}