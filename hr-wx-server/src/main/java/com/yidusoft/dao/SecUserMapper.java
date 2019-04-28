package com.yidusoft.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.domain.SecUser;

import java.util.List;

/**
 * User: libf
 * Time: 2018-12-07 15:26
 */
public interface SecUserMapper extends Mapper<SecUser> {
	List<SecUser> findUserAll();
}
