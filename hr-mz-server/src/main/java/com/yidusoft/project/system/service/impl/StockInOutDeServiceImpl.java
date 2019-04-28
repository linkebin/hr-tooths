package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.StockInOutDeMapper;
import com.yidusoft.project.system.domain.StockInOutDe;
import com.yidusoft.project.system.service.StockInOutDeService;
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
public class StockInOutDeServiceImpl extends AbstractService<StockInOutDe> implements StockInOutDeService {
    @Resource
    private StockInOutDeMapper stockInOutDeMapper;

    @Override
    public List<StockInOutDe> findStockInOutDe(String clinic, int limit, int page) {
        return stockInOutDeMapper.findStockInOutDe(clinic,limit,page);
    }

    @Override
    public List<Map<String, Object>> findStockItems(String clinic, String typeId,String code, int limit, int page) {
        return stockInOutDeMapper.findStockItems(clinic,typeId,code,limit,page);
    }

    @Override
    public int findStockItemsCount(String clinic, String typeId,String code) {
        Integer num = stockInOutDeMapper.findStockItemsCount(clinic,typeId,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> findUndoStock(String clinic, String typeId,String code, int limit, int page) {
        return stockInOutDeMapper.findUndoStock(clinic,typeId,code,limit,page);
    }

    @Override
    public int findUndoStockCount(String clinic, String typeId,String code) {
        Integer num = stockInOutDeMapper.findUndoStockCount(clinic,typeId,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public int findUndoStockNum(String code,String batchId, String clinic) {
        Integer num = stockInOutDeMapper.findUndoStockNum(code,batchId,clinic);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<StockInOutDe> findUndoStockList(String code, String batchId, String clinic) {
        return stockInOutDeMapper.findUndoStockList(code,batchId,clinic);
    }

    @Override
    public StockInOutDe findStockInfo(String code, String batchId, String orderId) {
        return stockInOutDeMapper.findStockInfo(code,batchId,orderId);
    }

    @Override
    public List<StockInOutDe> findStockDetail(String orderId, String operationType,int limit,int page) {
        return stockInOutDeMapper.findStockDetail(orderId,operationType,limit,page);
    }

    @Override
    public int findStockDetailCount(String orderId, String operationType) {
        return stockInOutDeMapper.findStockDetailCount(orderId,operationType);
    }

    @Override
    public List<Map<String, Object>> findInternalOrder(String clinic,String code,String time, int limit, int page) {
        return stockInOutDeMapper.findInternalOrder(clinic,code,time,limit,page);
    }

    @Override
    public int findInternalOrderCount(String clinic,String code,String time) {
        Integer num = stockInOutDeMapper.findInternalOrderCount(clinic,code,time);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> getStockSummary(String orderId,String clinicId) {
        return stockInOutDeMapper.getStockSummary(orderId,clinicId);
    }

    @Override
    public List<StockInOutDe> getProductStock(String clinic, String code) {
        return stockInOutDeMapper.getProductStock(clinic,code);
    }

    @Override
    public List<StockInOutDe> getProductStockDesc(String clinic, String code) {
        return stockInOutDeMapper.getProductStockDesc(clinic,code);
    }

    @Override
    public int getProductStockAll(String clinic, String code) {
        Integer num = stockInOutDeMapper.getProductStockAll(clinic,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> findReturnStock(String clinic, String typeId, String code, int limit, int page) {
        return stockInOutDeMapper.findReturnStock(clinic,typeId,code,limit,page);
    }

    @Override
    public Integer findReturnStockCount(String clinic, String typeId, String code) {
        Integer num = stockInOutDeMapper.findReturnStockCount(clinic,typeId,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public Integer findReturnStockNum(String clinic, String orderId, String code) {
        Integer num = stockInOutDeMapper.findReturnStockNum(clinic,orderId,code);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }

    @Override
    public StockInOutDe getReturnStock(String clinic, String orderId, String code) {
        return stockInOutDeMapper.getReturnStock(clinic,orderId,code);
    }

}
