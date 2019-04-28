package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.NoticeAdministrationMapper;
import com.yidusoft.project.system.domain.NoticeAdministration;
import com.yidusoft.project.system.service.NoticeAdministrationService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class NoticeAdministrationServiceImpl extends AbstractService<NoticeAdministration> implements NoticeAdministrationService {
    @Resource
    private NoticeAdministrationMapper noticeAdministrationMapper;

}
