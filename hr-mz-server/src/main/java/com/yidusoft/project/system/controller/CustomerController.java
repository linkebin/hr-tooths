package com.yidusoft.project.system.controller;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.AccountRecord;
import com.yidusoft.project.system.domain.Customer;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.YyManage;
import com.yidusoft.project.system.service.AccountRecordService;
import com.yidusoft.project.system.service.CustomerService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.YyManageService;
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
    @Resource
    private AccountRecordService accountRecordService;
    @Resource
    private YyManageService yyManageService;

    @PostMapping("/add")
    public Result add(String param) {
        SecUser secUser= Security.getUser();
        Map<String,Object> map=daoService.getDivMap("SELECT f_getCodes((select ORG_CODE from sec_org where ID_='" + secUser.getOrgId() + "')) as code");
        Customer customer= JSON.parseObject(param, Customer.class);
        customer.setCustCode(map.get("code").toString());
        customer.setCustId(UUID.randomUUID().toString());
        customer.setCreateTime(new Date());
        customer.setDeleted(0);
        customer.setCustClinic(secUser.getOrgId());
        if(StringUtils.isNotBlank(customer.getDoctor())){
            customer.setEarlyDoctor(customer.getDoctor());
            customer.setEarlyClinic(secUser.getOrgId());
        }
        customer.setMoney(0.0);
        customerService.save(customer);
        return ResultGenerator.genSuccessResult(customer.getCustId());
    }

    @PostMapping("/adds")
    public Result adds(String name,String id) {
        SecUser secUser= Security.getUser();
        Map<String,Object> map=daoService.getDivMap("SELECT f_getCodes((select ORG_CODE from sec_org where ID_='" + secUser.getOrgId() + "')) as code");
        Customer customer= new Customer();
        customer.setCustCode(map.get("code").toString());
        customer.setCustId(UUID.randomUUID().toString());
        customer.setCustName(name);
        customer.setCreateTime(new Date());
        customer.setDeleted(0);
        customer.setCustClinic(secUser.getOrgId());
        YyManage yy = yyManageService.findById(id);
        customer.setEarlyDoctor(yy.getYyDoctor());
        customer.setDoctor(yy.getYyDoctor());
        customer.setEarlyClinic(secUser.getOrgId());
        customer.setMoney(0.0);
        customer.setCustTell(yy.getYyCustTel());
        customer.setCustRemark(yy.getRemark());
        customer.setCustSex(yy.getYyCustSex());
        if(yy.getYyAge()!=null){
            customer.setCustAge(yy.getYyAge().toString());
        }
        daoService.getDivMap("update yy_manage y set y.yy_cust_id='"+customer.getCustId()+"'  where y.yy_id='"+id+"'");
        customerService.save(customer);
        return ResultGenerator.genSuccessResult(customer.getCustId());
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
            SecUser secUser= Security.getUser();
            customer.setEarlyClinic(secUser.getOrgId());
        }
        customerService.updateCustomer(customer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        Customer customer = customerService.findById(id);
        return ResultGenerator.genSuccessResult(customer);
    }
    @PostMapping("/details")
    public Result details(@RequestParam String id) {
        Map<String,Object> map= daoService.getDivMap("select s.*,(select u.USER_NAME from sec_user u where u.ID_=s.doctor) as early_doctor_name,(SELECT o.ORG_NAME from sec_org o where o.ID_=s.cust_clinic) as early_clinic_name from customer s where s.cust_id='"+id+"'");
        return ResultGenerator.genSuccessResult(map);
    }

    @PostMapping("/updates")
    public Result updates(String id,double money) {
        SecUser secUser=Security.getUser();
        daoService.getDivMap("update customer c set c.money=c.money+"+money+" where c.cust_id='"+id+"'");
        Customer customer=customerService.findById(id);
        AccountRecord accountRecord=new AccountRecord();
        accountRecord.setRecordId(UUID.randomUUID().toString());
        accountRecord.setCreateTime(new Date());
        accountRecord.setCustId(id);
        accountRecord.setRecordMoney(money);
        accountRecord.setRecordType("充值");
        accountRecord.setUserId(secUser.getId());
        accountRecordService.save(accountRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Customer> list = customerService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/lista")
    public Result lista(String str) {
        SecUser secUser= Security.getUser();
        List<Map<String,Object>> list = daoService.getDivList("select c.*,o.ORG_NAME,(select u.USER_NAME from sec_user u where u.ID_=c.doctor) as USER_NAME,(SELECT l.level_name from cust_level l where l.level_id=c.cust_class) as level_name,(select b.bl_time from cust_bl b where b.cust_id=c.cust_id ORDER BY b.bl_time LIMIT 1) as bl_time,(select u.USER_NAME from sec_user u where u.ID_=c.early_doctor) as early_doctor_name,(select a.org_name from sec_org a where a.ID_ = c.early_clinic) as early_clinic_name from customer c,sec_org o where c.cust_clinic=o.ID_ and c.cust_name like '%"+str+"%' and o.ID_='"+secUser.getOrgId()+"'\n"+
        "or c.cust_clinic=o.ID_ and c.cust_tell like '%"+str+"%' and o.ID_='"+secUser.getOrgId()+"'");
        return ResultGenerator.genSuccessResult(list);
    }
    @PostMapping("/getAllNotDel")
    public Result getAllNotDel() {
        List<Map<String,Object>> list = daoService.getDivList("select * from customer c where c.deleted=0 and c.cust_clinic='"+Security.getUser().getOrgId()+"'");
        return ResultGenerator.genSuccessResult(list);
    }


    //模糊查询
    @PostMapping("/getByid")
    public Result getByid(String str,int page) {
        SecUser secUser= Security.getUser();
        Map<String,Object> map=new HashMap<>();
        List<Customer> list=new ArrayList<>();
        if(!secUser.getStandby1().equals("医生")){
            map = daoService.getDivMap("select count(*) as count from customer c where c.deleted=0 and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_code like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_tell like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_name like '%"+str+"%'");
            PageHelper.startPage(page, 5);
            list = daoService.getbyList("select * from customer c where c.deleted=0 and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_code like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_tell like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_name like '%"+str+"%' order by c.create_time desc",Customer.class);
        }else{
             map = daoService.getDivMap("select count(*) as count from customer c where c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_code like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_tell like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_name like '%"+str+"%'");
            PageHelper.startPage(page, 5);
            list = daoService.getbyList("select * from customer c where c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_code like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_tell like '%"+str+"%'\n" +
                    "or c.deleted=0 and c.doctor='"+secUser.getId()+"' and c.cust_clinic='"+secUser.getOrgId()+"' and c.cust_name like '%"+str+"%' order by c.create_time desc",Customer.class);
        }
        PageInfo pageInfo = new PageInfo(list);
        pageInfo.setTotal(Integer.parseInt(map.get("count").toString()));
        return ResultGenerator.genSuccessResult(pageInfo);
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
        List<Map<String,Object>> list =daoService.getDivList("select c.*,o.ORG_NAME,u.USER_NAME,(select b.bl_time from cust_bl b where b.cust_id=c.cust_id ORDER BY b.bl_time LIMIT 1) as bl_time from customer c,sec_org o,sec_user u where c.cust_clinic=o.ID_ and c.doctor=u.ID_ and o.ID_='"+secUser.getOrgId()+"'");
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
