package com.yidusoft.project.system.service.impl;

import com.yidusoft.project.system.dao.CustomerMapper;
import com.yidusoft.project.system.domain.Customer;
import com.yidusoft.project.system.service.CustomerService;
import com.yidusoft.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
@Service
@Transactional
public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public void updateCustomer(Customer customer){
        customerMapper.updateCustomer(customer);
    }
}
