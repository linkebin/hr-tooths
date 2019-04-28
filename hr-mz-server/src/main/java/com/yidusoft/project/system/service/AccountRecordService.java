package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.AccountRecord;
import com.yidusoft.core.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/12/29.
 */
public interface AccountRecordService extends Service<AccountRecord> {
    List<Map<String,Object>> getList(Map<String, Object> map);
}
