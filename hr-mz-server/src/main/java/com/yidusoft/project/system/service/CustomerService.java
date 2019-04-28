package com.yidusoft.project.system.service;
import com.yidusoft.project.system.domain.Customer;
import com.yidusoft.core.Service;


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
}
