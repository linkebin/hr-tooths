package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.PotentialCustMapper;
import com.yidusoft.project.system.domain.PotentialCust;
import com.yidusoft.project.system.service.PotentialCustService;
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
public class PotentialCustServiceImpl extends AbstractService<PotentialCust> implements PotentialCustService {
    @Resource
    private PotentialCustMapper potentialCustMapper;

    @Override
    public List<Map<String,Object>> findPotentialCust(String source, String clinic, String starTime, String endTime, String customer, int limit, int page) {
        return potentialCustMapper.findPotentialCust(source,clinic,starTime,endTime,customer,limit,page);
    }

	@Override
	public List<Map<String,Object>> findPotentialCustAll(String source, String clinic, String starTime, String endTime, String customer) {
		return potentialCustMapper.findPotentialCustAll(source,clinic,starTime,endTime,customer);
	}

	@Override
	public int findPotentialCustCount(String source, String clinic, String starTime, String endTime, String customer) {
		return potentialCustMapper.findPotentialCustCount(source,clinic,starTime,endTime,customer);
	}
}
