package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.StockProductType;
import com.yidusoft.project.system.service.StockProductTypeService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/stock/product/type")
public class StockProductTypeController {
    @Resource
    private StockProductTypeService stockProductTypeService;

    @PostMapping("/add")
    public Result add(String json) {
        SecUser secUser = Security.getUser();
        StockProductType stockProductType = JSON.parseObject(json,StockProductType.class);
        if(stockProductType.getParentId() == null || "".equals(stockProductType.getParentId())){
            stockProductType.setParentId("CD0001");
        }
        stockProductType.setTypeId(UUID.randomUUID().toString());
        stockProductType.setCreator(secUser.getUserName());
        stockProductTypeService.save(stockProductType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        stockProductTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String json) {
        SecUser secUser = Security.getUser();
        StockProductType stockProductType = JSON.parseObject(json,StockProductType.class);
        stockProductType.setCreator(secUser.getUserName());
        stockProductTypeService.update(stockProductType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        StockProductType stockProductType = stockProductTypeService.findById(id);
        return ResultGenerator.genSuccessResult(stockProductType);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<StockProductType> list = stockProductTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findTree")
    public String findTree() {
        List<Map<String,Object>> list = stockProductTypeService.findProductType();
        return JSON.toJSONString(list);
    }

    @PostMapping("/treeInfo")
    public Result treeInfo(String pId,int size,int page) {
        SecUser secUser = Security.getUser();
        List<StockProductType> list = stockProductTypeService.getProductType(secUser.getOrgId(),pId,size,(page-1)*size);
        int count = stockProductTypeService.getProductTypeCount(secUser.getOrgId(),pId);
        return ResultGenerator.genSuccessResult(list).setTotal(count);
    }

    @PostMapping("/getInfo")
    public Result getInfo(String typeId) {
        StockProductType stockProductType = stockProductTypeService.findById(typeId);
        return ResultGenerator.genSuccessResult(stockProductType);
    }
}
