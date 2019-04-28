package com.yidusoft.project.system.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.ToothOrder;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.ToothOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by CodeGenerator on 2018/12/18.
 */
@RestController
@RequestMapping("/tooth/order")
public class ToothOrderController {
    @Resource
    private ToothOrderService toothOrderService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(ToothOrder toothOrder) {
        toothOrderService.save(toothOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        toothOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String str,String id) {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        if(str.equals("已接单")){
            daoService.getDivMap("update tooth_order t set t.order_state='已接单',t.finish_time='"+sdf.format(date)+"' where t.order_id='"+id+"'");
        }else if(str.equals("已完成")){
            daoService.getDivMap("update tooth_order t set t.order_state='已完成',t.mail_time='"+sdf.format(date)+"' where t.order_id='"+id+"'");
        }else{
            daoService.getDivMap("update tooth_order t set t.order_state='未提交' where t.order_id='"+id+"'");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        ToothOrder toothOrder = toothOrderService.findById(id);
        return ResultGenerator.genSuccessResult(toothOrder);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ToothOrder> list = toothOrderService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/lists")
    public Result lists(String order_number,String order_time,String order_times,String str) {
        Map<String,Object> map=new HashMap<>();
        map.put("order_number",order_number);
        map.put("order_time",order_time);
        map.put("order_times",order_times);
        map.put("clinic",str);
        List<Map<String,Object>> list=toothOrderService.getselAll(map);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/lista")
    public Result lista(String str) {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date tomorrow = c.getTime();
        if(str==null){
            List<Map<String,Object>> list = daoService.getDivList("select t.*,u.USER_NAME,o.ORG_NAME from tooth_order t,sec_user u,sec_org o where t.order_state!='未提交' and t.deleted=0 and t.clinic=o.ID_ and t.doctor=u.ID_ and t.order_time>='" + sdf.format(date) + "' and t.order_time<'" + sdf.format(tomorrow) + "'");
            return ResultGenerator.genSuccessResult(list);
        }
        List<Map<String,Object>> list = daoService.getDivList("select t.*,u.USER_NAME,o.ORG_NAME from tooth_order t,sec_user u,sec_org o where t.order_state!='未提交' and t.deleted=0 and t.clinic=o.ID_ and t.doctor=u.ID_ and t.clinic='" + str + "' and t.order_time>='" + sdf.format(date) + "' and t.order_time<'" + sdf.format(tomorrow) + "'");
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/listb")
    public Result listb() {
        List<Map<String,Object>> list = daoService.getDivList("select * from (select count(1) as  count,o.ORG_NAME from tooth_order t,sec_org o where t.clinic=o.ID_ and t.order_state='已完成' GROUP BY t.clinic) as a ORDER BY count desc");
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getCount")
    public Result getCount() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        Map<String,Object> map = daoService.getDivMap("select (SELECT count(*) as count FROM tooth_order WHERE deleted=0 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( mail_time, '%Y%m' ) ) =-5) as one\n" +
                ",(SELECT count(*) as count FROM tooth_order WHERE deleted=0 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( mail_time, '%Y%m' ) ) =-4) as two\n" +
                ",(SELECT count(*) as count FROM tooth_order WHERE deleted=0 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( mail_time, '%Y%m' ) ) =-3) as three\n" +
                ",(SELECT count(*) as count FROM tooth_order WHERE deleted=0 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( mail_time, '%Y%m' ) ) =-2) as four\n" +
                ",(SELECT count(*) as count FROM tooth_order WHERE deleted=0 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( mail_time, '%Y%m' ) ) =-1) as five\n" +
                ",(SELECT count(*) as count FROM tooth_order WHERE deleted=0 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( mail_time, '%Y%m' ) ) =0) as six");
        map.put("me",sdf.format(date));
        return ResultGenerator.genSuccessResult(map);
    }
}
