package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.StockInOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockInOutMapper extends Mapper<StockInOut> {
	List<Map<String,Object>> findOrder(@Param("clinic") String clinic, @Param("time") String time, @Param("type") String type, @Param("code") String code, @Param("limit") int limit, @Param("page") int page);

	List<Map<String,Object>> findOrderAll(@Param("clinic") String clinic, @Param("time") String time, @Param("type") String type, @Param("code") String code);

	Integer findOrderCount(@Param("clinic") String clinic, @Param("time") String time, @Param("type") String type, @Param("code") String code);

	StockInOut findStockOrder(@Param("clinic")String clinic,@Param("code") String code);


	List<Map<String,Object>> findInternalOrder(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type,@Param("limit")int limit, @Param("page")int page);

	List<Map<String,Object>> findInternalOrderAll(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type);

	Integer findInternalOrderCount(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type);

	List<Map<String,Object>> findApplyOrder(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type,@Param("limit")int limit, @Param("page")int page);

	List<Map<String,Object>> findApplyOrderAll(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type);

	Integer findApplyOrderCount(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type);

	List<Map<String,Object>> findToDoOrder();
}