package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.CustReturn;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2019/03/02.
 */
public interface CustReturnService extends Service<CustReturn> {
    List<Map<String,Object>> getReturnS(String orgId,String custId);

}
