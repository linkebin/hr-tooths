package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.ItemsTemporary;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.StockInOutDe;
import com.yidusoft.project.system.domain.StockProduct;
import com.yidusoft.project.system.service.*;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/items/temp")
public class ItemsTemporaryController {
    @Resource
    private ItemsTemporaryService itemsTemporaryService;

    @Resource
    private StockService stockService;

    @Resource
    private StockProductService stockProductService;

    @Resource
    private StockInOutDeService stockInOutDeService;

    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(int num, String batch, String code, double sellPrice, String remark,String validity,double inventoryCost,String production_code,String supplier,String expiration_time,String start_time) throws ParseException {
        ItemsTemporary itemsTemporary = new ItemsTemporary();
        StockProduct stockProduct = stockProductService.findStockProduct(code);
        itemsTemporary.setId(UUID.randomUUID().toString());
        itemsTemporary.setBrand(stockProduct.getProductBrand());
        itemsTemporary.setCode(code);
        itemsTemporary.setInventoryNum(num);
        itemsTemporary.setModel(stockProduct.getProductModel());
        itemsTemporary.setMoney(stockProduct.getInStockPrice()*num);
        itemsTemporary.setName(stockProduct.getProductName());
        itemsTemporary.setRetailPrice(sellPrice);
        itemsTemporary.setUnit(stockProduct.getProductUnit());
        itemsTemporary.setSpecifications(stockProduct.getProductSpec());
        itemsTemporary.setType(stockProduct.getProductType());
        itemsTemporary.setBatch(batch);
        itemsTemporary.setOrderId(batch);
        itemsTemporary.setTypeId(stockProduct.getTypeId());
        itemsTemporary.setRemark(remark);
        itemsTemporary.setInventoryCost(inventoryCost);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(validity);
        itemsTemporary.setProductionCode(production_code);
        if(start_time!=null&&!start_time.equals("")){
            itemsTemporary.setStartTime(sdf.parse(start_time));
        }
        itemsTemporary.setExpirationTime(expiration_time);
        itemsTemporary.setSupplier(supplier);
        itemsTemporary.setValidity(date);
        ItemsTemporary item = daoService.getObject("select * from items_temporary where order_id='"+batch+"' and code = '"+code+"'",ItemsTemporary.class);
        itemsTemporaryService.save(itemsTemporary);
        if(item == null){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genSuccessResult("添加成功！添加物品已存在，请确认是否重复添加！");
        }
    }

    @PostMapping("/remove")
    public Result remove(int num, String code,String order){
        ItemsTemporary itemsTemporary = new ItemsTemporary();
        StockInOutDe stockInOutDe = stockInOutDeService.findById(code);
        int selected = itemsTemporaryService.getItemsNum(order,stockInOutDe.getInOutCode());
        if(stockInOutDe.getAllowance() < selected + num){
            return ResultGenerator.genFailResult("所选物品已超库存剩余数量！");
        }
        itemsTemporary.setId(UUID.randomUUID().toString());
        itemsTemporary.setBrand(stockInOutDe.getpBrand());
        itemsTemporary.setCode(stockInOutDe.getInOutCode());
        itemsTemporary.setInventoryCost(stockInOutDe.getCost());
        itemsTemporary.setInventoryNum(num);
        itemsTemporary.setModel(stockInOutDe.getpModel());
        itemsTemporary.setMoney(stockInOutDe.getMoney());
        itemsTemporary.setUnit(stockInOutDe.getpUnit());
        itemsTemporary.setName(stockInOutDe.getpName());
        itemsTemporary.setRetailPrice(stockInOutDe.getPrice());
        itemsTemporary.setSpecifications(stockInOutDe.getpSpe());
        itemsTemporary.setType(stockInOutDe.getpType());
        itemsTemporary.setBatch(stockInOutDe.getBatchId());
        itemsTemporary.setOrderId(order);
        itemsTemporary.setTypeId(stockInOutDe.getTypeId());
        itemsTemporary.setRemark("");
        itemsTemporary.setValidity(stockInOutDe.getEndTime());
        ItemsTemporary item = daoService.getObject("select * from items_temporary where order_id='"+order+"' and code = '"+code+"'",ItemsTemporary.class);
        itemsTemporaryService.save(itemsTemporary);
        if(item == null){
            return ResultGenerator.genSuccessResult();
        }else{
            return ResultGenerator.genSuccessResult("添加成功！添加物品已存在，请确认是否重复添加！");
        }
    }

