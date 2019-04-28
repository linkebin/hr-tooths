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
    public List<Map<String,Object>> findInOrder(String clinic, String time, String type, String code, int limit, int page) {
        return stockInOutMapper.findInOrder(clinic,time,type,code,limit,page);
    }

    @Override
    public List<Map<String, Object>> findInOrderAll(String clinic, String time, String type, String code) {
        return stockInOutMapper.findInOrderAll(clinic,time,type,code);
    }

    @Override
    public int findInOrderCount(String clinic,String time,String type,String code) {
        Integer num = stockInOutMapper.findInOrderCount(clinic,time,type,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String,Object>> findOutOrder(String clinic,String time,String type,String code, int limit, int page) {
        return stockInOutMapper.findOutOrder(clinic,time,type,code,limit,page);
    }

    @Override
    public List<Map<String, Object>> findOutOrderAll(String clinic, String time, String type, String code) {
        return stockInOutMapper.findOutOrderAll(clinic,time,type,code);
    }

    @Override
    public int findOutOrderCount(String clinic,String time,String type,String code) {
        Integer num = stockInOutMapper.findOutOrderCount(clinic,time,type,code);
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
    public List<Map<String, Object>> findInternalOrderAll(String clinic, String code, String time, String type) {
        return stockInOutMapper.findInternalOrderAll(clinic,code,time,type);
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

}
