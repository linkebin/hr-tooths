package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.LoginLog;
import com.yidusoft.project.system.service.LoginLogService;
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
@RequestMapping("/login/log")
public class LoginLogController {
    @Resource
    private LoginLogService loginLogService;

    @PostMapping("/add")
    public Result add(LoginLog loginLog) {
        loginLogService.save(loginLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        loginLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(LoginLog loginLog) {
        loginLogService.update(loginLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        LoginLog loginLog = loginLogService.findById(id);
        return ResultGenerator.genSuccessResult(loginLog);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<LoginLog> list = loginLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getLoginLog")
    public Result getLoginLog( Integer page, Integer size,String account,String success){
        PageHelper.startPage(page, size);
        List<LoginLog> list = loginLogService.getLoginLog(account,success);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }
}
