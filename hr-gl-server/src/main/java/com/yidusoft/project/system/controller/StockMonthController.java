package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.StockMonthService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/stock/month")
public class StockMonthController {
    @Resource
    private StockMonthService stockMonthService;

    @PostMapping("/findStockMonth")
    public Result list(int page,int size,String code,String starTime,String endTime) {
        PageHelper.startPage(page, size);
        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockMonthService.findStockMonth("122",code,starTime,endTime);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @GetMapping("/exportStockMonth")
    public void exportStockMonth(HttpServletResponse response,String code,String starTime,String endTime) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("月度报表");
        List<String> titles = new ArrayList<>();
        titles.add("物品编号");
        titles.add("物品名称");
        titles.add("品牌");
        titles.add("类别");
        titles.add("计量单位");
        titles.add("期初库存_数量");
        titles.add("期初库存_单价");
        titles.add("期初库存_金额");
        titles.add("本月购用_数量");
        titles.add("本月购用_金额");
        titles.add("本月领用_数量");
        titles.add("本月领用_金额");
        titles.add("期末库存_数量");
        titles.add("期末库存_单价");
        titles.add("期末库存_金额");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockMonthService.findStockMonth("122",code,starTime,endTime);
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("product_code"));
            row.add(t.get("product_name"));
            row.add(t.get("product_brand"));
            row.add(t.get("product_type"));
            row.add(t.get("product_unit"));
            row.add(t.get("beginNum"));
            row.add(t.get("beginSprice"));
            row.add(t.get("beginSum"));
            row.add(t.get("num"));
            row.add(t.get("money"));
            row.add(t.get("useNum"));
            row.add(t.get("useMoney"));
            row.add(t.get("endNum"));
            row.add(t.get("endSprice"));
            row.add(t.get("endSum"));
            rows.add(row);
        });

        data.setRows(rows);

        String fileName="月度报表.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

}
