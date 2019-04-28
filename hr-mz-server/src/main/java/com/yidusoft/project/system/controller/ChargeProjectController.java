package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.ChargeProject;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.ChargeProjectService;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/27.
*/
@RestController
@RequestMapping("/charge/project")
public class ChargeProjectController {
    @Resource
    private ChargeProjectService chargeProjectService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String  param) {
        try {
            ChargeProject chargeProject = JSON.parseObject(param,ChargeProject.class);
            chargeProject.setId(UUID.randomUUID().toString());
            chargeProject.setOrgId(Security.getUser().getOrgId());
            chargeProjectService.save(chargeProject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String  param) {
        try {
            ChargeProject chargeProject= JSON.parseObject(param,ChargeProject.class);
            chargeProject.setOrgId(Security.getUser().getOrgId());
            chargeProjectService.update(chargeProject);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable String  id) {
        chargeProjectService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam String findName) {
        PageHelper.startPage(page, size);
        String sql  = "SELECT * FROM `charge_project` where org_id = '"+Security.getUser().getOrgId()+"'";
        if(StringUtils.isNotBlank(findName)){
            sql=sql+" and  (project_name  like '%"+findName+"%' or code  like '%"+findName+"%' or  type  like '%"+findName+"%')";
        }
        List list=daoService.getbyList(sql,ChargeProject.class);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    public Result update(ChargeProject chargeProject) {
        chargeProjectService.update(chargeProject);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        ChargeProject chargeProject = chargeProjectService.findById(id);
        return ResultGenerator.genSuccessResult(chargeProject);
    }

//    @PostMapping("/list")
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,String ids) {
//        PageHelper.startPage(page, size);
//        List<ChargeProject> list = chargeProjectService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }

    @PostMapping("/getOrgIdlist")
    public Result getorgIdlist(){
        SecUser secUser=Security.getUser();
        List<ChargeProject> list=daoService.getbyList("select c.* from charge_project c where c.org_id='"+secUser.getOrgId()+"'",ChargeProject.class);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getOrgIdlists")
    public Result getorgIdlists(String str){
        SecUser secUser=Security.getUser();
        String flag[]=str.split("/");
        List<ChargeProject> list=null;
        if(str.indexOf("/")!=-1){
            list=daoService.getbyList("select c.* from sec_user u,charge_project c where u.ORG_ID=c.org_id and c.project_name like '%"+flag[0]+"%' and c.type like '%"+flag[1]+"%' and u.ID_='"+secUser.getId()+"'"+
                    "or u.ORG_ID=c.org_id and c.code like '%"+flag[0]+"%' and c.type like '%"+flag[1]+"%'  and u.ID_='"+secUser.getId()+"'",ChargeProject.class);
        }else{
            list=daoService.getbyList("select c.* from sec_user u,charge_project c where u.ORG_ID=c.org_id and c.project_name like '%"+str+"%' and u.ID_='"+secUser.getId()+"'"+
                    "or u.ORG_ID=c.org_id and c.code like '%"+str+"%'  and u.ID_='"+secUser.getId()+"'"+
            "or u.ORG_ID=c.org_id and c.type like '%"+str+"%' and u.ID_='"+secUser.getId()+"'",ChargeProject.class);
        }
        return ResultGenerator.genSuccessResult(list);
    }
}
