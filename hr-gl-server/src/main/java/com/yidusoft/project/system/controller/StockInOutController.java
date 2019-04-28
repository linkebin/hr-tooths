package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.ItemsTemporary;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.StockInOut;
import com.yidusoft.project.system.domain.StockInOutDe;
import com.yidusoft.project.system.service.ItemsTemporaryService;
import com.yidusoft.project.system.service.StockInOutDeService;
import com.yidusoft.project.system.service.StockInOutService;
import com.yidusoft.project.system.service.StockService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.apache.poi.ss.usermodel.Cell;
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
    public Result add(String order, String type) {
        SecUser secUser = Security.getUser();
        StockInOut stockInOut = new StockInOut();
        stockInOut.setInOutId(UUID.randomUUID().toString());
        stockInOut.setCreator(secUser.getUserName());
        stockInOut.setInObj("总部");
        stockInOut.setOutObj("服务商");
        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(order);
        stockInOut.setInOutCode(order);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType(type);
        stockInOut.setClinicId("122");
        stockInOut.setState(0);
        stockInOutService.save(stockInOut);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/remove")
    public Result remove(String order, String type) {
        SecUser secUser = Security.getUser();
        List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);

        for(ItemsTemporary itemsTemporary : itemsTemporaryList){
            int stockAll = stockInOutDeService.findUndoStockNum(itemsTemporary.getCode(),itemsTemporary.getBatch(),"122");
            if(stockAll< itemsTemporary.getInventoryNum()){
                return ResultGenerator.genFailResult("《"+itemsTemporary.getCode()+"："+itemsTemporary.getName()+"》 物品库存不足，请刷新后重试");
            }
        }

        itemsTemporaryList.forEach(t->{
            List<StockInOutDe> stockInOutDeList= stockInOutDeService.findUndoStockList(t.getCode(),t.getBatch(),"122");
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
                stockInOutDeNew.setpSpe(stockInOutDe.getpSpe());
                stockInOutDeNew.setpUnit(stockInOutDe.getpUnit());
                stockInOutDeNew.setpType(stockInOutDe.getpType());
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
            }
        });

        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(order);
        StockInOut stockInOut = new StockInOut();
        stockInOut.setInOutId(UUID.randomUUID().toString());
        stockInOut.setCreator(secUser.getUserName());
        stockInOut.setInObj("服务商");
        stockInOut.setOutObj("总部");
        stockInOut.setInOutCode(order);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType(type);
        stockInOut.setClinicId("122");
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

    @PostMapping("/findOrder")
    public Result findOrder(String time, String type, String code, int page, int size) {
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutService.findOrder("122",time,type,code,size,(page - 1) * size);

            int count = stockInOutService.findOrderCount("122",time,type,code);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/receiveEnd")
    public Result receiveEnd(String id) {
        try {
           StockInOut stockInOut = stockInOutService.findById(id);
           stockInOut.setState(1);
           stockInOutService.update(stockInOut);
            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/findInternalOrder")
    public Result findInternalOrder(String time,String type,String code,int page,int size) {
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutService.findInternalOrder("122",code,time,type,size,(page - 1) * size);
            int count = stockInOutService.findInternalOrderCount("122",code,time,type);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

	@PostMapping("/findToDoOrder")
	public Result findToDoOrder(int page,int size) {
		try {
            PageHelper.startPage(page, size);
            List<Map<String,Object>> list = stockInOutService.findToDoOrder();
            PageInfo pageInfo = new PageInfo(list);
			return ResultGenerator.genTableListResult(pageInfo);
		}catch (Exception e){
			return ResultGenerator.genFailResult("");
		}
	}

    @PostMapping("/findApplyOrder")
    public Result findApplyOrder(String time,String type,String code,int page,int size) {
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutService.findApplyOrder("122",code,time,type,size,(page - 1) * size);
            int count = stockInOutService.findApplyOrderCount("122",code,time,type);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @GetMapping("/exportInOrder")
    public void excelInOrder(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("库存物品汇总");
        List<String> titles = new ArrayList<>();
        titles.add("库存单号");
        titles.add("进/退货日期");
        titles.add("单据状态");
        titles.add("操作类型");
        titles.add("物品数量");
        titles.add("总金额");
        titles.add("操作人");
        titles.add("审批人");
        titles.add("审批时间");
        titles.add("备注");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = stockInOutService.findOrderAll("122","","","");
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

        String fileName="库存订单记录.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

    @GetMapping("/exportApplyOrder")
    public void exportApplyOrder(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("进退货申请订单");
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
        List<Map<String,Object>> list = stockInOutService.findInternalOrderAll("122","","","");
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

        String fileName="进退货申请订单汇总.xls";
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
        List<Map<String,Object>> list = stockInOutService.findInternalOrderAll("122","","","");
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
                if(row.getCell(11).getNumericCellValue()!=0){
                    if(row.getCell(3).getStringCellValue().indexOf("药品")!=-1){
                        if(row.getCell(13)==null ){
                            return ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(1).getStringCellValue()+"》药品设置生产批号");
                        }else if(row.getCell(14)==null){
                            return ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(1).getStringCellValue()+"》药品未设置生产日期");
                        }else if(row.getCell(15)==null){
                            return ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(1).getStringCellValue()+"》药品未设置保质期");
                        }else if(row.getCell(16)==null){
                            return ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(1).getStringCellValue()+"》药品未设置供应商");
                        }
                    }
                    ItemsTemporary itemsTemporary = new ItemsTemporary();
                    itemsTemporary.setId(UUID.randomUUID().toString());
                    itemsTemporary.setOrderId(orderId);
                    itemsTemporary.setBatch(orderId);
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
                    try {
                        itemsTemporary.setValidity(row.getCell(10).getDateCellValue());
                    }catch (Exception e){
                        e.printStackTrace();
                        return     ResultGenerator.genFailResult("第"+(j+1)+"行《"+row.getCell(1).getStringCellValue()+"》物品的有效期至字段格式有误，请检查单元格式或者内容。格式请保持与下载时一致！");
                    }
                    itemsTemporary.setInventoryNum((int)row.getCell(11).getNumericCellValue());
                    itemsTemporary.setRemark(row.getCell(12) !=null ?row.getCell(12).getStringCellValue(): "");
                    itemsTemporary.setMoney(itemsTemporary.getInventoryNum() * itemsTemporary.getInventoryCost());
                    if(row.getCell(13) !=null) {
                        row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                        itemsTemporary.setProductionCode(row.getCell(13).getStringCellValue());
                    }
                    if(row.getCell(14) !=null){
                        row.getCell(14).setCellType(Cell.CELL_TYPE_FORMULA);
                        itemsTemporary.setStartTime(row.getCell(14).getDateCellValue());
                    }
                    if(row.getCell(15) !=null){
                        row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                        itemsTemporary.setExpirationTime(row.getCell(15).getStringCellValue());
                    }
                    itemsTemporary.setSupplier(row.getCell(16) !=null ?row.getCell(16).getStringCellValue():"");
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
        stockInOut.setInObj("总部");
        stockInOut.setOutObj("服务商");
        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(orderId);
        stockInOut.setInOutCode(orderId);
        stockInOut.setCreatorId(secUser.getId());
        stockInOut.setInOutTime(new Date());
        stockInOut.setMoney(Double.valueOf(map.get("moneySum").toString()));
        stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
        stockInOut.setType("进货");
        stockInOut.setClinicId("122");
        stockInOut.setState(0);
        stockInOutService.save(stockInOut);

        return ResultGenerator.genSuccessResult("上传成功！总数量："+(success)+"；");
    }
}
