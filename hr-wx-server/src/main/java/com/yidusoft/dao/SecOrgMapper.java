package com.yidusoft.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.domain.SecOrg;

import java.util.List;
import java.util.Map;

public interface SecOrgMapper extends Mapper<SecOrg> {
    List<Map<String,Object>> findAllByInfo(Map<String, Object> map);

    List<SecOrg> getSecOrg();
}