package com.yidusoft.project.system.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.SchedulingPlan;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.DaoService;
import com.yidusoft.project.system.service.SchedulingPlanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2018/11/13.
*/
@RestController
@RequestMapping("/scheduling/plan")
public class SchedulingPlanController {
    @Resource
    private SchedulingPlanService schedulingPlanService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(SchedulingPlan schedulingPlan) {
        schedulingPlanService.save(schedulingPlan);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        schedulingPlanService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SchedulingPlan schedulingPlan) {
        schedulingPlanService.update(schedulingPlan);
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        SchedulingPlan schedulingPlan = schedulingPlanService.findById(id);
        return ResultGenerator.genSuccessResult(schedulingPlan);
    }

    @PostMapping("/getPlanList")
    public Result list(String historyId) {
        Condition condition = new Condition(SchedulingPlan.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("historyId", historyId);
        List<SchedulingPlan> list = schedulingPlanService.findByCondition(condition);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/getsb")
    public Result getsb(String id){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<SecUser> list=daoService.getbyList("select u.* from scheduling_plan s,sec_user u where s.plan_date='"+sdf.format(date)+"' and s.state='班' and u.deleted = 0 and s.clinic=(select ORG_ID from sec_user where ID_='"+id+"') and s.doctors=u.ID_",SecUser.class);
        return  ResultGenerator.genSuccessResult(list);
    }
}
