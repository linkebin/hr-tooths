package com.yidusoft.project.system.operation;

import com.yidusoft.project.system.domain.Stock;
import com.yidusoft.project.system.domain.StockMonth;
import com.yidusoft.project.system.service.StockMonthService;
import com.yidusoft.project.system.service.StockService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * User: libf
 * Time: 2018-12-07 10:34
 */
@Component
public class ScheduledHandler {

	@Resource
	private StockMonthService stockMonthService;

	@Resource
	private StockService stockService;

	//定时任务
	@Scheduled(cron = "0 0 1 1 * ?")
	public void scheduled(){
		List<Stock> stockList = stockService.findAll();
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		String month = date.get(Calendar.MONTH) + 1 <10 ? "0"+String.valueOf(date.get(Calendar.MONTH) + 1):String.valueOf(date.get(Calendar.MONTH) + 1);
		stockList.forEach(t->{
			StockMonth stockMonth = new StockMonth();
			stockMonth.setEndTime(t.getEndTime());
			stockMonth.setClinicId(t.getClinicId());
			stockMonth.setProductBrand(t.getProductBrand());
			stockMonth.setProductCode(t.getProductCode());
			stockMonth.setProductModel(t.getProductModel());
			stockMonth.setProductName(t.getProductName());
			stockMonth.setProductSpec(t.getProductSpec());
			stockMonth.setProductType(t.getProductType());
			stockMonth.setSprice(t.getSprice());
			stockMonth.setProductUnit(t.getProductUnit());
			stockMonth.setTypeId(t.getTypeId());
			stockMonth.setStockNum(t.getStockNum());
			stockMonth.setStockId(UUID.randomUUID().toString());
			stockMonth.setMoney(t.getMoney());
			stockMonth.setMonth(Integer.valueOf(year+month));
			stockMonthService.save(stockMonth);
		});
	}

//	@Scheduled(cron = "1 * * * * ?")
//	public void scheduled(){
//		List<Stock> stockList = stockService.findAll();
//		Calendar date = Calendar.getInstance();
//		String year = String.valueOf(date.get(Calendar.YEAR));
//		String month = date.get(Calendar.MONTH) + 1 <10 ? "0"+String.valueOf(date.get(Calendar.MONTH) + 1):String.valueOf(date.get(Calendar.MONTH) + 1);
//		stockList.forEach(t->{
//			StockMonth stockMonth = new StockMonth();
//			stockMonth.setClinicId(t.getClinicId());
//			stockMonth.setEndTime(t.getEndTime());
//			stockMonth.setProductBrand(t.getProductBrand());
//			stockMonth.setProductCode(t.getProductCode());
//			stockMonth.setProductModel(t.getProductModel());
//			stockMonth.setProductName(t.getProductName());
//			stockMonth.setProductSpec(t.getProductSpec());
//			stockMonth.setProductType(t.getProductType());
//			stockMonth.setSprice(t.getSprice());
//			stockMonth.setProductUnit(t.getProductUnit());
//			stockMonth.setTypeId(t.getTypeId());
//			stockMonth.setStockNum(t.getStockNum());
//			stockMonth.setStockId(UUID.randomUUID().toString());
//			stockMonth.setMoney(t.getMoney());
//			stockMonth.setMonth(Integer.valueOf(year+month));
//			stockMonthService.save(stockMonth);
//		});
//	}
}
