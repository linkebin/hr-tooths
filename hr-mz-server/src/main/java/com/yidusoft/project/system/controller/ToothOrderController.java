package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.ToothOrder;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.ToothOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/tooth/order")
public class ToothOrderController {
    @Resource
    private ToothOrderService toothOrderService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public String add(String str) {
        ToothOrder toothOrder= JSON.parseObject(str,ToothOrder.class);
        toothOrder.setOrderId(UUID.randomUUID().toString());
        toothOrder.setOrderTime(new Date());
        toothOrder.setClinic(Security.getUser().getOrgId());
        toothOrder.setOrderState("已提交");
        toothOrder.setDeleted(0);
        toothOrderService.save(toothOrder);
        return toothOrder.getOrderId();
    }

    @PostMapping("/delete")
    public Result delete(String str) {
        ToothOrder toothOrder=JSON.parseObject(str,ToothOrder.class);
        toothOrder.setDeleted(1);
        toothOrderService.update(toothOrder);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String str) {
        ToothOrder toothOrder=JSON.parseObject(str,ToothOrder.class);
        toothOrderService.update(toothOrder);
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
    public Result lists(String ids,String code_zy,String dateTest2,String dd_state_zy) {
        String left="";
        String right="";
        if(dateTest2!=""&&dateTest2!=null){
            left=" and order_time>='"+dateTest2.substring(0,10)+"'";
            right=" and order_time<='"+dateTest2.substring(12,23)+"'";
        }
        List<ToothOrder> list = daoService.getbyList("select * from tooth_order where deleted=0 and cust_id='" + ids + "'and order_number like '%" + code_zy + "%'"+left+right+" and order_state LIKE '%" + dd_state_zy + "%' order by order_time desc", ToothOrder.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getOrederNumber")
    public Map getOrederNumber() {
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Map<String, Object> map = daoService.getDivMap("SELECT nextval('gongdan') xl");
        Map<String, Object> mapa=daoService.getDivMap("select o.ORG_NAME from sec_org o where o.ID_='"+Security.getUser().getOrgId()+"'");
        String str = map.get("xl").toString();
        Map<String, Object> maps=new HashMap<>();
        if(str.length()==1){
            str=sdf.format(date)+"000"+str;
        }else if (str.length()==2){
            str=sdf.format(date)+"00"+str;
        }else if (str.length()==3){
            str=sdf.format(date)+"0"+str;
        }else if (str.length()==4){
            str=sdf.format(date)+str;
        }
        maps.put("ddbh",str);
        maps.put("orgName",mapa.get("ORG_NAME").toString());
        return maps;
    }
}
