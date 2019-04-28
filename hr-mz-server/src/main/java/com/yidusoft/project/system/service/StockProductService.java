package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.StockProduct;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface StockProductService extends Service<StockProduct> {
	List<Map<String,Object>> getStockProduct(String typeId,String code,int limit,int page);

	int getStockProductCount(String typeId,String code);

	StockProduct findStockProduct( String code);
}
