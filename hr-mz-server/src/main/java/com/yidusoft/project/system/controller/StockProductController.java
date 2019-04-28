package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.StockProduct;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.StockProductService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/stock/product")
public class StockProductController {
    @Resource
    private StockProductService stockProductService;

    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String json) {
        StockProduct stockProduct = JSON.parseObject(json,StockProduct.class);
        SecUser secUser = Security.getUser();
        stockProduct.setProductId(UUID.randomUUID().toString());
        stockProduct.setUserId(secUser.getId());
        stockProduct.setUserName(secUser.getUserName());
        stockProduct.setAddTime(new Date());
        stockProductService.save(stockProduct);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockProductService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(StockProduct stockProduct) {
        stockProductService.update(stockProduct);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        StockProduct stockProduct = stockProductService.findById(id);
        return ResultGenerator.genSuccessResult(stockProduct);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<StockProduct> list = stockProductService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getStockProduct")
    public Result getStockProduct(String typeId,String code,int page,int size){
        List<Map<String,Object>> list = stockProductService.getStockProduct(typeId,code,size,(page-1)*size);
        int count = stockProductService.getStockProductCount(typeId,code);
        return ResultGenerator.genSuccessResult(list).setTotal(count);
    }

    @GetMapping("/exportInStockManage")
    public void exportInStockManage(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("入库导入模板");
        List<String> titles = new ArrayList<>();
        titles.add("物品编码");
        titles.add("物品名称");
        titles.add("类型编号");
        titles.add("物品类型");
        titles.add("型号");
        titles.add("规格");
        titles.add("品牌");
        titles.add("单位");
        titles.add("单价（可修改）");
        titles.add("零售价（可修改）");
        titles.add("有效期（必填）");
        titles.add("入库数量（必填）");
        titles.add("备注（选填）");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList<>();
        List<StockProduct> stockProductList = daoService.getbyList("select * from stock_product",StockProduct.class);
        String time = getBeforeDate(548);
        stockProductList.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.getProductCode());
            row.add(t.getProductName());
            row.add(t.getTypeId());
            row.add(t.getProductType());
            row.add(t.getProductModel());
            row.add(t.getProductSpec());
            row.add(t.getProductBrand());
            row.add(t.getProductUnit());
            row.add(t.getInStockPrice());
            row.add(t.getSellPrice());
            row.add(time);
            rows.add(row);
        });
        data.setRows(rows);
        List<String> cellType =new ArrayList<>();
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("双精度浮点数");
        cellType.add("双精度浮点数");
        cellType.add("日期");
        data.setRows(rows);
        data.setCellType(cellType);
        String fileName="入库导入模板.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

    private String getBeforeDate(Integer n){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,n);
        Date time = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(time);
    }
}
