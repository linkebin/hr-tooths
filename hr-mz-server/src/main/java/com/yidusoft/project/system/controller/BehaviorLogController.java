package com.yidusoft.project.system.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.BehaviorLog;
import com.yidusoft.project.system.service.BehaviorLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/behavior/log")
public class BehaviorLogController {
    @Resource
    private BehaviorLogService behaviorLogService;

    @PostMapping("/add")
    public Result add(BehaviorLog behaviorLog) {
        behaviorLogService.save(behaviorLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        behaviorLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(BehaviorLog behaviorLog) {
        behaviorLogService.update(behaviorLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        BehaviorLog behaviorLog = behaviorLogService.findById(id);
        return ResultGenerator.genSuccessResult(behaviorLog);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<BehaviorLog> list = behaviorLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
