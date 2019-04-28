package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.SecOrg;

import java.util.List;
import java.util.Map;

public interface SecOrgMapper extends Mapper<SecOrg> {
    List<Map<String,Object>> findAllByInfo(Map<String,Object> map);
}