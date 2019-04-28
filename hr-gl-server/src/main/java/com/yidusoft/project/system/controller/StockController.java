package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.*;
import com.yidusoft.project.system.service.*;
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
@RequestMapping("/stock")
public class StockController {
    @Resource
    private StockService stockService;
    @Resource
    private DaoService daoService;

    @Resource
    private StockInOutDeService stockInOutDeService;

    @Resource
    private StockInOutService stockInOutService;

    @Resource
    private ItemsTemporaryService itemsTemporaryService;

    @PostMapping("/add")
    public Result add(String order) {
        StockInOut stockInOut = stockInOutService.findStockOrder("122",order);
        if(stockInOut.getState() == 1){
            return ResultGenerator.genFailResult("物品已入库，请勿重复操作！");
        }
        stockInOut.setState(1);
        stockInOutService.update(stockInOut);
        List<Map<String,Object>> list = stockInOutDeService.getStockSummary(order,"122");
        list.forEach(t->{
            Stock stock = stockService.findStock("122",String.valueOf(t.get("in_out_code")));
            StockProduct stockProduct = daoService.getObject("select * from stock_product where product_code = '"+t.get("in_out_code").toString()+"'",StockProduct.class);
            boolean is_new = false;
            if(stock == null){
                stock = new Stock();
                stock.setClinicId("122");
                stock.setEndTime(new Date());
                stock.setProductBrand(t.get("p_brand").toString());
                stock.setProductCode(t.get("in_out_code").toString());
                stock.setProductModel(t.get("p_model").toString());
                stock.setProductName(t.get("p_name").toString());
                stock.setProductSpec(t.get("p_spe").toString());
                stock.setProductType(t.get("p_type").toString());
                stock.setSprice(t.get("cost").toString());
                stock.setProductUnit(t.get("p_unit").toString());
                stock.setTypeId(t.get("type_id").toString());
                stock.setStockNum(0);
                stock.setStockId(UUID.randomUUID().toString());
                is_new = true;
            }
            stock.setRemark(stockProduct.getRemark());
            if("进货".equals(t.get("operation_type").toString()) || "退领".equals(t.get("operation_type").toString())){
                stock.setStockNum(stock.getStockNum()+Integer.valueOf(t.get("num").toString()));
                stock.setMoney(Double.valueOf(t.get("price").toString()));
            }else{
                stock.setStockNum(stock.getStockNum()-Integer.valueOf(t.get("num").toString()));
                stock.setMoney(Double.valueOf(t.get("price").toString()));
            }
            if(is_new){
                stockService.save(stock);
            }else{
                stockService.update(stock);
            }
        });
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/remove")
    public Result remove(String order) {
        StockInOut stockInOut = stockInOutService.findStockOrder("122",order);
        if(stockInOut.getState() == 1){
            return ResultGenerator.genFailResult("物品已出库，请勿重复操作！");
        }
        stockInOut.setState(1);
        stockInOutService.update(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Stock stock) {
        stockService.update(stock);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        Stock stock = stockService.findById(id);
        return ResultGenerator.genSuccessResult(stock);
    }

    @PostMapping("/findStock")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Stock> list = stockService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/confirm")
    public Result confirm(String id,String order) {
        List<StockInOutDe> list=daoService.getbyList("select * from stock_in_out_de where order_id='"+order+"'",StockInOutDe.class);
        SecUser secUser = Security.getUser();
        list.forEach(t->{
            StockInOutDe stockInOutDe = daoService.getObject("select * from stock_in_out_de where operation_type = '进货' and batch_id ='" + t.getBatchId() + "' and in_out_code = '" + t.getInOutCode() + "' and clinic_id='122'", StockInOutDe.class);
            stockInOutDe.setAllowance(stockInOutDe.getAllowance() + t.getNum());
            stockInOutDeService.update(stockInOutDe);
            Stock stock = stockService.findStock("122", t.getInOutCode());
            stock.setStockNum(stock.getStockNum() + t.getNum());
            stockService.update(stock);
        });
        StockInOut stockInOut = stockInOutService.findById(id);
        stockInOut.setState(1);
        stockInOutService.update(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/getStock")
    public Result getStock(String typeId, String name,String productBrand, int size, int page) {
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = stockService.getStock("122",typeId,name,productBrand,size,(page-1)*size);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/findUseStock")
    public Result findUseStock(int size, int page,String code,String time,String typeId){
        SecUser secUser = Security.getUser();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if("三个月内".equals(time)){
            c.setTime(new Date());
            c.add(Calendar.MONTH, -3);
            Date m3 = c.getTime();
            time = format.format(m3);
        }else if("半年内".equals(time)){
            c.setTime(new Date());
            c.add(Calendar.MONTH, -6);
            Date m3 = c.getTime();
            time = format.format(m3);
        }else if("一年内".equals(time)){
            c.setTime(new Date());
            c.add(Calendar.YEAR, -1);
            Date y = c.getTime();
            time = format.format(y);
        }
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list=stockService.findUseStock("122",code,time,typeId);
        PageInfo pageInfo = new PageInfo(list);
        return  ResultGenerator.genTableListResult(pageInfo);
    }

    @GetMapping("/exportInventory")
    public void excelInventory(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("库存物品汇总");
        List<String> titles = new ArrayList<>();
        titles.add("物品编号");
        titles.add("物品名称");
        titles.add("物品类型");
        titles.add("型号");
        titles.add("规格");
        titles.add("品牌");
        titles.add("单位");
        titles.add("库存总数量");
        titles.add("平均单价");
        titles.add("金额");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockService.getStockAll("122","","");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("product_code"));
            row.add(t.get("product_name"));
            row.add(t.get("product_type"));
            row.add(t.get("product_model"));
            row.add(t.get("product_spec"));
            row.add(t.get("product_brand"));
            row.add(t.get("product_unit"));
            row.add(t.get("stock_num"));
            row.add(t.get("sprice"));
            row.add(t.get("money"));
            rows.add(row);
        });

        data.setRows(rows);

        String fileName="库存物品汇总.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

    @PostMapping("/getOrgIdlist")
    public Result getOrgIdlist(String ids){
        List<Stock> list=daoService.getbyList("select s.* from sec_user u,stock s where s.clinic_id=u.ORG_ID and u.ID_='"+ids+"'",Stock.class);
        return  ResultGenerator.genSuccessResult(list);
    }
    @PostMapping("/getGqWarn")
    public Result getGqWarn(int page,int size){
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = daoService.getDivList("SELECT in_out_code,num,p_name,end_time, TO_DAYS(end_time)-TO_DAYS(NOW()) as days " +
                " FROM `stock_in_out_de` " +
                "where TO_DAYS(end_time)-TO_DAYS(NOW()) <=90 and clinic_id !='122'");
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }
}
