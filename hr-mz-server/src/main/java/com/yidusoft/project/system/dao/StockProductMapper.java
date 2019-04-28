package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.StockProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockProductMapper extends Mapper<StockProduct> {
	List<Map<String,Object>> getStockProduct(@Param("typeId") String typeId,@Param("code")String code,@Param("limit") int limit,@Param("page") int page);

	int getStockProductCount(@Param("typeId")String typeId,@Param("code")String code);

	StockProduct findStockProduct(@Param("code") String code);
}