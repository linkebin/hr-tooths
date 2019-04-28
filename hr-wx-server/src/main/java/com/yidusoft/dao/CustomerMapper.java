package com.yidusoft.dao;

import com.yidusoft.domain.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(String custId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String custId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}