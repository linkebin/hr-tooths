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
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/stock/product")
public class StockProductController {
    @Resource
    private DaoService daoService;
    @Resource
    private StockProductService stockProductService;

    @PostMapping("/add")
    public Result add(String json) {
        StockProduct stockProduct = JSON.parseObject(json,StockProduct.class);
        SecUser secUser = Security.getUser();
        stockProduct.setProductId(UUID.randomUUID().toString());
        stockProduct.setProductCode(CodeHelper.getCode("M"));
        stockProduct.setUserId(secUser.getId());
        stockProduct.setUserName(secUser.getUserName());
        stockProduct.setAddTime(new Date());
        if(stockProduct.getSellPrice() == null){
            stockProduct.setSellPrice(0.0);
        }
        stockProductService.save(stockProduct);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockProductService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String json) {
        StockProduct stockProduct = JSON.parseObject(json,StockProduct.class);
        SecUser secUser = Security.getUser();
        stockProduct.setUserId(secUser.getId());
        stockProduct.setUserName(secUser.getUserName());
        stockProduct.setAddTime(new Date());
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
    public Result getStockProduct(String typeId, String code, int page, int size){
        List<Map<String,Object>> list = stockProductService.getStockProduct(typeId,code,size,(page-1)*size);
        int count = stockProductService.getStockProductCount(typeId,code);
        return ResultGenerator.genSuccessResult(list).setTotal(count);
    }

    @PostMapping("/uploadExcel")
    public Result uploadImg(HttpServletRequest request,@RequestParam("fileToUpload")MultipartFile file) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
        HSSFSheet sheet = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        List<Map<String,Object>> list = daoService.getDivList("select code id, name from stock_product_type");
        Map<String,Object> stockProductMap = new HashMap<>();
        for (Map<String,Object> map:list) {
            stockProductMap.put(map.get("name").toString(),map.get("id"));
        }
        int success = 0;
        int error = 0;
        List<StockProduct> productList = new ArrayList<>();
        for (int j = 0; j < physicalNumberOfRows; j++) {
            try {
                HSSFRow row = sheet.getRow(j);
                if (j == 0) {
                    continue;//标题行
                }else if(stockProductMap.get(row.getCell(1).getStringCellValue()) != null){
                    SecUser secUser = Security.getUser();
                    StockProduct stockProduct = new StockProduct();
                    stockProduct.setProductName(row.getCell(0).getStringCellValue());
                    stockProduct.setProductType(row.getCell(1).getStringCellValue());
                    if(row.getCell(2)!=null) {
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        stockProduct.setProductModel(row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "");
                    }
                    if(row.getCell(3)!=null){
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        stockProduct.setProductSpec( row.getCell(3).getStringCellValue());
                    }else {
                        stockProduct.setProductSpec("");
                    }
                    if(row.getCell(4)!=null) {
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        stockProduct.setProductBrand(row.getCell(4) != null ? row.getCell(4).getStringCellValue() : "");
                    }
                    if(row.getCell(5)!=null) {
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        stockProduct.setProductUnit(row.getCell(5) != null ? row.getCell(5).getStringCellValue() : "");
                    }
                    stockProduct.setInStockPrice(row.getCell(6)!=null? row.getCell(6).getNumericCellValue():0);
                    stockProduct.setSellPrice(row.getCell(7)!=null? row.getCell(7).getNumericCellValue():0);
                    stockProduct.setTypeId(stockProductMap.get(row.getCell(1).getStringCellValue()).toString());
                    stockProduct.setProductId(UUID.randomUUID().toString());
                    stockProduct.setProductCode(CodeHelper.getCode("M"));
                    stockProduct.setUserId(secUser.getId());
                    stockProduct.setUserName(secUser.getUserName());
                    stockProduct.setAddTime(new Date());
                    if(stockProduct.getSellPrice() == null){
                        stockProduct.setSellPrice(0.0);
                    }
                    if(stockProduct.getInStockPrice() == null){
                        stockProduct.setSellPrice(0.0);
                    }
                    productList.add(stockProduct);
                    success++;
                }else{
                    return ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(0).getStringCellValue()+"》物品在系统中找不到相应的类型");
                }
            }catch (Exception e){
                e.printStackTrace();
                return ResultGenerator.genFailResult("第"+(j+1)+"行物品解析失败，请检查格式！");
            }
        }
        productList.forEach(t->{
            stockProductService.save(t);
        });
        return ResultGenerator.genSuccessResult("上传成功！总数量："+(success)+"；");
    }

    @GetMapping("/exportStockProduct")
    public void excelInventory(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("模板");
        List<String> titles = new ArrayList<>();
        titles.add("物品名称");
        titles.add("物品类型");
        titles.add("型号");
        titles.add("规格");
        titles.add("品牌");
        titles.add("单位");
        titles.add("采购单价");
        titles.add("建议售价");
        data.setTitles(titles);
        List<List<Object>> rows = new ArrayList<>();
        List<Object> row = new ArrayList<>();
        row.add("测试数据1");
        row.add("药品类");
        row.add("CD100086");
        row.add("500ml");
        row.add("XXX品牌");
        row.add("瓶");
        row.add(1);
        row.add(5);
        rows.add(row);
        data.setRows(rows);
        String fileName="产品库物品导入模板.xls";
        ExcelUtils.exportExcel(response,fileName,data);
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
        titles.add("生产批号");
        titles.add("生产日期(2019/4/1)");
        titles.add("保质期");
        titles.add("供应商");
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
        cellType.add("文本");
        cellType.add("文本");
        cellType.add("文本");
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
