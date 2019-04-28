package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.Customer;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface CustomerService extends Service<Customer> {
    /**
    * @Author: didi_c
    * @Description: 修改
    * @Date: 2018/11/15 9:52
    * @Param: customer
    * @returns: void
    */
    void updateCustomer(Customer customer);

    List<Map<String,Object>> findCustomer(String clinic,String code);
}
