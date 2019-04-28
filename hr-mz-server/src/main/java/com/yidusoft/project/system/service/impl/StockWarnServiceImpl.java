package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.StockWarnMapper;
import com.yidusoft.project.system.domain.StockWarn;
import com.yidusoft.project.system.service.StockWarnService;
import com.yidusoft.core.AbstractService;
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
public class StockWarnServiceImpl extends AbstractService<StockWarn> implements StockWarnService {
    @Resource
    private StockWarnMapper stockWarnMapper;

    @Override
    public List<Map<String, Object>> findStockWarn(String clinic, String code,String typeId,int limit,int page) {
        return stockWarnMapper.findStockWarn(clinic,code,typeId,limit,page);
    }

    @Override
    public List<Map<String, Object>> findStockWarnAll(String clinic, String code, String typeId) {
        return stockWarnMapper.findStockWarnAll(clinic,code,typeId);
    }

    @Override
    public int findStockWarnCount(String clinic, String code,String typeId) {
        Integer num = stockWarnMapper.findStockWarnCount(clinic,code,typeId);

        if(num == null){
            return 0;
        }else{
            return num;
        }
    }
}
