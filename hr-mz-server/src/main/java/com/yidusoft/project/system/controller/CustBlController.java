package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.CustBl;
import com.yidusoft.project.system.domain.Customer;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.CustBlService;
import com.yidusoft.project.system.service.CustomerService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/cust/bl")
public class CustBlController {
    @Resource
    private CustBlService custBlService;
    @Resource
    private CustomerService customerService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String param) {
        SecUser secUser= Security.getUser();
        CustBl custBl= JSON.parseObject(param,CustBl.class);
        if(StringUtils.isBlank(custBl.getDoctor())){
            return ResultGenerator.genFailResult("该客户未选择接诊医生，无法生成病例");
        }
        Customer customer = daoService.getObject("select * from customer where cust_id='"+custBl.getCustId()+"'",Customer.class);
        if(StringUtils.isBlank(customer.getEarlyClinic())){
            customer.setEarlyClinic(secUser.getOrgId());
            customer.setEarlyDoctor(custBl.getDoctor());
            customerService.save(customer);
        }
        custBl.setBlId(UUID.randomUUID().toString());
        custBl.setDeleted(0);
        custBl.setAddUser(secUser.getId());
        custBlService.save(custBl);
        return ResultGenerator.genSuccessResult(custBl.getBlId());
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        daoService.getDivMap("update cust_bl set deleted=1 where bl_id='"+id+"'");
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String param) {
        CustBl custBl=JSON.parseObject(param,CustBl.class);
        custBlService.update(custBl);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        CustBl custBl = custBlService.findById(id);
        return ResultGenerator.genSuccessResult(custBl);
    }

    @PostMapping("/detail/dy")
    public Result dyDetail(@RequestParam String id) {
        Map<String, Object> divMap = daoService.getDivMap("SELECT cb.*,so.org_name,c.cust_name,c.cust_addr,c.cust_age,c.cust_sex,c.cust_tell,su.USER_NAME as doctor_name FROM cust_bl cb\n" +
                "left join customer c on  cb.cust_id =c.cust_id\n" +
                "LEFT JOIN sec_user  su on cb.doctor = su.ID_\n" +
                "LEFT JOIN sec_org  so on c.cust_clinic = so.ID_\n" +
                "where bl_id='"+id+"'");
        return ResultGenerator.genSuccessResult(divMap);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CustBl> list = custBlService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getByCustid")
    public Result getByCustid(String id) {
        List<Map<String,Object>> list =daoService.getDivList("select *,(select USER_NAME from sec_user u where u.ID_=c.doctor) as USER_NAME from cust_bl c where c.deleted=0 and c.cust_id='"+id+"' ORDER BY c.bl_time");
        return ResultGenerator.genSuccessResult(list);
    }
}
