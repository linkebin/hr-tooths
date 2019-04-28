package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.CustSfjlMapper;
import com.yidusoft.project.system.domain.CustSfjl;
import com.yidusoft.project.system.service.CustSfjlService;
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
public class CustSfjlServiceImpl extends AbstractService<CustSfjl> implements CustSfjlService {
    @Resource
    private CustSfjlMapper custSfjlMapper;

    @Override
    public List<Map<String, Object>> findCustSfjl(String clinic,String doctor,String starTime,String endTime) {
        return custSfjlMapper.findCustSfjl(clinic,doctor,starTime,endTime);
    }

    @Override
    public Map<String, Object> findCustSfjlSum(String clinic, String doctor, String starTime, String endTime) {
        return custSfjlMapper.findCustSfjlSum(clinic,doctor,starTime,endTime);
    }

    @Override
    public Map<String, Object> findCustSfjlCostSum(String clinic, String doctor, String starTime, String endTime) {
        return custSfjlMapper.findCustSfjlCostSum(clinic,doctor,starTime,endTime);
    }

    @Override
    public List<Map<String, Object>> findDrugRevenue(String clinic, String starTime, String endTime) {
        return custSfjlMapper.findDrugRevenue(clinic,starTime,endTime);
    }

    @Override
    public List<Map<String, Object>> findProjectRevenue(String clinic, String starTime, String endTime) {
        return custSfjlMapper.findProjectRevenue(clinic,starTime,endTime);
    }

    @Override
    public int findDrugRevenueSum(String clinic, String starTime, String endTime) {
        Integer num = custSfjlMapper.findDrugSum(clinic,starTime,endTime);
        if(num == null){
            return 0;
        }else{
            return num;
        }
    }

    @Override
    public int findProjectRevenueSum(String clinic, String starTime, String endTime) {
        Integer num = custSfjlMapper.findProjectSum(clinic,starTime,endTime);
        if(num == null){
            return 0;
        }else{
            return num;
        }
    }

    @Override
    public int patients() {
        Integer num = custSfjlMapper.patients();
        if(num == null){
            return 0;
        }else{
            return num;
        }
    }

}
