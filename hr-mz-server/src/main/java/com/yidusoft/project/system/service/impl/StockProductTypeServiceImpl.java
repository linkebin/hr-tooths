package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.StockProductTypeMapper;
import com.yidusoft.project.system.domain.StockProductType;
import com.yidusoft.project.system.service.StockProductTypeService;
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
public class StockProductTypeServiceImpl extends AbstractService<StockProductType> implements StockProductTypeService {
    @Resource
    private StockProductTypeMapper stockProductTypeMapper;

    @Override
    public List<Map<String, Object>> findProductType() {
        return stockProductTypeMapper.findProductType();
    }

    @Override
    public List<StockProductType> getProductType(String clinic, String parentId,int limit,int page) {
        return stockProductTypeMapper.getProductType(clinic,parentId,limit,page);
    }

    @Override
    public Integer getProductTypeCount(String clinic, String parentId) {
        return stockProductTypeMapper.getProductTypeCount(clinic,parentId);
    }


}
