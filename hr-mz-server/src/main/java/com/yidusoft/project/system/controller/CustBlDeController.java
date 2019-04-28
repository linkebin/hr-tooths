package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.CustBlDe;
import com.yidusoft.project.system.service.CustBlDeService;
import com.yidusoft.project.system.service.DaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Created by CodeGenerator on 2018/11/13.
 */
@RestController
@RequestMapping("/cust/bl/de")
public class CustBlDeController {
    @Resource
    private CustBlDeService custBlDeService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String param) {
        List<CustBlDe> custBlDes =JSON.parseArray(param, CustBlDe.class);
        if(custBlDes!=null && custBlDes.size()>0){
            //先做删除操作
            deld(custBlDes.get(0).getBlId());

            for (int i = 0; i < custBlDes.size(); i++) {
                CustBlDe custBlDe = custBlDes.get(i);
                if (custBlDe != null) {
                    custBlDe.setBlDeId(UUID.randomUUID().toString());
                    custBlDeService.save(custBlDe);
                } else {
                    return ResultGenerator.genFailResult("保存失败");
                }
            }
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("传入的病历详情为空，保存失败");
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        custBlDeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String param) {
        CustBlDe custBlDe = JSON.parseObject(param, CustBlDe.class);
        custBlDeService.update(custBlDe);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        CustBlDe custBlDe = custBlDeService.findById(id);
        return ResultGenerator.genSuccessResult(custBlDe);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<CustBlDe> list = custBlDeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getByBlId")
    public Result getByBlId(String id) {
        List<CustBlDe> list = daoService.getbyList("select * from cust_bl_de c where c.bl_id='" + id + "' order by sort", CustBlDe.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/deld")
    public Result deld(String id) {
        List<CustBlDe> list = daoService.getbyList("DELETE FROM cust_bl_de WHERE bl_id ='" + id + "'", CustBlDe.class);
        return ResultGenerator.genSuccessResult(list);
    }
}
