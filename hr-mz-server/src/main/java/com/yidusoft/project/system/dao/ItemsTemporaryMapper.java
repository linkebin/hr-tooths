package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.ItemsTemporary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsTemporaryMapper extends Mapper<ItemsTemporary> {
	List<Map<String,Object>> getItemsTemp(@Param("orderId") String orderId,@Param("limit") int limit,@Param("page") int page);

	Integer getItemsTempCount(@Param("orderId") String orderId);

	void deleteCode(@Param("code")String code,@Param("orderId")String orderId);

	Map<String,Object> getItemsTempSum(@Param("orderId") String orderId);

	List<ItemsTemporary> findItemsTemp(@Param("orderId") String orderId);

	Integer getItemsNum(@Param("orderId") String orderId,@Param("code") String code);

	List<Map<String,Object>> findStockDetail(@Param("orderId") String orderId,@Param("limit") int limit,@Param("page") int page);

	Integer findStockDetailCount(@Param("orderId") String orderId);
}