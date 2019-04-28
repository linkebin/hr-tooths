package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.AccountRecord;
import com.yidusoft.project.system.service.AccountRecordService;
import com.yidusoft.project.system.service.DaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2018/12/29.
*/
@RestController
@RequestMapping("/account/record")
public class AccountRecordController {
    @Resource
    private AccountRecordService accountRecordService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(AccountRecord accountRecord) {
        accountRecordService.save(accountRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        accountRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(AccountRecord accountRecord) {
        accountRecordService.update(accountRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        AccountRecord accountRecord = accountRecordService.findById(id);
        return ResultGenerator.genSuccessResult(accountRecord);
    }

    @PostMapping("/list")
     public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<AccountRecord> list = accountRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/lista")
    public Result lista(String time, String type, String ids) {
        Map<String,Object> map=new HashMap<>();
        map.put("record_type",type);
        map.put("create_time", time);
        map.put("cust_id",ids);
        List<Map<String,Object>> list=accountRecordService.getList(map);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/consumeLog")
    public Result consumeLog(String sfId, Integer page, Integer size){
        PageHelper.startPage(page, size);
        List<Map<String,Object>> accountRecordList = daoService.getDivList("select a.*,b.user_name from account_record a,sec_user b where a.sf_id = '"+ sfId +"' and a.user_id = b.ID_ order by a.create_time");
        PageInfo pageInfo = new PageInfo(accountRecordList);
        return ResultGenerator.genTableListResult(pageInfo);
    }
}
