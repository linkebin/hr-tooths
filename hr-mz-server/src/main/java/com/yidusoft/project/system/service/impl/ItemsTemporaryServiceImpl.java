package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.ItemsTemporaryMapper;
import com.yidusoft.project.system.domain.ItemsTemporary;
import com.yidusoft.project.system.service.ItemsTemporaryService;
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
public class ItemsTemporaryServiceImpl  extends AbstractService<ItemsTemporary> implements ItemsTemporaryService {
    @Resource
    private ItemsTemporaryMapper itemsTemporaryMapper;

    @Override
    public List<Map<String, Object>> getItemsTemp(String orderId, int limit, int page) {
        return itemsTemporaryMapper.getItemsTemp(orderId,limit,page);
    }

    @Override
    public int getItemsTempCount(String orderId) {
        return itemsTemporaryMapper.getItemsTempCount(orderId);
    }

    @Override
    public void deleteCode(String code, String orderId) {
        itemsTemporaryMapper.deleteCode(code,orderId);
    }

    @Override
    public Map<String, Object> getItemsTempSum(String orderId) {
        return itemsTemporaryMapper.getItemsTempSum(orderId);
    }

    @Override
    public List<ItemsTemporary> findItemsTemp(String orderId) {
        return itemsTemporaryMapper.findItemsTemp(orderId);
    }

    @Override
    public int getItemsNum(String orderId, String code) {
        Integer num = itemsTemporaryMapper.getItemsNum(orderId,code);
        if(num != null){
           return num;
        }else{
            return 0;
        }
    }

    @Override
    public List<Map<String, Object>> findStockDetail(String orderId,int limit,int page) {
        return itemsTemporaryMapper.findStockDetail(orderId,limit,page);
    }

    @Override
    public int findStockDetailCount(String orderId) {
        Integer num = itemsTemporaryMapper.findStockDetailCount(orderId);
        if(num != null){
            return num;
        }else{
            return 0;
        }
    }
}
