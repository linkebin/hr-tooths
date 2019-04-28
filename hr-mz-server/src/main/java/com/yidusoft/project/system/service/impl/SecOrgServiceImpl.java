package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.SecOrgMapper;
import com.yidusoft.project.system.domain.SecOrg;
import com.yidusoft.project.system.service.SecOrgService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecOrgServiceImpl extends AbstractService<SecOrg> implements SecOrgService {
    @Resource
    private SecOrgMapper secOrgMapper;

    public  List<Map<String,Object>> findAllByInfo(Map<String,Object> map) {
      return   secOrgMapper.findAllByInfo(map);
    }
}