    @GetMapping("/addItems")
    public Result addItems(int num, String code, String order) {
        SecUser secUser = Security.getUser();
        int inventory = stockService.findStockNum("122",code);
        int selected = itemsTemporaryService.getItemsNum(order,code);

        if(inventory < selected + num){
            return ResultGenerator.genFailResult("所选物品已超库存！");
        }

        ItemsTemporary itemsTemporary = new ItemsTemporary();
        StockProduct stockProduct = stockProductService.findStockProduct(code);

        itemsTemporary.setId(UUID.randomUUID().toString());
        itemsTemporary.setBrand(stockProduct.getProductBrand());
        itemsTemporary.setCode(code);
        itemsTemporary.setInventoryCost(stockProduct.getInStockPrice());
        itemsTemporary.setInventoryNum(num);
        itemsTemporary.setUnit(stockProduct.getProductUnit());
        itemsTemporary.setModel(stockProduct.getProductModel());
        itemsTemporary.setMoney(stockProduct.getInStockPrice()*num);
        itemsTemporary.setName(stockProduct.getProductName());
        itemsTemporary.setRetailPrice(stockProduct.getSellPrice());
        itemsTemporary.setSpecifications(stockProduct.getProductSpec());
        itemsTemporary.setType(stockProduct.getProductType());
        itemsTemporary.setOrderId(order);
        itemsTemporary.setTypeId(stockProduct.getTypeId());
        itemsTemporaryService.save(itemsTemporary);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/removeItems")
    public Result undoStock(String order, String batch, String code, int num) {
        SecUser secUser = Security.getUser();
        int inventory = stockInOutDeService.findUndoStockNum(code,batch,"122");
        int selected = itemsTemporaryService.getItemsNum(order,code);
        if(inventory < num + selected){
            return ResultGenerator.genFailResult("所选物品已超领用数量！");
        }
        ItemsTemporary itemsTemporary = new ItemsTemporary();
        StockProduct stockProduct = stockProductService.findStockProduct(code);
        itemsTemporary.setBatch(batch);
        itemsTemporary.setId(UUID.randomUUID().toString());
        itemsTemporary.setBrand(stockProduct.getProductBrand());
        itemsTemporary.setCode(code);
        itemsTemporary.setInventoryCost(stockProduct.getInStockPrice());
        itemsTemporary.setInventoryNum(num);
        itemsTemporary.setUnit(stockProduct.getProductUnit());
        itemsTemporary.setModel(stockProduct.getProductModel());
        itemsTemporary.setMoney(stockProduct.getInStockPrice()*num);
        itemsTemporary.setName(stockProduct.getProductName());
        itemsTemporary.setRetailPrice(stockProduct.getSellPrice());
        itemsTemporary.setSpecifications(stockProduct.getProductSpec());
        itemsTemporary.setType(stockProduct.getProductType());
        itemsTemporary.setOrderId(order);
        itemsTemporary.setTypeId(stockProduct.getTypeId());
        itemsTemporaryService.save(itemsTemporary);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        itemsTemporaryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/deleteCode")
    public Result deleteCode(String code, String batch) {
        itemsTemporaryService.deleteCode(code,batch);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ItemsTemporary inOrder) {
        itemsTemporaryService.update(inOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        ItemsTemporary itemsTemporary = itemsTemporaryService.findById(id);
        return ResultGenerator.genSuccessResult(itemsTemporary);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ItemsTemporary> list = itemsTemporaryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/getItemsTemp")
    public Result getItemsTemp(String batch, int size, int page) {
        List<Map<String,Object>> list = itemsTemporaryService.getItemsTemp(batch,size,(page-1)*size);
        int count = itemsTemporaryService.getItemsTempCount(batch);
        return ResultGenerator.genSuccessResult(list).setTotal(count);
    }

    @PostMapping("/getItemsTempSum")
    public Result getItemsTempSum(String batch) {
        Map<String,Object> map = itemsTemporaryService.getItemsTempSum(batch);
        return ResultGenerator.genSuccessResult(map);
    }
}
