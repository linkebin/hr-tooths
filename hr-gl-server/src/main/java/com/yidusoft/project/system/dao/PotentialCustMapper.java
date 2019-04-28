package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.PotentialCust;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PotentialCustMapper extends Mapper<PotentialCust> {
	List<Map<String,Object>> findPotentialCust(@Param("source") String source, @Param("clinic") String clinic, @Param("starTime") String starTime, @Param("endTime") String endTime, @Param("customer") String customer, @Param("limit") int limit, @Param("page") int page);

	List<Map<String,Object>> findPotentialCustAll(@Param("source") String source, @Param("clinic") String clinic, @Param("starTime") String starTime, @Param("endTime") String endTime, @Param("customer") String customer);

	int findPotentialCustCount(@Param("source") String source, @Param("clinic") String clinic, @Param("starTime") String starTime, @Param("endTime") String endTime, @Param("customer") String customer);
}