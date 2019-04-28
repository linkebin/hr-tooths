package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.*;
import com.yidusoft.project.system.service.ItemsTemporaryService;
import com.yidusoft.project.system.service.StockInOutDeService;
import com.yidusoft.project.system.service.StockInOutService;
import com.yidusoft.project.system.service.StockService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/stock/in/out")
public class StockInOutController {
    @Resource
    private StockInOutService stockInOutService;

    @Resource
    private ItemsTemporaryService itemsTemporaryService;

    @Resource
    private StockInOutDeService stockInOutDeService;

    @Resource
    private StockService stockService;

    @PostMapping("/add")
    public Result add(String order,String type) {
        SecUser secUser = Security.getUser();
        StockInOut stockInOut = new StockInOut();
        stockInOut.setInOutId(UUID.randomUUID().toString());
        stockInOut.setCreator(secUser.getUserName());
        if("进货".equals(type)){
            stockInOut.setInObj(secUser.getOrgId());
            stockInOut.setOutObj("总部");
        }else if("领用".equals(type)){
            stockInOut.setInObj(secUser.getUserName());
            stockInOut.setOutObj(secUser.getOrgId());
        }
        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(order);
        stockInOut.setInOutCode(order);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType(type);
        stockInOut.setClinicId(secUser.getOrgId());
        stockInOut.setState(0);
        stockInOutService.save(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/remove")
    public Result remove(String order,String type) {
        SecUser secUser = Security.getUser();
        List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);

        for(ItemsTemporary itemsTemporary : itemsTemporaryList){
            int stockAll = stockInOutDeService.findUndoStockNum(itemsTemporary.getCode(),itemsTemporary.getBatch(),secUser.getOrgId());
            if(stockAll< itemsTemporary.getInventoryNum()){
                return ResultGenerator.genFailResult("《"+itemsTemporary.getCode()+"："+itemsTemporary.getName()+"》 领用物品不足，请刷新后重试");
            }
        }

        itemsTemporaryList.forEach(t->{
            List<StockInOutDe> stockInOutDeList= stockInOutDeService.findUndoStockList(t.getCode(),t.getBatch(),secUser.getOrgId());
            int remainingNum = t.getInventoryNum();
            Date date = new Date();
            for (StockInOutDe stockInOutDe : stockInOutDeList){
                StockInOutDe stockInOutDeNew = new StockInOutDe();
                stockInOutDeNew.setBatchId(stockInOutDe.getBatchId());
                stockInOutDeNew.setClinicId(stockInOutDe.getClinicId());
                stockInOutDeNew.setCreateTime(date);
                stockInOutDeNew.setInOutCode(stockInOutDe.getInOutCode());
                stockInOutDeNew.setInOutDeId(UUID.randomUUID().toString());
                stockInOutDeNew.setOperationType(type);
                stockInOutDeNew.setpBrand(stockInOutDe.getpBrand());
                stockInOutDeNew.setpModel(stockInOutDe.getpModel());
                stockInOutDeNew.setpName(stockInOutDe.getpName());
                stockInOutDeNew.setPrice(stockInOutDe.getPrice());
                stockInOutDeNew.setpUnit(stockInOutDe.getpUnit());
                stockInOutDeNew.setpSpe(stockInOutDe.getpSpe());
                stockInOutDeNew.setpType(stockInOutDe.getpType());
                stockInOutDeNew.setRemark(stockInOutDe.getRemark());
                stockInOutDeNew.setTypeId(stockInOutDe.getTypeId());
                stockInOutDeNew.setCost(stockInOutDe.getCost());
                stockInOutDeNew.setUserId(secUser.getId());
                stockInOutDeNew.setOrderId(order);
                stockInOutDeNew.setEndTime(stockInOutDe.getEndTime());
                stockInOutDeNew.setUserName(secUser.getUserName());
                stockInOutDeNew.setRemark(t.getRemark());
                if(remainingNum <= stockInOutDe.getNum()-stockInOutDe.getAllowance()){
                    stockInOutDeNew.setAllowance(0);
                    stockInOutDeNew.setNum(remainingNum);
                    stockInOutDeNew.setMoney(remainingNum * stockInOutDe.getCost());
                    stockInOutDeService.save(stockInOutDeNew);
                    stockInOutDe.setAllowance(stockInOutDe.getAllowance() + remainingNum);
                    stockInOutDeService.update(stockInOutDe);
                    break;
                }else{
                    remainingNum = remainingNum - (stockInOutDe.getNum()-stockInOutDe.getAllowance());
                    stockInOutDeNew.setAllowance(0);
                    stockInOutDeNew.setNum(stockInOutDe.getNum()-stockInOutDe.getAllowance());
                    stockInOutDeNew.setMoney((stockInOutDe.getNum()-stockInOutDe.getAllowance()) * stockInOutDe.getCost());
                    stockInOutDeService.save(stockInOutDeNew);
                    stockInOutDe.setAllowance(stockInOutDe.getNum());
                    stockInOutDeService.update(stockInOutDe);
                }
            }
        });

        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(order);
        StockInOut stockInOut = new StockInOut();
        stockInOut.setInOutId(UUID.randomUUID().toString());
        stockInOut.setCreator(secUser.getUserName());
        if("退货".equals(type)){
            stockInOut.setInObj("总部");
            stockInOut.setOutObj(secUser.getOrgId());
        }else if("退领".equals(type)){
            stockInOut.setInObj(secUser.getOrgId());
            stockInOut.setOutObj(secUser.getUserName());
        }
        stockInOut.setInOutCode(order);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType(type);
        stockInOut.setClinicId(secUser.getOrgId());
        stockInOut.setState(0);
        stockInOutService.save(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("confirm")
    public Result confirm(String order){
        SecUser secUser = Security.getUser();
        StockInOut stockInOut = stockInOutService.findStockOrder(secUser.getOrgId(),order);
        if(stockInOut.getState() == 1){
            return ResultGenerator.genFailResult("物品已出库，请勿重复操作！");
        }
        stockInOut.setState(1);
        stockInOutService.update(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/returnStock")
    public Result returnStock(String order,String type) {
        SecUser secUser = Security.getUser();
        List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);

        for(ItemsTemporary itemsTemporary : itemsTemporaryList){
            StockInOutDe stockInOutDe = stockInOutDeService.findById(itemsTemporary.getBatch());
            if(stockInOutDe.getAllowance()< itemsTemporary.getInventoryNum()){
                return ResultGenerator.genFailResult("《"+itemsTemporary.getCode()+"："+itemsTemporary.getName()+"》 库存物品不足，请刷新后重试");
            }
        }

        itemsTemporaryList.forEach(t -> {
            StockInOutDe stockInOutDe = stockInOutDeService.findById(t.getBatch());
            Date date = new Date();
            StockInOutDe stockInOutDeNew = new StockInOutDe();
            stockInOutDeNew.setBatchId(stockInOutDe.getBatchId());
            stockInOutDeNew.setClinicId(stockInOutDe.getClinicId());
            stockInOutDeNew.setCreateTime(date);
            stockInOutDeNew.setInOutCode(stockInOutDe.getInOutCode());
            stockInOutDeNew.setInOutDeId(UUID.randomUUID().toString());
            stockInOutDeNew.setOperationType(type);
            stockInOutDeNew.setpBrand(stockInOutDe.getpBrand());
            stockInOutDeNew.setpModel(stockInOutDe.getpModel());
            stockInOutDeNew.setpName(stockInOutDe.getpName());
            stockInOutDeNew.setPrice(stockInOutDe.getPrice());
            stockInOutDeNew.setpSpe(stockInOutDe.getpSpe());
            stockInOutDeNew.setpType(stockInOutDe.getpType());
            stockInOutDeNew.setpUnit(stockInOutDe.getpUnit());
            stockInOutDeNew.setRemark(stockInOutDe.getRemark());
            stockInOutDeNew.setTypeId(stockInOutDe.getTypeId());
            stockInOutDeNew.setCost(stockInOutDe.getCost());
            stockInOutDeNew.setUserId(secUser.getId());
            stockInOutDeNew.setOrderId(order);
            stockInOutDeNew.setEndTime(stockInOutDe.getEndTime());
            stockInOutDeNew.setUserName(secUser.getUserName());
            stockInOutDeNew.setAllowance(0);
            stockInOutDeNew.setNum(t.getInventoryNum());
            stockInOutDeNew.setRemark(t.getRemark());
            stockInOutDeNew.setMoney(t.getInventoryNum() * stockInOutDe.getCost());
            stockInOutDeService.save(stockInOutDeNew);
            stockInOutDe.setAllowance(stockInOutDe.getAllowance() - t.getInventoryNum());
            stockInOutDeService.update(stockInOutDe);
        });

        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(order);
        StockInOut stockInOut = new StockInOut();
        stockInOut.setInOutId(UUID.randomUUID().toString());
        stockInOut.setCreator(secUser.getUserName());
        stockInOut.setInObj("总部");
        stockInOut.setOutObj(secUser.getOrgId());
        stockInOut.setInOutCode(order);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType(type);
        stockInOut.setClinicId(secUser.getOrgId());
        stockInOut.setState(0);
        stockInOutService.save(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockInOutService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(StockInOut stockInOut) {
        stockInOutService.update(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        StockInOut stockInOut = stockInOutService.findById(id);
        return ResultGenerator.genSuccessResult(stockInOut);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<StockInOut> list = stockInOutService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findInOrder")
    public Result findInOrder(String time,String type,String code,int page,int size) {
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutService.findInOrder(secUser.getOrgId(),time,type,code,size,(page - 1) * size);

            int count = stockInOutService.findInOrderCount(secUser.getOrgId(),time,type,code);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/findOutOrder")
    public Result findOutOrder(String time,String type,String code,int page,int size) {
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutService.findOutOrder(secUser.getOrgId(),time,type,code,size,(page - 1) * size);
            int count = stockInOutService.findOutOrderCount(secUser.getOrgId(),time,type,code);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/findInternalOrder")
    public Result findInternalOrder(String time,String type,String code,int page,int size) {
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutService.findInternalOrder(secUser.getOrgId(),code,time,type,size,(page - 1) * size);
            int count = stockInOutService.findInternalOrderCount(secUser.getOrgId(),code,time,type);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/receiveEnd")
    public Result receiveEnd(String id) {
        try {
           StockInOut stockInOut = stockInOutService.findById(id);
            if(stockInOut.getState() == 1){
                return ResultGenerator.genFailResult("物品已领用，请勿重复操作！");
            }
           stockInOut.setState(1);
           stockInOutService.update(stockInOut);
            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/send")
    public Result send(String id,String order) {
        try {
            StockInOut stockInOut = stockInOutService.findById(id);
            if(stockInOut.getState() == 0.5){
                return ResultGenerator.genFailResult("物品已退还，请勿重复操作！");
            }
            stockInOut.setState(0.5);
            stockInOutService.update(stockInOut);
            List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);
            SecUser user = Security.getUser();
            itemsTemporaryList.forEach(t->{
                Stock stock = stockService.findStock(user.getOrgId(),t.getCode());
                stock.setStockNum(stock.getStockNum() - t.getInventoryNum());
                stockService.update(stock);
            });
            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }


    @GetMapping("/exportOutOrder")
    public void excelOutOrder(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("出库物品汇总");
        List<String> titles = new ArrayList<>();
        titles.add("出库单号");
        titles.add("出库日期");
        titles.add("单据状态");
        titles.add("出库类型");
        titles.add("物品数量");
        titles.add("总金额");
        titles.add("出库人");
        titles.add("审批人");
        titles.add("审批时间");
        titles.add("备注");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockInOutService.findOutOrderAll(secUser.getOrgId(),"","","");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("in_out_code"));
            row.add(t.get("in_out_time"));
            row.add(t.get("state"));
            row.add(t.get("type"));
            row.add(t.get("num"));
            row.add(t.get("money"));
            row.add(t.get("creator"));
            row.add(t.get("user_name"));
            row.add(t.get("audit_time"));
            row.add(t.get("remark"));
        });
        data.setRows(rows);

        String fileName="出库物品汇总.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

    @GetMapping("/exportInOrder")
    public void excelInOrder(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("入库物品汇总");
        List<String> titles = new ArrayList<>();
        titles.add("入库单号");
        titles.add("入库日期");
        titles.add("单据状态");
        titles.add("入库类型");
        titles.add("物品数量");
        titles.add("总金额");
        titles.add("入库人");
        titles.add("审批人");
        titles.add("审批时间");
        titles.add("备注");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockInOutService.findInOrderAll(secUser.getOrgId(),"","","");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("in_out_code"));
            row.add(t.get("in_out_time"));
            row.add(t.get("state"));
            row.add(t.get("type"));
            row.add(t.get("num"));
            row.add(t.get("money"));
            row.add(t.get("creator"));
            row.add(t.get("user_name"));
            row.add(t.get("audit_time"));
            row.add(t.get("remark"));
            rows.add(row);
        });
        data.setRows(rows);

        String fileName="入库物品汇总.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

    @GetMapping("/exportInternalOrder")
    public void exportInternalOrder(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("内部订单");
        List<String> titles = new ArrayList<>();
        titles.add("订单单号");
        titles.add("订单日期");
        titles.add("单据状态");
        titles.add("订单类型");
        titles.add("物品数量");
        titles.add("总金额");
        titles.add("订单发起人");
        titles.add("审批人");
        titles.add("审批时间");
        titles.add("备注");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockInOutService.findInternalOrderAll(secUser.getOrgId(),"","","");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("in_out_code"));
            row.add(t.get("in_out_time"));
            row.add(t.get("state"));
            row.add(t.get("type"));
            row.add(t.get("num"));
            row.add(t.get("money"));
            row.add(t.get("creator"));
            row.add(t.get("user_name"));
            row.add(t.get("audit_time"));
            row.add(t.get("remark"));
            rows.add(row);
        });
        data.setRows(rows);

        String fileName="内部订单汇总.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

    /**
     * 入库导入
     * @param request
     * @param file
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @PostMapping("/uploadInStock")
    public Result uploadInStock(HttpServletRequest request, @RequestParam("fileToUpload")MultipartFile file) throws IOException,ParseException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        int success = 0;
        int error = 0;
        List<ItemsTemporary> itemsTemporaryList = new ArrayList<>();
        String orderId = CodeHelper.getCode("DKC");
        for (int j = 0; j < physicalNumberOfRows; j++) {
            XSSFRow row = sheet.getRow(j);
            if (j == 0) {
                continue;//标题行
            }else if(row.getCell(10)!= null && row.getCell(11) !=null){
                if(row.getCell(11).getNumericCellValue()!=0) {
                    ItemsTemporary itemsTemporary = new ItemsTemporary();
                    itemsTemporary.setId(UUID.randomUUID().toString());
                    itemsTemporary.setOrderId(orderId);
                    itemsTemporary.setCode(row.getCell(0).getStringCellValue());
                    itemsTemporary.setName(row.getCell(1).getStringCellValue());
                    itemsTemporary.setTypeId(row.getCell(2).getStringCellValue());
                    itemsTemporary.setType(row.getCell(3).getStringCellValue());
                    itemsTemporary.setModel(row.getCell(4).getStringCellValue());
                    itemsTemporary.setSpecifications(row.getCell(5).getStringCellValue());
                    itemsTemporary.setBrand(row.getCell(6).getStringCellValue());
                    itemsTemporary.setUnit(row.getCell(7).getStringCellValue());
                    itemsTemporary.setInventoryCost(row.getCell(8).getNumericCellValue());
                    itemsTemporary.setRetailPrice(row.getCell(9).getNumericCellValue());
                    itemsTemporary.setValidity(row.getCell(10).getDateCellValue());
                    itemsTemporary.setInventoryNum((int) row.getCell(11).getNumericCellValue());
                    itemsTemporary.setRemark(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : "");
                    itemsTemporary.setMoney(itemsTemporary.getInventoryNum() * itemsTemporary.getInventoryCost());

                    itemsTemporaryList.add(itemsTemporary);
                    success++;
                }
            }else{
                return ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(1).getStringCellValue()+"》物品未设置入库数量或到期时间");
            }

        }
        itemsTemporaryList.forEach(t->{
            itemsTemporaryService.save(t);
        });
        SecUser secUser = Security.getUser();
        StockInOut stockInOut = new StockInOut();
        stockInOut.setInOutId(UUID.randomUUID().toString());
        stockInOut.setCreator(secUser.getUserName());
        stockInOut.setInObj(secUser.getOrgId());
        stockInOut.setOutObj("总部");
        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(orderId);
        stockInOut.setInOutCode(orderId);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType("进货");
        stockInOut.setClinicId(secUser.getOrgId());
        stockInOut.setState(0);
        stockInOutService.save(stockInOut);

        return ResultGenerator.genSuccessResult("上传成功！总数量："+(success)+"；");
    }

    /**
     * 下载出库模板
     * @param response
     * @throws Exception
     */
    @GetMapping("/exportInventory")
    public void excelInventory(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("现有库存汇总");
        List<String> titles = new ArrayList<>();
        titles.add("物品编号");
        titles.add("物品名称");
        titles.add("物品类型");
        titles.add("类型编号");
        titles.add("单位");
        titles.add("型号");
        titles.add("规格");
        titles.add("品牌");
        titles.add("出库数量(必填)");
        titles.add("单价");
        titles.add("金额");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockService.getStockAll(secUser.getOrgId(),"","");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("product_code"));
            row.add(t.get("product_name"));
            row.add(t.get("product_type"));
            row.add(t.get("type_id"));
            row.add(t.get("product_unit"));
            row.add(t.get("product_model"));
            row.add(t.get("product_spec"));
            row.add(t.get("product_brand"));
            row.add(t.get("stock_num"));
            row.add(t.get("sprice"));
            row.add(t.get("money"));
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
        cellType.add("文本");
        cellType.add("双精度浮点数");
        cellType.add("双精度浮点数");
        data.setRows(rows);
        data.setCellType(cellType);
        String fileName="出库导入模板.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }


    /**
     * 出库导入
     * @param request
     * @param file
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @PostMapping("/uploadOutStock")
    public Result uploadOutStock(HttpServletRequest request, @RequestParam("fileToUpload")MultipartFile file) throws IOException,ParseException {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        int success = 0;
        int error = 0;
        List<ItemsTemporary> itemsTemporaryList = new ArrayList<>();
        String orderId = CodeHelper.getCode("CK");
        for (int j = 0; j < physicalNumberOfRows; j++) {
            XSSFRow row = sheet.getRow(j);
            if (j == 0) {
                continue;//标题行
            }else if(row.getCell(8)!= null){
                    ItemsTemporary itemsTemporary = new ItemsTemporary();
                    itemsTemporary.setId(UUID.randomUUID().toString());
                    itemsTemporary.setOrderId(orderId);
                    itemsTemporary.setCode(row.getCell(0).getStringCellValue());
                    itemsTemporary.setName(row.getCell(1).getStringCellValue());
                    itemsTemporary.setType(row.getCell(2).getStringCellValue());
                    itemsTemporary.setTypeId(row.getCell(3).getStringCellValue());
                    itemsTemporary.setUnit(row.getCell(4).getStringCellValue());
                    itemsTemporary.setModel(row.getCell(5).getStringCellValue());
                    itemsTemporary.setSpecifications(row.getCell(6).getStringCellValue());
                    itemsTemporary.setBrand(row.getCell(7).getStringCellValue());
                    itemsTemporary.setInventoryNum((int) row.getCell(8).getNumericCellValue());
                    itemsTemporary.setInventoryCost(row.getCell(9).getNumericCellValue());
//                    itemsTemporary.setValidity(row.getCell(10).getDateCellValue());
//                    itemsTemporary.setRemark(row.getCell(12) != null ? row.getCell(12).getStringCellValue() : "");
                    itemsTemporary.setMoney(itemsTemporary.getInventoryNum() * itemsTemporary.getInventoryCost());

                    itemsTemporaryList.add(itemsTemporary);
                    success++;
            }else{
                return ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(1).getStringCellValue()+"》物品未设置数量");
            }

        }
        itemsTemporaryList.forEach(t->{
            itemsTemporaryService.save(t);
        });
        SecUser secUser = Security.getUser();
        StockInOut stockInOut = new StockInOut();
        stockInOut.setInOutId(UUID.randomUUID().toString());
        stockInOut.setCreator(secUser.getUserName());
        stockInOut.setInObj(secUser.getUserName());
        stockInOut.setOutObj(secUser.getOrgId());
        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(orderId);
        stockInOut.setInOutCode(orderId);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType("领用");
        stockInOut.setClinicId(secUser.getOrgId());
        stockInOut.setState(0);
        stockInOutService.save(stockInOut);

        return ResultGenerator.genSuccessResult("上传成功！总数量："+(success)+"；");
    }

}
