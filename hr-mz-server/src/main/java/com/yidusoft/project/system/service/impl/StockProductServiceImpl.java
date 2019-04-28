package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.StockProductMapper;
import com.yidusoft.project.system.domain.StockProduct;
import com.yidusoft.project.system.service.StockProductService;
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
public class StockProductServiceImpl extends AbstractService<StockProduct> implements StockProductService {
    @Resource
    private StockProductMapper stockProductMapper;

    @Override
    public List<Map<String, Object>> getStockProduct(String typeId,String code,int limit,int page) {
        return stockProductMapper.getStockProduct(typeId,code,limit,page);
    }

    @Override
    public int getStockProductCount(String typeId,String code) {
        return stockProductMapper.getStockProductCount(typeId,code);
    }

    @Override
    public StockProduct findStockProduct(String code) {
        return stockProductMapper.findStockProduct(code);
    }
}
