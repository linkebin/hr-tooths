package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.CustSfjl;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustSfjlMapper extends Mapper<CustSfjl> {
	List<Map<String,Object>> findCustSfjl(@Param("clinic")String clinic,@Param("doctor")String doctor,@Param("starTime")String starTime,@Param("endTime")String endTime);

	Map<String,Object> findCustSfjlSum(@Param("clinic")String clinic,@Param("doctor")String doctor,@Param("starTime")String starTime,@Param("endTime")String endTime);

	Map<String,Object> findCustSfjlCostSum(@Param("clinic")String clinic,@Param("doctor")String doctor,@Param("starTime")String starTime,@Param("endTime")String endTime);

	List<Map<String,Object>> findDrugRevenue(@Param("clinic")String clinic,@Param("starTime")String starTime,@Param("endTime")String endTime);

	List<Map<String,Object>> findProjectRevenue(@Param("clinic")String clinic,@Param("starTime")String starTime,@Param("endTime")String endTime);

	Integer findDrugSum(@Param("clinic")String clinic,@Param("starTime")String starTime,@Param("endTime")String endTime);

	Integer findProjectSum(@Param("clinic")String clinic,@Param("starTime")String starTime,@Param("endTime")String endTime);

	Integer patients();
}