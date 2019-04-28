package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.AccountRecord;
import com.yidusoft.project.system.domain.CustSfjl;
import com.yidusoft.project.system.domain.Customer;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.AccountRecordService;
import com.yidusoft.project.system.service.CustSfjlService;
import com.yidusoft.project.system.service.CustomerService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.utils.DateUtils;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by CodeGenerator on 2018/11/13.
 */
@RestController
@RequestMapping("/cust/sfjl")
public class CustSfjlController {
    @Resource
    private CustSfjlService custSfjlService;
    @Resource
    private DaoService daoService;
    @Resource
    private CustomerService customerService;
    @Resource
    private AccountRecordService accountRecordService;

    @PostMapping("/add")
    public Result add(String custid, String custname, String money,String jzTime) {
        Customer customer = daoService.getObject("select * from customer where cust_id='"+custid+"'",Customer.class);
        if(StringUtils.isBlank(customer.getDoctor())){
            return ResultGenerator.genFailResult("该客户分配未医生就诊，无法创建订单");
        }
        SecUser secUser=daoService.getObject("select u.* from customer o,sec_user u where o.doctor=u.iD_ and o.cust_id='"+custid+"'",SecUser.class);
        CustSfjl custSfjl = new CustSfjl();
        custSfjl.setSfId(UUID.randomUUID().toString());
        custSfjl.setCustId(custid);
        custSfjl.setCreateTime(DateUtils.parse(jzTime));
        custSfjl.setCustName(custname);
        custSfjl.setDoctor(secUser.getUserName());
        custSfjl.setDoctorId(secUser.getId());
        custSfjl.setSfStat("未收费");
        custSfjl.setSfMoney(money);
        custSfjl.setDeleted(0);
        custSfjl.setArrears(Double.valueOf(money));
        custSfjlService.save(custSfjl);
        return ResultGenerator.genSuccessResult(custSfjl.getSfId());
    }

