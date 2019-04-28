package com.yidusoft.project.system.controller;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecMenuOrg;
import com.yidusoft.project.system.service.SecMenuOrgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/menu/org")
public class SecMenuOrgController {
    @Resource
    private SecMenuOrgService secMenuOrgService;

    @PostMapping("/add")
    public Result add(SecMenuOrg secMenuOrg) {
        secMenuOrgService.save(secMenuOrg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        secMenuOrgService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SecMenuOrg secMenuOrg) {
        secMenuOrgService.update(secMenuOrg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SecMenuOrg secMenuOrg = secMenuOrgService.findById(id);
        return ResultGenerator.genSuccessResult(secMenuOrg);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SecMenuOrg> list = secMenuOrgService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
