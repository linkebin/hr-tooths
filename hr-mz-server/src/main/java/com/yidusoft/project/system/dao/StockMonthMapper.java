package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.StockMonth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockMonthMapper extends Mapper<StockMonth> {
	List<Map<String,Object>> findStockMonth(@Param("clinic") String clinic, @Param("code") String code, @Param("starTime") String starTime, @Param("endTime") String endTime);
}