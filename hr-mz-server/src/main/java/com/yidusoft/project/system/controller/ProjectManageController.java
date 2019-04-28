package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.ProjectManage;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.ProjectManageService;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/26.
*/
@RestController
@RequestMapping("/project/manage")
public class ProjectManageController {
    @Resource
    private ProjectManageService projectManageService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String  param) {
        try {
            ProjectManage projectManage= JSON.parseObject(param,ProjectManage.class);
            projectManage.setId(UUID.randomUUID().toString());
            projectManage.setOrgId(Security.getUser().getOrgId());
            projectManageService.save(projectManage);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String  param) {
        try {
            ProjectManage projectManage= JSON.parseObject(param,ProjectManage.class);
            projectManage.setOrgId(Security.getUser().getOrgId());
            projectManageService.update(projectManage);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable String  id) {
        projectManageService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,@RequestParam String findName) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(ProjectManage.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orgId",Security.getUser().getOrgId());
        if(StringUtils.isNotBlank(findName)){
            criteria.andLike("projectName","%"+findName+"%");
        }
        List list = projectManageService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/getProject")
    public Result getProject(){
        SecUser secUser=Security.getUser();
        List<Map<String,Object>> list = daoService.getDivList("select id_ id,project_name name from project_manage where org_id ='" +secUser.getOrgId()+ "'");
        return ResultGenerator.genSuccessResult(list);
    }
}
