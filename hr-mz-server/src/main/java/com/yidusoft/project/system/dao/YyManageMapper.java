package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.YyManage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface YyManageMapper extends Mapper<YyManage> {
	List<YyManage> getYYManage(@Param("clinic") String clinic,@Param("doctor") String doctor,@Param("code") String code,@Param("project") String project,@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("limit")int limit,@Param("page") int page);

	Integer getYYManageCount(@Param("clinic") String clinic,@Param("doctor") String doctor,@Param("code") String code,@Param("project") String project,@Param("startTime") String startTime,@Param("endTime") String endTime);

	List<YyManage> getList(Map<String, Object> map);
}