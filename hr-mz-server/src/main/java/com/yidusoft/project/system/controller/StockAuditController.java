package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.*;
import com.yidusoft.project.system.service.*;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/stock/audit")
public class StockAuditController {
    @Resource
    private StockAuditService stockAuditService;

    @Resource
    private StockInOutDeService stockInOutDeService;

    @Resource
    private StockService stockService;

    @Resource
    private ItemsTemporaryService itemsTemporaryService;

    @Resource
    private DaoService daoService;

    @PostMapping("/addOutStock")
    public Result add(String id,String order,String type,String clinic,String userId,String username,String remark) {
        List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);

        boolean is = false;
        for(ItemsTemporary itemsTemporary : itemsTemporaryList){
            int stockAll = stockInOutDeService.getProductStockAll(clinic,itemsTemporary.getCode());
            if(stockAll< itemsTemporary.getInventoryNum()){
                return ResultGenerator.genFailResult("《"+itemsTemporary.getCode()+"："+itemsTemporary.getName()+"》 物品库存不足");
            }
        }

        StockAudit hasStockAudit =  daoService.getObject("select * from stock_audit where in_out_id='"+id+"'",StockAudit.class);
        if(hasStockAudit!=null){
            return ResultGenerator.genFailResult("订单已审核！请勿重复审核");
        }
        SecUser secUser = Security.getUser();
        StockAudit stockAudit = new StockAudit();
        stockAudit.setAuditId(UUID.randomUUID().toString());
        stockAudit.setAuditTime(new Date());
        stockAudit.setInOutId(id);
        stockAudit.setIsPass(1);
        stockAudit.setUserId(secUser.getId());
        stockAudit.setUserName(secUser.getUserName());
        stockAudit.setRemark(remark);
        stockAuditService.save(stockAudit);


