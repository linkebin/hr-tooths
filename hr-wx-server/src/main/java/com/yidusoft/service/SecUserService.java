package com.yidusoft.service;

import com.yidusoft.core.Service;
import com.yidusoft.domain.SecUser;

import java.util.List;


/**
 * Created by CodeGenerator on 2018/04/20.
 */
public interface SecUserService extends Service<SecUser> {
	List<SecUser> findUserAll();
}
