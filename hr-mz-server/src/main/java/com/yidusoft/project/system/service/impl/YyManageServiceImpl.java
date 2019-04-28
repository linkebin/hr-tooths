package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.YyManageMapper;
import com.yidusoft.project.system.domain.YyManage;
import com.yidusoft.project.system.service.YyManageService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class YyManageServiceImpl extends AbstractService<YyManage> implements YyManageService {
    @Resource
    private YyManageMapper yyManageMapper;

    @Override
    public List<YyManage> getYYManage(String clinic, String doctor, String code, String project, String startTime, String endTime, int limit, int page) {
        return yyManageMapper.getYYManage(clinic,doctor,code,project,startTime,endTime,limit,page);
    }

    @Override
    public int getYYManageCount(String clinic, String doctor, String code, String project, String startTime, String endTime) {
        Integer num = yyManageMapper.getYYManageCount(clinic,doctor,code,project,startTime,endTime);
        if(num == null){
            return 0;
        }else{
            return num;
        }
    }

    @Override
    public List<YyManage> getList(Map<String, Object> map){
        return yyManageMapper.getList(map);
    }
}
