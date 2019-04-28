package com.yidusoft.service.imp;

import com.yidusoft.core.AbstractService;
import com.yidusoft.dao.YyManageMapper;
import com.yidusoft.domain.YyManage;
import com.yidusoft.service.YyManageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class YyManageServiceImpl extends AbstractService<YyManage> implements YyManageService {
    @Resource
    private YyManageMapper yyManageMapper;

}
