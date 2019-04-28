package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.StockMonth;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockMonthService extends Service<StockMonth> {
	List<Map<String,Object>> findStockMonth(String clinic, String code, String starTime, String endTime);
}