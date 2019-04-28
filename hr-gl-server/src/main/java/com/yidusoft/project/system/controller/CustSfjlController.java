package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.poi.ExcelData;
import com.yidusoft.poi.ExcelUtils;
import com.yidusoft.project.system.domain.CustSfjl;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.CustSfjlService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/add")
    public String add(String custid, String custname, String doctor, String doctorid, String money) {
        SecUser secUser=Security.getUser();
        CustSfjl custSfjl = new CustSfjl();
        custSfjl.setSfId(UUID.randomUUID().toString());
        custSfjl.setCustId(custid);
        custSfjl.setCreateTime(new Date());
        custSfjl.setCustName(custname);
        custSfjl.setDoctor(doctor);
        custSfjl.setDoctorId(doctorid);
        custSfjl.setSfStat("未收费");
        custSfjl.setSfMoney(money);
        custSfjl.setDeleted(0);
        custSfjl.setCashier(secUser.getId());
        custSfjlService.save(custSfjl);
        return custSfjl.getSfId();
    }

    @PostMapping("/delete")
    public Result delete(String str) {
        CustSfjl custSfjl = JSON.parseObject(str, CustSfjl.class);
        custSfjl.setDeleted(1);
        custSfjlService.update(custSfjl);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String id) {
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        daoService.getDivMap("update cust_sfjl c set c.sf_stat='已收费',c.sf_time='" + sdf.format(date) + "' where c.sf_id='" + id + "'");
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
    public Result lists(String ids, String sf_name) {
        List<CustSfjl> list = daoService.getbyList("select * from cust_sfjl s where s.cust_id='" + ids + "' and s.cust_name like '%"+sf_name+"%' and s.deleted=0", CustSfjl.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/lista")
    public Result lista(String sf_name) {
        SecUser secUser=Security.getUser();
        List<CustSfjl> list = daoService.getbyList("select * from cust_sfjl s,customer c where s.sf_stat='未收费' and s.cust_id=c.cust_id and c.cust_clinic='122' and s.cust_name like '%"+sf_name+"%' and s.deleted=0", CustSfjl.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/findCustSfjl")
    public Result findCustSfjl(String clinic,String doctor,String starTime,String endTime,String type,int page,int size) {
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
        List<Map<String,Object>> list = custSfjlService.findCustSfjl(clinic,doctor,starTime,endTime);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/findCustSfjlSum")
    public Result findCustSfjlSum(String clinic,String doctor,String starTime,String endTime,String type) {
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
        Map<String,Object> sum = custSfjlService.findCustSfjlSum(clinic,doctor,starTime,endTime);
        Map<String,Object> costSum = custSfjlService.findCustSfjlCostSum(clinic,doctor,starTime,endTime);
        sum.put("costCount",costSum.get("count"));
        sum.put("costMoney",costSum.get("money"));
        return ResultGenerator.genSuccessResult(sum);
    }

    @PostMapping("/findProjectRevenue")
    public Result findProjectRevenue(String clinic,String starTime,String endTime,int page,int size) {

        PageHelper.startPage(page,size);
        List<Map<String,Object>> list = custSfjlService.findProjectRevenue(clinic,starTime,endTime);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/findDrugRevenue")
    public Result findDrugRevenue(String clinic,String starTime,String endTime,int page,int size) {

        PageHelper.startPage(page,size);
        List<Map<String,Object>> list = custSfjlService.findDrugRevenue(clinic,starTime,endTime);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/findRevenueSum")
    public Result findRevenueSum(String clinic,String starTime,String endTime) {

        int drugSum =  custSfjlService.findDrugRevenueSum(clinic,starTime,endTime);
        int projectSum = custSfjlService.findProjectRevenueSum(clinic,starTime,endTime);
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

        List<Map<String,Object>> list = custSfjlService.findCustSfjl("","","","");
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

    @PostMapping("/findSum")
    public Result findSum() {
        Map<String, Object> map = daoService.getDivMap("SELECT a.*,b.* from (SELECT SUM(sf_money) as monDM,SUM(sf_money)-SUM(arrears) as monSM from cust_sfjl \n" +
                "where DATE_FORMAT( create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m')\n" +
                "and deleted = 0)a,(\n" +
                "SELECT IFNULL(SUM(sf_money),0) as dayDM,IFNULL(SUM(sf_money)-SUM(arrears),0) as daySM from cust_sfjl \n" +
                "where  to_days(now())-to_days(create_time) = 1\n" +
                "and deleted = 0)b");
        return ResultGenerator.genSuccessResult(map);
    }

}
