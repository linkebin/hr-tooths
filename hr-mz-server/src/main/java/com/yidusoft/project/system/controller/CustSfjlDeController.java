package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.CustSfjl;
import com.yidusoft.project.system.domain.CustSfjlDe;
import com.yidusoft.project.system.service.CustSfjlDeService;
import com.yidusoft.project.system.service.CustSfjlService;
import com.yidusoft.project.system.service.DaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/cust/sfjl/de")
public class CustSfjlDeController {
    @Resource
    private CustSfjlDeService custSfjlDeService;
    @Resource
    private CustSfjlService custSfjlService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String param) {
        CustSfjlDe custSfjlDe= JSON.parseObject(param,CustSfjlDe.class);
        custSfjlDe.setSfDeId(UUID.randomUUID().toString());
        custSfjlDeService.save(custSfjlDe);
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/edit")
      public Result edit(String dataList,String sfjlId,String money) {
        //删除原有的明细
        CustSfjl custSfjl = custSfjlService.findById(sfjlId);
        custSfjl.setSfMoney(money);
        custSfjl.setDeleted(0);
        custSfjl.setArrears(Double.valueOf(money));
        custSfjlService.update(custSfjl);
        daoService.getDivList("DELETE   FROM `cust_sfjl_de`  where sfjl_id = '"+sfjlId+"'");
        List list = JSON.parseObject(dataList,ArrayList.class);
        for (int i = 0; i < list.size(); i++) {
            String  o =  JSON.toJSONString(list.get(i));
            CustSfjlDe custSfjlDe=JSON.parseObject(o,CustSfjlDe.class);
            custSfjlDe.setSfDeId(UUID.randomUUID().toString());
            custSfjlDe.setSfjlId(sfjlId);
            custSfjlDe.setRemark(i+"");
            custSfjlDeService.save(custSfjlDe);
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        custSfjlDeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CustSfjlDe custSfjlDe) {
        custSfjlDeService.update(custSfjlDe);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        CustSfjlDe custSfjlDe = custSfjlDeService.findById(id);
        return ResultGenerator.genSuccessResult(custSfjlDe);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CustSfjlDe> list = custSfjlDeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getByIdlist")
    public Result getByIdlist(String ids) {
        List<CustSfjlDe> list = daoService.getbyList("select * from cust_sfjl_de d where d.sfjl_id='"+ids+"' order by remark",CustSfjlDe.class);
        return ResultGenerator.genSuccessResult(list);
    }
}
