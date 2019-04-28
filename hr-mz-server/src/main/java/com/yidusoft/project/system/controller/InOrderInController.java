package com.yidusoft.project.system.controller;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.InOrderIn;
import com.yidusoft.project.system.service.InOrderInService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/in/order/in")
public class InOrderInController {
    @Resource
    private InOrderInService inOrderInService;

    @PostMapping("/add")
    public Result add(InOrderIn inOrderIn) {
        inOrderInService.save(inOrderIn);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        inOrderInService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(InOrderIn inOrderIn) {
        inOrderInService.update(inOrderIn);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        InOrderIn inOrderIn = inOrderInService.findById(id);
        return ResultGenerator.genSuccessResult(inOrderIn);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<InOrderIn> list = inOrderInService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