    @PostMapping("/delete")
    public Result delete(String str) {
        CustSfjl custSfjl = JSON.parseObject(str, CustSfjl.class);
        custSfjl.setDeleted(1);
        custSfjlService.update(custSfjl);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
     public Result update(String id,String type,double money,String bank) {
        Date date=new Date();
        SecUser secUser=Security.getUser();
        CustSfjl custSfjl = custSfjlService.findById(id);
        BigDecimal bg = new BigDecimal((custSfjl.getArrears() - money));
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if(f1 == 0){
            custSfjl.setSfStat("已收费");
            custSfjl.setCashier(secUser.getId());
            custSfjl.setSfTime(new Date());
        }

        custSfjl.setArrears(f1);
        custSfjlService.update(custSfjl);
        AccountRecord accountRecord=new AccountRecord();
        accountRecord.setRecordId(UUID.randomUUID().toString());
        accountRecord.setCreateTime(new Date());
        accountRecord.setCustId(custSfjl.getCustId());
        accountRecord.setSfId(id);
        accountRecord.setRecordMoney(money);
        accountRecord.setRecordType(type);
        accountRecord.setBank(bank);
        accountRecord.setUserId(secUser.getId());
        accountRecordService.save(accountRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updates")
    public Result updates(String id,String ids,double money) {
        //实付金额
        double reMoney = 0.0;
        SecUser secUser=Security.getUser();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Customer customer=customerService.findById(ids);
        if(customer.getMoney() == 0){
            return ResultGenerator.genFailResult("余额不足，请及时充值！");
        }
        CustSfjl custSfjl = custSfjlService.findById(id);
        //账户余额小于未付
        if(custSfjl.getArrears() > customer.getMoney()){
            reMoney = customer.getMoney();
            BigDecimal bg = new BigDecimal(custSfjl.getArrears() - customer.getMoney());
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            custSfjl.setArrears(f1);
            customer.setMoney(0.0);
        }else{
            reMoney = custSfjl.getArrears();
            custSfjl.setArrears(0.0);
            custSfjl.setSfStat("已收费");
            custSfjl.setSfTime(new Date());
            custSfjl.setCashier(secUser.getId());
            BigDecimal bg = new BigDecimal(customer.getMoney() - reMoney);
            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            customer.setMoney(f1);
        }
        custSfjlService.update(custSfjl);
        customerService.update(customer);
        AccountRecord accountRecord=new AccountRecord();
        accountRecord.setRecordId(UUID.randomUUID().toString());
        accountRecord.setCreateTime(new Date());
        accountRecord.setCustId(ids);
        accountRecord.setSfId(id);
        accountRecord.setRecordMoney(reMoney);
        accountRecord.setRecordType("余额");
        accountRecord.setUserId(secUser.getId());
        accountRecordService.save(accountRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        CustSfjl custSfjl = custSfjlService.findById(id);
        return ResultGenerator.genSuccessResult(custSfjl);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CustSfjl> list = custSfjlService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/lists")
    public Result lists(String ids, String sf_time) {
        String flag="";
        if(!sf_time.equals(null)&&!sf_time.equals("")){
            flag=" and date_format(s.create_time,'%Y-%m-%d')='"+sf_time+"'";
        }
        List<Map<String,Object>> list = daoService.getDivList("select DISTINCT t.*,b.user_name,IF(temp.record_money is null ,\"未收\",\"已收\") as sfzt from (\n" +
                "    select s.*,c.cust_sex  from cust_sfjl s,customer c\n" +
                "    where s.cust_id=c.cust_id\n" +
                "    and s.deleted=0\n" +
                "    and s.cust_id='"+ids+"'" + flag+ ") t\n" +
                "    left join sec_user b on t.cashier = b.ID_\n" +
                "    LEFT JOIN account_record temp  on temp.sf_id = t.sf_id\n" +
                "    order by t.create_time desc");
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/lista")
    public Result lista(String sf_name) {
        SecUser secUser=Security.getUser();
        List<Map<String,Object>> list = daoService.getDivList("select s.*,(select u.USER_NAME from sec_user u where u.ID_=s.cashier) as USER_NAME from cust_sfjl s,customer c where s.sf_stat='未收费' and s.cust_id=c.cust_id and c.cust_clinic='" + secUser.getOrgId() + "' and s.cust_name like '%"+sf_name+"%' and s.deleted=0 order by s.create_time desc");
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/findCustSfjl")
    public Result findCustSfjl(String doctor,String starTime,String endTime,String type,int page,int size) {
        SecUser secUser = Security.getUser();
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String  month = String.format("%02d", date.get(Calendar.MONTH)+1);
        int day = getCurrentMonthDay();
        if("本年".equals(type)){
            starTime = year + "-01-01";
            endTime = year + "-12-31";
        }else if("本月".equals(type)){
            starTime = year + "-" + month + "-" +"01";
            endTime = year + "-" + month + "-" +day;
        }
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list = custSfjlService.findCustSfjl(secUser.getOrgId(),doctor,starTime,endTime);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/findCustSfjlSum")
    public Result findCustSfjlSum(String doctor,String starTime,String endTime,String type) {
        SecUser secUser = Security.getUser();
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String  month = String.format("%02d", date.get(Calendar.MONTH)+1);
        int day = getCurrentMonthDay();
        if("本年".equals(type)){
            starTime = year + "-01-01";
            endTime = year + "-12-31";
        }else if("本月".equals(type)){
            starTime = year + "-" + month + "-" +"01";
            endTime = year + "-" + month + "-" +day;
        }
        Map<String,Object> sum = custSfjlService.findCustSfjlSum(secUser.getOrgId(),doctor,starTime,endTime);
        Map<String,Object> costSum = custSfjlService.findCustSfjlCostSum(secUser.getOrgId(),doctor,starTime,endTime);
        sum.put("costCount",costSum.get("count"));
        sum.put("costMoney",costSum.get("money"));
        return ResultGenerator.genSuccessResult(sum);
    }

    @PostMapping("/findProjectRevenue")
    public Result findProjectRevenue(String starTime,String endTime,int page,int size) {
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list = custSfjlService.findProjectRevenue(secUser.getOrgId(),starTime,endTime);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/findDrugRevenue")
    public Result findDrugRevenue(String starTime,String endTime,int page,int size) {
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page,size);
        List<Map<String,Object>> list = custSfjlService.findDrugRevenue(secUser.getOrgId(),starTime,endTime);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/findRevenueSum")
    public Result findRevenueSum(String starTime,String endTime) {
        SecUser secUser = Security.getUser();
        int drugSum =  custSfjlService.findDrugRevenueSum(secUser.getOrgId(),starTime,endTime);
        int projectSum = custSfjlService.findProjectRevenueSum(secUser.getOrgId(),starTime,endTime);
        Map<String,Object> map = new HashMap<>();
        map.put("drugSum",drugSum);
        map.put("projectSum",projectSum);
        return ResultGenerator.genSuccessResult(map);
    }

    private static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    @GetMapping("/exportCustSfjil")
    public void excelCustSfjil(HttpServletResponse response) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("财务结算");
        List<String> titles = new ArrayList<>();
        titles.add("状态");
        titles.add("收费时间");
        titles.add("账单时间");
        titles.add("客户姓名");
        titles.add("订单金额");
        titles.add("未付金额");
        titles.add("已付金额");
        titles.add("医生");
        titles.add("收款员");
        titles.add("备注");
        data.setTitles(titles);

        SecUser secUser = Security.getUser();
        List<Map<String,Object>> list = custSfjlService.findCustSfjl(secUser.getOrgId(),"","","");
        List<List<Object>> rows = new ArrayList<>();
        list.forEach(t->{
            List<Object> row = new ArrayList<>();
            row.add(t.get("sf_stat"));
            row.add(t.get("create_time"));
            row.add(t.get("sf_time"));
            row.add(t.get("cust_name"));
            row.add(t.get("sf_money"));
            row.add(t.get("arrears"));
            row.add(Double.valueOf(t.get("sf_money").toString())-Double.valueOf(t.get("arrears").toString()));
            row.add(t.get("doctor"));
            row.add(t.get("username"));
            row.add(t.get("remark"));
            rows.add(row);
        });

        data.setRows(rows);

        String fileName="财务结算.xls";
        ExcelUtils.exportExcel(response,fileName,data);
    }

    @PostMapping("/getYf")
    public Result getYf(String  sfId,String  createTime) throws ParseException {
        long lt = new Long(createTime);
        Date date = new Date(lt);
        Map<String, Object> divMap = daoService.getDivMap("SELECT sum(record_money) as money FROM `account_record` WHERE sf_id = '"+sfId+"' and UNIX_TIMESTAMP(account_record.create_time) <= "+lt/1000);
        return ResultGenerator.genSuccessResult(divMap);
    }
}
