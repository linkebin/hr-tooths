package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.StockMapper;
import com.yidusoft.project.system.domain.Stock;
import com.yidusoft.project.system.service.StockService;
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
public class StockServiceImpl extends AbstractService<Stock> implements StockService {
    @Resource
    private StockMapper stockMapper;

    @Override
    public Stock findStock(String clinic, String code) {
        return stockMapper.findStock(clinic,code);
    }

    @Override
    public List<Map<String,Object>> getStock(String clinic, String typeId,String name,String productBrand, int limit, int page) {
        return stockMapper.getStock(clinic,typeId,name,productBrand,limit,page);
    }

    @Override
    public List<Map<String, Object>> getStockAll(String clinic, String typeId, String name) {
        return stockMapper.getStockAll(clinic,typeId,name);
    }

    @Override
    public int getStockCount(String clinic, String typeId,String name) {
        Integer num = stockMapper.getStockCount(clinic,typeId,name);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }
    @Override
    public int findStockNum(String clinic, String code) {
        Integer num = stockMapper.findStockNum(clinic,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Stock> findStockList(String typeId, String name, int limit, int page) {
        return stockMapper.findStockList(typeId,name,limit,page);
    }

    @Override
    public Integer findStockListCount(String typeId, String name) {
        Integer num = stockMapper.findStockListCount(typeId,name);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> findUseStock(String clinic, String code, String time,String typeId) {
        return stockMapper.findUseStock(clinic,code,time,typeId);
    }
}
