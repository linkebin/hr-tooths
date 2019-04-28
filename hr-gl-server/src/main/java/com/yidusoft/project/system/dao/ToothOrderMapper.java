package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.ToothOrder;

import java.util.List;
import java.util.Map;

public interface ToothOrderMapper extends Mapper<ToothOrder> {
    public List<Map<String,Object>> getselAll(Map<String,Object> map);
}