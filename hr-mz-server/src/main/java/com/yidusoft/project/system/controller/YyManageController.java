package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.domain.YyManage;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.YyManageService;
import com.yidusoft.utils.CodeHelper;
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
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String json) {
        SecUser secUser = Security.getUser();
        YyManage yyManage= JSON.parseObject(json,YyManage.class);
        yyManage.setYyId(UUID.randomUUID().toString());
        yyManage.setYyCode(CodeHelper.getCode("YY"));
        yyManage.setYyClinic(secUser.getOrgId());
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
        SecUser secUser=Security.getUser();
        Map<String,Object> map=new HashMap<>();
        map.put("yyDate",yyDate);
        map.put("yyCustName",yyCustName);
        map.put("yyCustTel",yyCustName);
        map.put("yyProject",yyProject);
        map.put("yyClinic",secUser.getOrgId());
        map.put("yy_cust_id",yy_cust_id);
        if(!secUser.getStandby1().equals("医生")){
            map.put("yyDoctor",null);
        }else {
            map.put("yyDoctor",yyDoctor);
        }
        List<YyManage> list =yyManageService.getList(map);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getYYManage")
    public Result getYYManage(String doctor, String code, String project, String startTime, String endTime, int size, int page) {
        SecUser secUser = Security.getUser();
        PageHelper.startPage(page, size);
        List<YyManage> list = yyManageService.getYYManage(secUser.getOrgId(),doctor,code,project,startTime,endTime,size,(page-1)*size);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/getTime")
    public Result getTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SecUser secUser=Security.getUser();
        Map<String,Object> yyManage=daoService.getDivMap("select count(*) as time1,(SELECT count(*) from yy_manage where date_format(yy_date,'%Y-%m-%d')='"+sdf.format(date)+"' and yy_clinic='"+secUser.getOrgId()+"') as time2 from yy_manage y where date_format(y.create_time,'%Y-%m-%d')='"+sdf.format(date)+"'and y.yy_clinic='"+secUser.getOrgId()+"'");
        return ResultGenerator.genSuccessResult(yyManage);
    }

    @PostMapping("/getTj")
    public Result getTj(){
        SecUser user=Security.getUser();
        List<Map<String,Object>> list=daoService.getDivList("select u.USER_NAME,if(a.days is null,0,a.days) as days,if(b.week is null,0,b.week) as weeks,if(c.months is null,0,c.months) as months,\n" +
                "if(q.days is null,0,q.days) as daya,if(w.week is null,0,w.week) as weeka,if(e.months is null,0,e.months) as montha from\n" +
                "sec_user u left JOIN\n" +
                "(select count(*) as days,y.yy_doctor from yy_manage y LEFT JOIN customer o ON y.yy_cust_id=o.cust_id where\n" +
                "to_days(y.yy_date) = to_days(now())\n" +
                "and y.yy_clinic='"+user.getOrgId()+"' and y.yy_date>o.create_time GROUP BY y.yy_doctor) a\n" +
                "on u.ID_=a.yy_doctor\n" +
                "LEFT JOIN\n" +
                "(select count(*) as week,y.yy_doctor from yy_manage y LEFT JOIN customer o ON y.yy_cust_id=o.cust_id where\n" +
                "YEARWEEK(date_format(y.yy_date,'%Y-%m-%d')) = YEARWEEK(now())\n" +
                "and y.yy_clinic='"+user.getOrgId()+"' and y.yy_date>o.create_time GROUP BY y.yy_doctor)b \n" +
                "on u.ID_=b.yy_doctor\n" +
                "LEFT JOIN\n" +
                "(select count(*) as months,y.yy_doctor from yy_manage y LEFT JOIN customer o ON y.yy_cust_id=o.cust_id where\n" +
                "DATE_FORMAT( y.yy_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m' ) \n" +
                "and y.yy_clinic='"+user.getOrgId()+"' and y.yy_date>o.create_time GROUP BY y.yy_doctor) c \n" +
                "on u.ID_=c.yy_doctor\n" +
                "LEFT JOIN\n" +
                "(select count(*) as days,y.yy_doctor from yy_manage y LEFT JOIN customer o ON y.yy_cust_id=o.cust_id where\n" +
                "to_days(y.yy_date) = to_days(now())\n" +
                "and y.yy_clinic='"+user.getOrgId()+"' and y.yy_date<o.create_time or to_days(y.yy_date) = to_days(now()) and y.yy_clinic='"+user.getOrgId()+"' and y.yy_cust_id is null GROUP BY y.yy_doctor) q\n" +
                "on u.ID_=q.yy_doctor\n" +
                "LEFT JOIN\n" +
                "(select count(*) as week,y.yy_doctor from yy_manage y LEFT JOIN customer o ON y.yy_cust_id=o.cust_id where\n" +
                "YEARWEEK(date_format(y.yy_date,'%Y-%m-%d')) = YEARWEEK(now())\n" +
                "and y.yy_clinic='"+user.getOrgId()+"' and y.yy_date<o.create_time or YEARWEEK(date_format(y.yy_date,'%Y-%m-%d')) = YEARWEEK(now()) and y.yy_clinic='"+user.getOrgId()+"' and y.yy_cust_id is null GROUP BY y.yy_doctor)w\n" +
                "on u.ID_=w.yy_doctor\n" +
                "LEFT JOIN\n" +
                "(select count(*) as months,y.yy_doctor from yy_manage y LEFT JOIN customer o ON y.yy_cust_id=o.cust_id where\n" +
                "DATE_FORMAT( y.yy_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m' ) \n" +
                "and y.yy_clinic='"+user.getOrgId()+"' and y.yy_date<o.create_time or DATE_FORMAT( y.yy_date, '%Y%m' ) = DATE_FORMAT( CURDATE( ) ,'%Y%m' ) and y.yy_clinic='"+user.getOrgId()+"' and y.yy_cust_id is null GROUP BY y.yy_doctor) e\n" +
                "on u.ID_=e.yy_doctor\n" +
                "where u.ORG_ID='"+user.getOrgId()+"'\n" +
                "and u.STANDBY1='医生' and u.DELETED=0 and u.STATUS='启用';\n");
        return ResultGenerator.genSuccessResult(list);
    }
}
