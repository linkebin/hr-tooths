package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.StockMonthMapper;
import com.yidusoft.project.system.domain.StockMonth;
import com.yidusoft.project.system.service.StockMonthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class StockMonthServiceImpl extends AbstractService<StockMonth> implements StockMonthService {
    @Resource
    private StockMonthMapper stockMonthMapper;


    @Override
    public List<Map<String, Object>> findStockMonth(String clinic, String code, String starTime, String endTime) {
        return stockMonthMapper.findStockMonth(clinic, code, starTime, endTime);
    }
}

