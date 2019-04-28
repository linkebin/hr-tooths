package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.StockInOutMapper;
import com.yidusoft.project.system.domain.StockInOut;
import com.yidusoft.project.system.service.StockInOutService;
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
public class StockInOutServiceImpl extends AbstractService<StockInOut> implements StockInOutService {
    @Resource
    private StockInOutMapper stockInOutMapper;

    @Override
    public List<Map<String,Object>> findOrder(String clinic, String time, String type, String code, int limit, int page) {
        return stockInOutMapper.findOrder(clinic,time,type,code,limit,page);
    }

    @Override
    public List<Map<String, Object>> findOrderAll(String clinic, String time, String type, String code) {
        return stockInOutMapper.findOrderAll(clinic,time,type,code);
    }

    @Override
    public int findOrderCount(String clinic,String time,String type,String code) {
        Integer num = stockInOutMapper.findOrderCount(clinic,time,type,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public StockInOut findStockOrder(String clinic, String code) {
        return stockInOutMapper.findStockOrder(clinic,code);
    }

    @Override
    public List<Map<String, Object>> findInternalOrder(String clinic, String code, String time, String type, int limit, int page) {
        return stockInOutMapper.findInternalOrder(clinic,code,time,type,limit,page);
    }

    @Override
    public int findInternalOrderCount(String clinic, String code, String time, String type) {
        Integer num = stockInOutMapper.findInternalOrderCount(clinic,code,time,type);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> findApplyOrder(String clinic, String code, String time, String type, int limit, int page) {
        return stockInOutMapper.findApplyOrder(clinic,code,time,type,limit,page);
    }

    @Override
    public List<Map<String, Object>> findInternalOrderAll(String clinic, String code, String time, String type) {
        return stockInOutMapper.findInternalOrderAll(clinic,code,time,type);
    }

    @Override
    public List<Map<String, Object>> findApplyOrderAll(String clinic, String code, String time, String type) {
        return stockInOutMapper.findApplyOrderAll(clinic,code,time,type);
    }

    @Override
    public int findApplyOrderCount(String clinic, String code, String time, String type) {
        Integer num = stockInOutMapper.findApplyOrderCount(clinic,code,time,type);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> findToDoOrder() {
        return stockInOutMapper.findToDoOrder();
    }
}