        Date date = new Date();
        itemsTemporaryList.forEach(itemsTemporary->{
            List<StockInOutDe> stockInOutDeList = stockInOutDeService.getProductStock(clinic,itemsTemporary.getCode());
            int remainingNum = itemsTemporary.getInventoryNum();

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
                stockInOutDeNew.setUserId(userId);
                stockInOutDeNew.setOrderId(order);
                stockInOutDeNew.setEndTime(stockInOutDe.getEndTime());
                stockInOutDeNew.setUserName(username);
                if(remainingNum <= stockInOutDe.getAllowance()){
                    stockInOutDeNew.setAllowance(0);
                    stockInOutDeNew.setNum(remainingNum);
                    stockInOutDeNew.setMoney(remainingNum * stockInOutDe.getCost());
                    stockInOutDeService.save(stockInOutDeNew);
                    stockInOutDe.setAllowance(stockInOutDe.getAllowance() - remainingNum);
                    stockInOutDeService.update(stockInOutDe);
                    break;
                }else{
                    remainingNum = remainingNum - stockInOutDe.getAllowance();
                    stockInOutDeNew.setAllowance(0);
                    stockInOutDeNew.setNum(stockInOutDe.getAllowance());
                    stockInOutDeNew.setMoney(stockInOutDe.getAllowance() * stockInOutDe.getCost());
                    stockInOutDeService.save(stockInOutDeNew);
                    stockInOutDe.setAllowance(0);
                    stockInOutDeService.update(stockInOutDe);
                }
            }
            Stock stock = stockService.findStock(secUser.getOrgId(),itemsTemporary.getCode());
            stock.setStockNum(stock.getStockNum() - itemsTemporary.getInventoryNum());
            stockService.update(stock);
        });

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/addInStock")
    public Result remove(String id,String order,String remark) {
        StockAudit hasStockAudit =  daoService.getObject("select * from stock_audit where in_out_id='"+id+"'",StockAudit.class);
        if(hasStockAudit!=null){
            return ResultGenerator.genFailResult("订单已审核！请勿重复审核");
        }
        SecUser secUser = Security.getUser();
        StockAudit stockAudit = new StockAudit();
        stockAudit.setAuditId(UUID.randomUUID().toString());
        stockAudit.setAuditTime(new Date());
        stockAudit.setInOutId(id);
        stockAudit.setIsPass(1);
        stockAudit.setUserId(secUser.getId());
        stockAudit.setUserName(secUser.getUserName());
        stockAudit.setRemark(remark);
        stockAuditService.save(stockAudit);
        List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);


        itemsTemporaryList.forEach(itemsTemporary->{
            List<StockInOutDe> list=daoService.getbyList("select * from stock_in_out_de where operation_type = '进货' and batch_id ='"+itemsTemporary.getBatch()+"' and in_out_code = '"+itemsTemporary.getCode()+"' and clinic_id='"+secUser.getOrgId()+"' order by create_time desc",StockInOutDe.class);
            int remainingNum = itemsTemporary.getInventoryNum();
            for (StockInOutDe stockInOutDe :list){
                if(remainingNum<= stockInOutDe.getNum()-stockInOutDe.getAllowance()){
                    stockInOutDe.setAllowance(stockInOutDe.getAllowance() + remainingNum);
                    stockInOutDeService.update(stockInOutDe);
                    break;
                }else{
                    remainingNum = remainingNum - (stockInOutDe.getNum()-stockInOutDe.getAllowance());
                    stockInOutDe.setAllowance(stockInOutDe.getNum());
                    stockInOutDeService.update(stockInOutDe);
                }
            }
            Stock stock = stockService.findStock(secUser.getOrgId(), itemsTemporary.getCode());
            stock.setStockNum(stock.getStockNum() + itemsTemporary.getInventoryNum());
            stockService.update(stock);
        });

        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockAuditService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(StockAudit stockAudit) {
        stockAuditService.update(stockAudit);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        StockAudit stockAudit = stockAuditService.findById(id);
        return ResultGenerator.genSuccessResult(stockAudit);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<StockAudit> list = stockAuditService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/noPassIn")
    public Result noPassIn(String id,String remark){
        StockAudit hasStockAudit =  daoService.getObject("select * from stock_audit where in_out_id='"+id+"'",StockAudit.class);
        if(hasStockAudit!=null){
            return ResultGenerator.genFailResult("订单已审核！请勿重复审核");
        }
        SecUser secUser = Security.getUser();
        StockAudit stockAudit = new StockAudit();
        stockAudit.setAuditId(UUID.randomUUID().toString());
        stockAudit.setAuditTime(new Date());
        stockAudit.setInOutId(id);
        stockAudit.setIsPass(0);
        stockAudit.setUserId(secUser.getId());
        stockAudit.setUserName(secUser.getUserName());
        stockAudit.setRemark(remark);
        stockAuditService.save(stockAudit);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/noPassOut")
    public Result noPassOut(String id,String remark,String order){
        StockAudit hasStockAudit =  daoService.getObject("select * from stock_audit where in_out_id='"+id+"'",StockAudit.class);
        if(hasStockAudit!=null){
            return ResultGenerator.genFailResult("订单已审核！请勿重复审核");
        }
        SecUser secUser = Security.getUser();
        StockAudit stockAudit = new StockAudit();
        stockAudit.setAuditId(UUID.randomUUID().toString());
        stockAudit.setAuditTime(new Date());
        stockAudit.setInOutId(id);
        stockAudit.setIsPass(0);
        stockAudit.setUserId(secUser.getId());
        stockAudit.setUserName(secUser.getUserName());
        stockAudit.setRemark(remark);
        stockAuditService.save(stockAudit);
        List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);

        itemsTemporaryList.forEach(t->{
            List<StockInOutDe> stockInOutDeList= stockInOutDeService.findUndoStockList(t.getCode(),t.getBatch(),secUser.getOrgId());
            int num = t.getInventoryNum();
            for (StockInOutDe stockInOutDe : stockInOutDeList){
                if(num > stockInOutDe.getAllowance()){
                    stockInOutDe.setAllowance(stockInOutDe.getAllowance() - num);
                    break;
                }else{
                    num = num - stockInOutDe.getAllowance();
                    stockInOutDe.setAllowance(0);
                }
                stockInOutDeService.update(stockInOutDe);
            };
        });

        return ResultGenerator.genSuccessResult();
    }
}
