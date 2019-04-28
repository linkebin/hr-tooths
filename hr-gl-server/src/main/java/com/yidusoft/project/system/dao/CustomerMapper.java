package com.yidusoft.project.system.dao;

import com.yidusoft.core.Mapper;
import com.yidusoft.project.system.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper extends Mapper<Customer> {
    /**
    * @Author: didi_c
    * @Description: 修改
    * @Date: 2018/11/15 9:53
    * @Param: customer
    * @returns: void
    */
    void updateCustomer(Customer customer);

    List<Map<String,Object>> findCustomer(@Param("clinic") String clinic, @Param("code") String code);
}