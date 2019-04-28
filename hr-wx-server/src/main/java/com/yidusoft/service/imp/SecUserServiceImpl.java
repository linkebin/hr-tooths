package com.yidusoft.service.imp;

import com.yidusoft.core.AbstractService;
import com.yidusoft.dao.SecUserMapper;
import com.yidusoft.domain.SecUser;
import com.yidusoft.service.SecUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
@Service
@Transactional
public class SecUserServiceImpl extends AbstractService<SecUser> implements SecUserService {
    @Resource
    private SecUserMapper secUserMapper;

    @Override
    public List<SecUser> findUserAll() {
        return secUserMapper.findUserAll();
    }
}
