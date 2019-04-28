package com.yidusoft.project.system.controller;
import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.CustJz;
import com.yidusoft.project.system.service.CustJzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/cust/jz")
public class CustJzController {
    @Resource
    private CustJzService custJzService;

    @PostMapping("/add")
    public Result add(String param) {
        CustJz custJz= JSON.parseObject(param,CustJz.class);
        custJz.setJzId(UUID.randomUUID().toString());
        custJzService.save(custJz);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        custJzService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String param) {
        CustJz custJz= JSON.parseObject(param,CustJz.class);
        custJzService.update(custJz);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        CustJz custJz = custJzService.findById(id);
        return ResultGenerator.genSuccessResult(custJz);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CustJz> list = custJzService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
