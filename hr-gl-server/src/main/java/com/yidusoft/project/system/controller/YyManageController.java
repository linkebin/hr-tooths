package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.YyManage;
import com.yidusoft.project.system.service.CustSfjlService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.YyManageService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/yy/manage")
public class YyManageController {
    @Resource
    private YyManageService yyManageService;
	@Resource
	private CustSfjlService custSfjlService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String json) {
        SecUser secUser = Security.getUser();
        YyManage yyManage= JSON.parseObject(json,YyManage.class);
        yyManage.setYyId(UUID.randomUUID().toString());
        yyManage.setYyCode("D" + new Date().getTime());
        yyManage.setCreateTime(new Date());
        yyManageService.save(yyManage);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        yyManageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(YyManage yyManage) {
        yyManageService.update(yyManage);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        YyManage yyManage = yyManageService.findById(id);
        return ResultGenerator.genSuccessResult(yyManage);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<YyManage> list = yyManageService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @PostMapping("/lists")
    public Result lists(@RequestParam(defaultValue = "") String yyDate,@RequestParam(defaultValue = "") String yyCustName,@RequestParam(defaultValue = "") String yyDoctor,@RequestParam(defaultValue = "") String yyProject,String yy_cust_id) {
        Map<String,Object> map=new HashMap<>();
        map.put("yyDate",yyDate);
        map.put("yyCustName",yyCustName);
        map.put("yyCustTel",yyCustName);
        map.put("yyDoctor",yyDoctor);
        map.put("yyProject",yyProject);
        map.put("yy_cust_id",yy_cust_id);
        List<YyManage> list =yyManageService.getList(map);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getYYManage")
    public Result getYYManage(String clinic,String doctor, String code, String project, String startTime, String endTime, int size, int page) {
        List<YyManage> list = yyManageService.getYYManage(clinic,doctor,code,project,startTime,endTime,size,(page-1)*size);
        int count = yyManageService.getYYManageCount(clinic,doctor,code,project,startTime,endTime);
        return ResultGenerator.genSuccessResult(list).setTotal(count);
    }

    @PostMapping("/tanking")
    public Result tanking() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        String month = date.get(Calendar.MONTH)+1 <10 ? "0"+String.valueOf(date.get(Calendar.MONTH) + 1) : String.valueOf(date.get(Calendar.MONTH) + 1);
        int day = getCurrentMonthDay();
        List<Map<String,Object>> dayList = yyManageService.todayTanking();
        List<Map<String,Object>> monthList = yyManageService.monthTanking(year + "-" + month + "-" +"01",year + "-" + month + "-" +day);
        Map<String,Object> map = new HashMap<>();
        map.put("dayList",dayList);
        map.put("monthList",monthList);
        return ResultGenerator.genSuccessResult(map);
    }

	@PostMapping("/patientsSum")
	public Result patientsSum() {
		int yyCount = yyManageService.patients();
		int custCount= custSfjlService.patients();
		Map<String,Object> map = new HashMap<>();
		map.put("yyCount",yyCount);
		map.put("custCount",custCount);
		return ResultGenerator.genSuccessResult(map);
	}

    @PostMapping("/getTime")
    public Result getTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SecUser secUser=Security.getUser();
        Map<String,Object> yyManage=daoService.getDivMap("select count(*) as time1,(SELECT count(*) from yy_manage where yy_date='"+sdf.format(date)+"' and yy_clinic='122') as time2 from yy_manage y where y.create_time='"+sdf.format(date)+"'and y.yy_clinic='122'");
        return ResultGenerator.genSuccessResult(yyManage);
    }
    @PostMapping("/getTj")
    public Result getTj(){
        List<Map<String,Object>> list=daoService.getDivList("select DISTINCT u.USER_NAME,IFNULL(a.days,0) as days,IFNULL(b.weeks,0) as weeks,IFNULL(c.months,0) as months,IFNULL(d.montha,0) as montha,IFNULL(e.weeka,0) as weeka,IFNULL(f.daya,0) as daya\n" +
                "from  sec_user u \n" +
                "LEFT JOIN (\n" +
                "select count(*) as days,s1.USER_NAME from yy_manage y \n" +
                "LEFT JOIN customer o ON y.yy_cust_id=o.cust_id \n" +
                "LEFT JOIN sec_user s1 ON y.yy_doctor=s1.ID_ \n" +
                "where to_days(y.yy_date) = to_days(now()) \n" +
                "and y.yy_date>o.create_time \n" +
                "GROUP BY s1.USER_NAME) a on a.USER_NAME = u.USER_NAME\n" +
                "LEFT JOIN (\n" +
                "select count(*) as weeks,s1.USER_NAME from yy_manage y \n" +
                "LEFT JOIN customer o ON y.yy_cust_id=o.cust_id \n" +
                "LEFT JOIN sec_user s1 ON y.yy_doctor=s1.ID_ \n" +
                "where YEARWEEK(date_format(y.yy_date,'%Y-%m-%d')) = YEARWEEK(now())\n" +
                "and y.yy_date>o.create_time \n" +
                "GROUP BY s1.USER_NAME\n" +
                ")b  on b.USER_NAME = u.USER_NAME\n" +
                "LEFT JOIN (\n" +
                "select count(*) as months ,s1.USER_NAME from yy_manage y \n" +
                "LEFT JOIN customer o ON y.yy_cust_id=o.cust_id \n" +
                "LEFT JOIN sec_user s1 ON y.yy_doctor=s1.ID_ \n" +
                "where DATE_FORMAT( y.yy_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m' )\n" +
                "and y.yy_date>o.create_time\n" +
                " GROUP BY s1.USER_NAME\n" +
                ") c on c.USER_NAME = u.USER_NAME\n" +
                "LEFT JOIN(\n" +
                "select count(*) as montha ,s1.USER_NAME from  customer c\n" +
                "LEFT JOIN sec_user s1 ON c.early_doctor=s1.ID_ \n" +
                "where DATE_FORMAT( c.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m')\n" +
                "GROUP BY s1.USER_NAME\n" +
                ")d on d.USER_NAME = u.USER_NAME\n" +
                "LEFT JOIN(\n" +
                "select count(*) as weeka ,s1.USER_NAME from  customer c\n" +
                "LEFT JOIN sec_user s1 ON c.early_doctor=s1.ID_ \n" +
                "where YEARWEEK(date_format(c.create_time,'%Y-%m-%d')) = YEARWEEK(now())\n" +
                "GROUP BY s1.USER_NAME\n" +
                ")e on e.USER_NAME = u.USER_NAME\n" +
                "LEFT JOIN(\n" +
                "select count(*) as daya ,s1.USER_NAME from  customer c\n" +
                "LEFT JOIN sec_user s1 ON c.early_doctor=s1.ID_ \n" +
                "where to_days(c.create_time) = to_days(now()) \n" +
                "GROUP BY s1.USER_NAME\n" +
                ")f on f.USER_NAME = u.USER_NAME\n" +
                "where u.STANDBY1='医生' and u.DELETED=0 and u.STATUS='启用' ORDER BY montha desc\n\n");
        return ResultGenerator.genSuccessResult(list);
    }
    private static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
