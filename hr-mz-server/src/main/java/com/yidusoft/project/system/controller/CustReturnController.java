package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.CustReturn;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.CustReturnService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2019/03/02.
*/
@RestController
@RequestMapping("/cust/return")
public class CustReturnController {

    @Resource
    private CustReturnService custReturnService;
    @Resource
    private DaoService daoService;

    @PostMapping("/update")
    public Result update(String obj) {
        CustReturn custReturn = JSONObject.parseObject(obj, CustReturn.class);
        custReturnService.update(custReturn);
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/add")
    public Result add(String obj) {
        CustReturn custReturn = JSONObject.parseObject(obj, CustReturn.class);
        custReturn.setId(UUID.randomUUID().toString());
        custReturn.setCreator(Security.getUser().getUserName());
        custReturn.setCreateTime(new Date());
        custReturn.setDeleted("0");
        custReturnService.save(custReturn);
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/addBatch")
    public Result addBatch(String obj,String custId) {
        Calendar ca = Calendar.getInstance();
        List<Map> list = JSONObject.parseObject(obj, List.class);
        list.forEach((Map t) ->{
            String planNum = (String) t.get("planNum");
            if(StringUtils.isNotBlank(planNum)){
                CustReturn custReturn = new CustReturn();
                custReturn.setId(UUID.randomUUID().toString());
                custReturn.setCreator(Security.getUser().getUserName());
                custReturn.setCreateTime(new Date());
                custReturn.setDeleted("0");
                custReturn.setReturnState("未访");
                custReturn.setReturnContent((String) t.get("returnContent"));
                ca.add(Calendar.DATE, (Integer.parseInt(planNum)));
                Date   currdate = ca.getTime();
                custReturn.setReturnTime(currdate);
                custReturn.setCustId(custId);
                custReturnService.save(custReturn);
            }
        });
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/list")
    public Result list(String custId,int page,int size) {
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = custReturnService.getReturnS(secUser.getOrgId(),custId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/delete")
    public Result delete(String  id) {
        daoService.getDivList("update cust_return set deleted  = 1 where id_ = '"+id+"'");
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/indexList")
    public Result indexList(Integer days,int page,int size) {
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = daoService.getDivList("select c.cust_name,ct.return_time,(TO_DAYS(return_time)-TO_DAYS(NOW())) as dist_day from cust_return ct\n" +
                "LEFT JOIN customer c on ct.cust_id = c.cust_id\n" +
                "where  ct.deleted = '0' \n" +
                "and ct.return_state = '未访' \n" +
                "and c.cust_clinic =  '"+secUser.getOrgId()+"' \n" +
                "and c.deleted =  0 \n" +
                "and return_time is NOT NULL\n" +
                "order by dist_day\n" );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/noPlanList")
    public Result noPlanList(int page,int size){
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = daoService.getDivList("select cust_name,create_time from customer" +
                " where  deleted = 0 and cust_clinic = '"+secUser.getOrgId()+"' " +
                "and cust_id  not in(select cust_id from cust_return where deleted  = '0')" );
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }
}
