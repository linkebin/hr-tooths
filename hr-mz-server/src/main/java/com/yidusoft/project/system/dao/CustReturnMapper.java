package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.CustReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustReturnMapper extends Mapper<CustReturn> {
    List<Map<String,Object>> getReturnS(@Param("orgId") String orgId,  @Param("custId") String custId);
}