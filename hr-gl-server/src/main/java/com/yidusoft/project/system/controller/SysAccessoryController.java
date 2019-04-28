package com.yidusoft.project.system.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SysAccessory;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.SysAccessoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/12/18.
*/
@RestController
@RequestMapping("/sys/accessory")
public class SysAccessoryController {
    @Resource
    private SysAccessoryService sysAccessoryService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(SysAccessory sysAccessory) {
        sysAccessoryService.save(sysAccessory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        sysAccessoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysAccessory sysAccessory) {
        sysAccessoryService.update(sysAccessory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        List<SysAccessory> list = daoService.getbyList("select * from sys_accessory s where s.re_id='" + id + "'", SysAccessory.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysAccessory> list = sysAccessoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
