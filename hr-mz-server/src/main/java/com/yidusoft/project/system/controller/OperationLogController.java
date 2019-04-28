package com.yidusoft.project.system.controller;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.OperationLog;
import com.yidusoft.project.system.service.OperationLogService;
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
@RequestMapping("/operation/log")
public class OperationLogController {
    @Resource
    private OperationLogService operationLogService;

    @PostMapping("/add")
    public Result add(OperationLog operationLog) {
        operationLogService.save(operationLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        operationLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(OperationLog operationLog) {
        operationLogService.update(operationLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        OperationLog operationLog = operationLogService.findById(id);
        return ResultGenerator.genSuccessResult(operationLog);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<OperationLog> list = operationLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
