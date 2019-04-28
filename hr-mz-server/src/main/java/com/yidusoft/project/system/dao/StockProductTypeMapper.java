package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.StockProductType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockProductTypeMapper extends Mapper<StockProductType> {
	List<Map<String,Object>> findProductType();

	List<StockProductType> getProductType(@Param("clinic") String clinic,@Param("parentId") String parentId, @Param("limit") int limit,@Param("page") int page);

	int getProductTypeCount(@Param("clinic") String clinic,@Param("parentId") String parentId);
}