package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.StockWarn;
import com.yidusoft.project.system.service.StockWarnService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/stock/warn")
public class StockWarnController {
    @Resource
    private StockWarnService stockWarnService;

    @PostMapping("/add")
    public Result add(String id, String code, String name, int num) {
        StockWarn stockWarn = new StockWarn();
        SecUser secUser = Security.getUser();
        if(id!=null && !"".equals(id)){
            stockWarn.setId(id);
			stockWarn.setClinicId("122");
			stockWarn.setWarnProId(code);
			stockWarn.setWarnName(name);
			stockWarn.setUserId(secUser.getId());
			stockWarn.setUserName(secUser.getUserName());
			stockWarn.setWarnNum(num);
			stockWarnService.update(stockWarn);
        }else{
            stockWarn.setId(UUID.randomUUID().toString());
            stockWarn.setClinicId("122");
            stockWarn.setWarnProId(code);
            stockWarn.setWarnName(name);
			stockWarn.setUserId(secUser.getId());
			stockWarn.setUserName(secUser.getUserName());
			stockWarn.setWarnNum(num);
			stockWarnService.save(stockWarn);
        }

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockWarnService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(StockWarn stockWarn) {
        stockWarnService.update(stockWarn);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        StockWarn stockWarn = stockWarnService.findById(id);
        return ResultGenerator.genSuccessResult(stockWarn);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<StockWarn> list = stockWarnService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findStockWarn")
    public Result findStockWarn(String code, String typeId, int page, int size) {
        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockWarnService.findStockWarn("122",code,typeId,size,(page-1)*size);
        int count = stockWarnService.findStockWarnCount("122",code,typeId);
        return ResultGenerator.genSuccessResult(list).setTotal(count);
    }

    @GetMapping("/exportStockWarn")
    public void excelStockWarn(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("库存预警汇总");
        List<String> titles = new ArrayList<>();
        titles.add("物品编号");
        titles.add("物品名称");
        titles.add("物品类型");
        titles.add("型号");
        titles.add("规格");
        titles.add("品牌");
        titles.add("库存总数量");
        titles.add("预警数量");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockWarnService.findStockWarnAll("122","","");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("product_code"));
            row.add(t.get("product_name"));
            row.add(t.get("product_type"));
            row.add(t.get("product_model"));
            row.add(t.get("product_spec"));
            row.add(t.get("product_brand"));
            row.add(t.get("stock_num"));
            row.add(t.get("warn_num"));
            rows.add(row);
        });

        data.setRows(rows);

        String fileName="库存预警汇总.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }
}
