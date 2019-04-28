package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.SecOrg;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface SecOrgService extends Service<SecOrg> {
    List<Map<String,Object>> findAllByInfo(Map<String,Object> map);

}
