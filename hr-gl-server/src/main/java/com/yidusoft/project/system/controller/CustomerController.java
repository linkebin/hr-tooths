package com.yidusoft.project.system.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.Customer;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.CustomerService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.utils.Security;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String param) {
        Customer customer= JSON.parseObject(param, Customer.class);
        Map<String,Object> map=daoService.getDivMap("SELECT f_getCodes((select ORG_CODE from sec_org where ID_='" + customer.getCustClinic() + "')) as code");
        customer.setCustCode(map.get("code").toString());
        customer.setCustId(UUID.randomUUID().toString());
        customer.setCreateTime(new Date());
        customer.setDeleted(0);
        customer.setMoney(0.0);
        if(StringUtils.isNotBlank(customer.getDoctor())){
            customer.setEarlyDoctor(customer.getDoctor());
            customer.setEarlyClinic(customer.getCustClinic());
        }
        customerService.save(customer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        customerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String param) {
        Customer customer= JSON.parseObject(param,Customer.class);
        if(StringUtils.isNotBlank(customer.getDoctor()) && StringUtils.isEmpty(customer.getEarlyDoctor())){
            customer.setEarlyDoctor(customer.getDoctor());
            customer.setEarlyClinic(customer.getCustClinic());
        }
        customerService.updateCustomer(customer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        Customer customer = customerService.findById(id);
        return ResultGenerator.genSuccessResult(customer);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Customer> list = customerService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/lists")
    public Result lists() {
        SecUser secUser= Security.getUser();
        List<Customer> list = daoService.getbyList("select c.* from customer c where c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='122' ORDER BY c.create_time desc",Customer.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/lista")
    public Result lista(String str,String clinic) {
        List<Map<String,Object>> list = customerService.findCustomer(clinic,str);
        return ResultGenerator.genSuccessResult(list);
    }

    //模糊查询
    @PostMapping("/getByid")
    public Result getByid(String str) {
        SecUser secUser= Security.getUser();
        List<Customer> list = daoService.getbyList("select * from customer c where c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='122' and c.cust_code like '%"+str+"%'\n" +
                "or c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='122' and c.cust_tell like '%"+str+"%'\n" +
                "or c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='122' and c.cust_name like '%"+str+"%'",Customer.class);
        return ResultGenerator.genSuccessResult(list);
    }


    @GetMapping("/exportCust")
    public void excelCust(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("客户列表");
        List<String> titles = new ArrayList<>();
        titles.add("病历号");
        titles.add("姓名");
        titles.add("性别");
        titles.add("年龄");
        titles.add("联系电话");
        titles.add("联系邮箱");
        titles.add("QQ");
        titles.add("微信");
        titles.add("洁牙习惯");
        titles.add("就诊经历");
        titles.add("过敏史");
        titles.add("客户来源");
        titles.add("初诊诊所");
        titles.add("医生");
        SecUser secUser=Security.getUser();

        data.setTitles(titles);
        List<Map<String,Object>> list =daoService.getDivList("select c.*,o.ORG_NAME,u.USER_NAME,(select b.bl_time from cust_bl b where b.cust_id=c.cust_id ORDER BY b.bl_time LIMIT 1) as bl_time from customer c,sec_org o,sec_user u where c.cust_clinic=o.ID_ and c.doctor=u.ID_ ");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("cust_code"));
            row.add(t.get("cust_name"));
            row.add(t.get("cust_sex"));
            row.add(t.get("cust_age"));
            row.add(t.get("cust_tell"));
            row.add(t.get("cust_mail"));
            row.add(t.get("cust_qq"));
            row.add(t.get("cust_wechat"));
            row.add(t.get("cust_jyxg"));
            row.add(t.get("cust_jzjl"));
            row.add(t.get("cust_gms"));
            row.add(t.get("cust_source"));
            row.add(t.get("ORG_NAME"));
            row.add(t.get("USER_NAME"));
            rows.add(row);
        });

        data.setRows(rows);

        String fileName="客户列表.xls";
        ExcelUtils.exportExcel(response, fileName, data);
    }

    //获取时间
    @PostMapping("/getdate")
    public String getdate() {
        return new Date().getTime() + "";
    }
}
