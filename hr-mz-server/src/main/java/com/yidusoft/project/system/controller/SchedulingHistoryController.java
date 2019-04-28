package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SchedulingHistory;
import com.yidusoft.project.system.domain.SchedulingPlan;
import com.yidusoft.project.system.service.SchedulingHistoryService;
import com.yidusoft.project.system.service.SchedulingPlanService;
import com.yidusoft.utils.DateUtils;
import com.yidusoft.utils.Security;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* Created by CodeGenerator on 2018/11/28.
*/
@RestController
@RequestMapping("/scheduling/history")
public class SchedulingHistoryController {
    @Resource
    private SchedulingHistoryService schedulingHistoryService;
    @Resource
    private SchedulingPlanService schedulingPlanService;

    @PostMapping("/savePlan")
    public Result add(Integer year,Integer month,String planList,String historyId) {
        //新增
        if(!StringUtils.isNotBlank(historyId)){
            SchedulingHistory schedulingHistory = new SchedulingHistory();
            schedulingHistory.setId(UUID.randomUUID().toString());
            schedulingHistory.setOrgId(Security.getUser().getOrgId());
            schedulingHistory.setCreator(Security.getUser().getUserName());
            schedulingHistory.setCreateTime(new Date());
            schedulingHistory.setYear(year);
            schedulingHistory.setMonth(month);
            List<SchedulingPlan>  list = JSONArray.parseArray(planList,SchedulingPlan.class);
            for (SchedulingPlan plan:list) {
                plan.setId(UUID.randomUUID().toString());
                plan.setClinic(schedulingHistory.getOrgId());
                plan.setHistoryId(schedulingHistory.getId());
                Date planDate = DateUtils.singleParse(schedulingHistory.getYear(),schedulingHistory.getMonth(),plan.getDate());
                plan.setPlanDate(planDate);
                schedulingPlanService.save(plan);
            }
            schedulingHistoryService.save(schedulingHistory);
        }else {
            //修改
            List<SchedulingPlan>  list = JSONArray.parseArray(planList,SchedulingPlan.class);
            for (SchedulingPlan plan:list) {
                SchedulingHistory schedulingHistory =schedulingHistoryService.findById(historyId);
                if(StringUtils.isNotBlank(plan.getId())){
                    SchedulingPlan byId = schedulingPlanService.findById(plan.getId());
                    if(byId!=null&&StringUtils.equals(byId.getState(),plan.getState())){

                    }else{
                        byId.setState(plan.getState());
                        Date planDate = DateUtils.singleParse(schedulingHistory.getYear(),schedulingHistory.getMonth(),plan.getDate());
                        byId.setPlanDate(planDate);
                        schedulingPlanService.update(byId);
                    }
                }else{
                    plan.setId(UUID.randomUUID().toString());
                    plan.setClinic(Security.getUser().getOrgId());
                    plan.setHistoryId(historyId);
                    Date planDate = DateUtils.singleParse(schedulingHistory.getYear(),schedulingHistory.getMonth(),plan.getDate());
                    plan.setPlanDate(planDate);
                    schedulingPlanService.save(plan);
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String  id) {
        schedulingHistoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/list")
    public Result list(Integer  year) {
        Condition condition = new Condition(SchedulingHistory.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orgId", Security.getUser().getOrgId());
        criteria.andEqualTo("year",year);
        List<SchedulingHistory> list = schedulingHistoryService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }

}
