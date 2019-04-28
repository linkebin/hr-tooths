package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.AccountRecord;

import java.util.List;
import java.util.Map;

public interface AccountRecordMapper extends Mapper<AccountRecord> {
    List<Map<String,Object>> getList(Map<String, Object> map);
}