package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.StockProductType;
import com.yidusoft.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockProductTypeService extends Service<StockProductType> {

	List<Map<String,Object>> findProductType();

	List<StockProductType> getProductType(String clinic, String parentId,int limit,int page);

	Integer getProductTypeCount(String clinic,String parentId);

}
