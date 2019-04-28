package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SecOrg;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.SecOrgService;
import com.yidusoft.utils.CodeHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/04/20.
*/
@RestController
@RequestMapping("/sec/org")
public class SecOrgController {
    @Resource
    private SecOrgService secOrgService;

    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String  json) {
        SecOrg secOrg= JSON.parseObject(json,SecOrg.class);
        if(secOrg.getState()==null){
            secOrg.setState(0);
        }
        secOrg.setId(UUID.randomUUID().toString());
        secOrg.setDeleted(0);
        secOrg.setOrgCode(CodeHelper.getCode("DP"));
        secOrgService.save(secOrg);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 删除
     * @param delIds
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestParam(name = "delIds", required = true) String delIds) {
        String[] ids = null;
        if(StringUtils.isNotBlank(delIds)){
            ids = delIds.split(",");
        }
        if(ids!=null&&ids.length>0){
            for (int i = 0; i <ids.length ; i++) {
                if(StringUtils.isNotBlank(ids[i])){
                    SecOrg secOrg = secOrgService.findById(ids[i]);
                    secOrg.setDeleted(1);
                    secOrgService.update(secOrg);
                }
            }
        }
        return ResultGenerator.genSuccessResult("删除成功");
    }

    /**
     * 启用禁用
     * @param idStr
     * @param state
     * @return
     */
    @PostMapping("/openOrClose")
    public Result openOrClose(@RequestParam String idStr, @RequestParam Integer state) {
        String[] ids = null;
        if(StringUtils.isNotBlank(idStr)){
            ids = idStr.split(",");
        }
        if(ids!=null&&ids.length>0){
            for (int i = 0; i <ids.length ; i++) {
                if(StringUtils.isNotBlank(ids[i])){
                    SecOrg secOrg = secOrgService.findById(ids[i]);
                    secOrg.setState(state);
                    secOrgService.update(secOrg);
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String  json) {
        SecOrg secOrg= JSON.parseObject(json,SecOrg.class);
        if(secOrg.getState()==null){
            secOrg.setState(0);
        }
        secOrgService.update(secOrg);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        Map map = new HashMap<>();
        Map orgMap = new HashMap();
        map.put("orgId",id);
        List<Map<String,Object>> list = secOrgService.findAllByInfo(map);
        if(list!=null&&!list.isEmpty()){
            orgMap = list.get(0);
        }
        return ResultGenerator.genSuccessResult(orgMap);
    }

    /**
     * 所有部门信息（部门树）
     * @return
     */
    @PostMapping("/dpTree")
    public List dpTree() {
        //Condition的用法参照
//        Condition condition = new Condition(SecOrg.class);
//        Example.Criteria criteria = condition.createCriteria();
//        criteria.andEqualTo("deleted",0);
        Map map = new HashMap<>();
        map.put("orgName","");
        map.put("state",1);
        map.put("parentId","");
        List<Map<String,Object>> list = secOrgService.findAllByInfo(map);
        return list;
    }

    @PostMapping("/list")
    public Result list(@RequestParam String  orgName, @RequestParam String  parentId, @RequestParam String  state, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Map map = new HashMap<>();
        map.put("orgName",orgName);
        map.put("state",state);
        map.put("parentId",parentId);
        List<Map<String,Object>>list = secOrgService.findAllByInfo(map);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genTableListResult(pageInfo);
    }

    @PostMapping("/secOrgTree")
    public String secOrgTree() {
        List<Map<String,Object>> list = daoService.getDivList("select ID_ id ,ORG_NAME name,PARENT_ID pId,case when id_='122' then 1 else 0 end open from sec_org where DELETED = 0 and state = 1");
        return JSON.toJSONString(list);
    }

    @PostMapping("/secOrgClinic")
    public Map secOrgClinic() {
        List<Map<String,Object>> clinicList = daoService.getDivList("select ID_ id ,ORG_NAME name from sec_org where DELETED = 0 and state = 1 and type='门诊'");
        List<Map<String,Object>> doctorList = daoService.getDivList("select ID_ id, USER_NAME name,ORG_ID orgId from sec_user where DELETED = 0 and STANDBY1 = '医生'");
        Map<String,Object> map = new HashMap<>();
        map.put("clinic",clinicList);
        map.put("doctor",doctorList);
        return map;
    }
}
