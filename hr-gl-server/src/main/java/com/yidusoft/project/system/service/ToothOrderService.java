package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.ToothOrder;
import com.yidusoft.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/12/18.
 */
public interface ToothOrderService extends Service<ToothOrder> {
    public List<Map<String,Object>> getselAll(Map<String,Object> map);
}
