package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.Stock;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockService extends Service<Stock> {
	Stock findStock(String clinic, String code);

	List<Map<String,Object>> getStock(String clinic, String typeId,String name,String productBrand, int limit, int page);

	List<Map<String,Object>> getStockAll(String clinic, String typeId, String name);

	int getStockCount(String clinic,String typeId,String name);

	int findStockNum(String clinic,String code);

	List<Stock> findStockList(String typeId,String name,int limit,int page);

	Integer findStockListCount(String typeId,String name);

	List<Map<String,Object>> findUseStock(String clinic,String code,String time,String typeId);
}