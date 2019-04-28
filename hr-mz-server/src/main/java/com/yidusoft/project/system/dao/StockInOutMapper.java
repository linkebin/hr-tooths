package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.StockInOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockInOutMapper extends Mapper<StockInOut> {
	List<Map<String,Object>> findInOrder (@Param("clinic")String clinic, @Param("time")String time, @Param("type")String type, @Param("code")String code, @Param("limit")int limit, @Param("page")int page);

	List<Map<String,Object>> findInOrderAll (@Param("clinic")String clinic,@Param("time")String time,@Param("type")String type,@Param("code")String code);

	Integer findInOrderCount(@Param("clinic")String clinic,@Param("time")String time,@Param("type")String type,@Param("code")String code);

	List<Map<String,Object>> findOutOrder (@Param("clinic")String clinic,@Param("time")String time,@Param("type")String type,@Param("code")String code, @Param("limit")int limit, @Param("page")int page);

	List<Map<String,Object>> findOutOrderAll (@Param("clinic")String clinic,@Param("time")String time,@Param("type")String type,@Param("code")String code);

	Integer findOutOrderCount(@Param("clinic")String clinic,@Param("time")String time,@Param("type")String type,@Param("code")String code);

	StockInOut findStockOrder(@Param("clinic")String clinic,@Param("code") String code);

	List<Map<String,Object>> findInternalOrder(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type,@Param("limit")int limit, @Param("page")int page);

	List<Map<String,Object>> findInternalOrderAll(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type);

	Integer findInternalOrderCount(@Param("clinic")String clinic,@Param("code")String code,@Param("time") String time,@Param("type") String type);
}