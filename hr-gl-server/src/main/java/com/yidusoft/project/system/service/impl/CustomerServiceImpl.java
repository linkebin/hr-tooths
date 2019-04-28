package com.yidusoft.project.system.service.impl;

import com.yidusoft.core.AbstractService;
import com.yidusoft.project.system.dao.CustomerMapper;
import com.yidusoft.project.system.domain.Customer;
import com.yidusoft.project.system.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


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

    @Override
    public List<Map<String, Object>> findCustomer(String clinic, String code) {
        return customerMapper.findCustomer(clinic,code);
    }
}
