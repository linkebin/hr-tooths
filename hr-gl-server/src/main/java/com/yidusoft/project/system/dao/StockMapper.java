package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockMapper extends Mapper<Stock> {
	Stock findStock(@Param("clinic") String clinic, @Param("code") String code);

	List<Map<String,Object>> getStock(@Param("clinic") String clinic, @Param("typeId") String typeId, @Param("name") String name,@Param("productBrand") String productBrand, @Param("limit") int limit, @Param("page") int page);

	List<Map<String,Object>> getStockAll(@Param("clinic") String clinic, @Param("typeId") String typeId, @Param("name") String name);

	Integer getStockCount(@Param("clinic") String clinic, @Param("typeId") String typeId, @Param("name") String name);

	Integer findStockNum(@Param("clinic") String clinic, @Param("code") String code);

	List<Map<String,Object>> findUseStock(@Param("clinic") String clinic,@Param("code") String code,@Param("time") String time,@Param("typeId") String typeId );
}