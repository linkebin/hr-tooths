package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.ItemsTemporary;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.StockInOut;
import com.yidusoft.project.system.domain.StockInOutDe;
import com.yidusoft.project.system.service.ItemsTemporaryService;
import com.yidusoft.project.system.service.StockInOutDeService;
import com.yidusoft.project.system.service.StockInOutService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by CodeGenerator on 2018/11/13.
 */
@RestController
@RequestMapping("/stock/in/out/de")
public class StockInOutDeController {
    @Resource
    private StockInOutDeService stockInOutDeService;

    @Resource
    private StockInOutService stockInOutService;

    @Resource
    private ItemsTemporaryService itemsTemporaryService;

    @PostMapping("/add")
    public Result add(String order, String type) {
        try {
            List<ItemsTemporary> itemsTemporaryList = itemsTemporaryService.findItemsTemp(order);


            Date date = new Date();
            SecUser secUser = Security.getUser();
            itemsTemporaryList.forEach(itemsTemporary->{

                StockInOutDe stockInOutDe = new StockInOutDe();
                stockInOutDe.setBatchId(itemsTemporary.getBatch());
                stockInOutDe.setClinicId("122");
                stockInOutDe.setCreateTime(date);
                stockInOutDe.setInOutCode(itemsTemporary.getCode());
                stockInOutDe.setInOutDeId(UUID.randomUUID().toString());
                stockInOutDe.setMoney(itemsTemporary.getMoney());
                stockInOutDe.setNum(itemsTemporary.getInventoryNum());
                stockInOutDe.setOperationType(type);
                stockInOutDe.setpUnit(itemsTemporary.getUnit());
                stockInOutDe.setpBrand(itemsTemporary.getBrand());
                stockInOutDe.setpModel(itemsTemporary.getModel());
                stockInOutDe.setpName(itemsTemporary.getName());
                stockInOutDe.setPrice(itemsTemporary.getRetailPrice());
                stockInOutDe.setpSpe(itemsTemporary.getSpecifications());
                stockInOutDe.setpType(itemsTemporary.getType());
                stockInOutDe.setRemark(itemsTemporary.getRemark());
                stockInOutDe.setTypeId(itemsTemporary.getTypeId());
                stockInOutDe.setCost(itemsTemporary.getInventoryCost());
                stockInOutDe.setUserId(secUser.getId());
                stockInOutDe.setOrderId(order);
                stockInOutDe.setEndTime(itemsTemporary.getValidity());
                stockInOutDe.setUserName(secUser.getUserName());
                if("进货".equals(type)){
                    stockInOutDe.setAllowance(itemsTemporary.getInventoryNum());
                }else{
                    stockInOutDe.setAllowance(0);
                }
                stockInOutDeService.save(stockInOutDe);
            });

            Map<String,Object> map = itemsTemporaryService.getItemsTempSum(order);
            StockInOut stockInOut = new StockInOut();
            stockInOut.setInOutId(UUID.randomUUID().toString());
            stockInOut.setCreator(secUser.getUserName());
            if("进货".equals(type)){
                stockInOut.setInObj("122");
                stockInOut.setOutObj("总部");
            }else if("退货".equals(type)){
                stockInOut.setInObj("总部");
                stockInOut.setOutObj("122");
            }else if("领用".equals(type)){
                stockInOut.setInObj("122");
                stockInOut.setOutObj(secUser.getUserName());
            }else if("退领".equals(type)){
                stockInOut.setInObj(secUser.getUserName());
                stockInOut.setOutObj("122");
            }
            stockInOut.setInOutCode(order);
            stockInOut.setInOutTime(date);
            stockInOut.setMoney((Double) map.get("moneySum"));
            stockInOut.setNum(Integer.valueOf(map.get("numSum").toString()));
            stockInOut.setType(type);
            stockInOut.setClinicId("122");
            stockInOut.setState(0);
            stockInOutService.save(stockInOut);
            return ResultGenerator.genSuccessResult();
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockInOutDeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(StockInOutDe stockInOutDe) {
        stockInOutDeService.update(stockInOutDe);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        StockInOutDe stockInOutDe = stockInOutDeService.findById(id);
        return ResultGenerator.genSuccessResult(stockInOutDe);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<StockInOutDe> list = stockInOutDeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findStockItems")
    public Result findStockItems(String type_id, String code, int size, int page){
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutDeService.findStockItems("122",type_id,code,size,(page-1)*size);
            int count = stockInOutDeService.findStockItemsCount("122",type_id,code);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/findUndoStock")
    public Result findUndoStock(String type_id,String code, int size, int page){
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutDeService.findUndoStock("122",type_id,code,size,(page-1)*size);
            int count = stockInOutDeService.findUndoStockCount("122",type_id,code);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }

    @PostMapping("/findStockDetail")
    public Result findStockDetail(String orderId, String state, int page, int size){
        if ("未审核".equals(state)){
            List<Map<String,Object>> list = itemsTemporaryService.findStockDetail(orderId,size,(page-1)*size);
            int count = itemsTemporaryService.findStockDetailCount(orderId);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }else{
            List<StockInOutDe> stockInOutDeList = stockInOutDeService.findStockDetail(orderId,"",size,(page-1)*size);
            int count = stockInOutDeService.findStockDetailCount(orderId,"");
            return ResultGenerator.genSuccessResult(stockInOutDeList).setTotal(count);
        }
    }

    @PostMapping("/findInternalOrder")
    public Result findInternalOrder(String code, String time, int page, int size){
        try {
            SecUser secUser = Security.getUser();
            List<Map<String,Object>> list = stockInOutDeService.findInternalOrder("122",code,time,size,(page - 1) * size);
            int count = stockInOutDeService.findInternalOrderCount("122",code,time);
            return ResultGenerator.genSuccessResult(list).setTotal(count);
        }catch (Exception e){
            return ResultGenerator.genFailResult("");
        }
    }


}
