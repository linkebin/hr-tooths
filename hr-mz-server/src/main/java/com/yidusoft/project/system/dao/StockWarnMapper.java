package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.StockWarn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockWarnMapper extends Mapper<StockWarn> {
	List<Map<String,Object>> findStockWarn(@Param("clinic")String clinic, @Param("code") String code, @Param("typeId")String typeId, @Param("limit") int limit, @Param("page") int page);

	List<Map<String,Object>> findStockWarnAll(@Param("clinic")String clinic, @Param("code") String code, @Param("typeId")String typeId);

	Integer findStockWarnCount(@Param("clinic")String clinic,@Param("code") String code,@Param("typeId")String typeId);
}