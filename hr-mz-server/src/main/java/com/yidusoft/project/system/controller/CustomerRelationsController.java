package com.yidusoft.project.system.controller;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.CustomerRelations;
import com.yidusoft.project.system.service.CustomerRelationsService;
import com.yidusoft.project.system.service.DaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
* Created by CodeGenerator on 2019/03/26.
*/
@RestController
@RequestMapping("/customer/relations")
public class CustomerRelationsController {
    @Resource
    private CustomerRelationsService customerRelationsService;
    @Resource
    private DaoService daoService;

    @PostMapping("/add")
    public Result add(String cust_id,String cust_id_bd,String relation) {
        CustomerRelations customerRelations= new CustomerRelations();
        customerRelations.setId(UUID.randomUUID().toString());
        customerRelations.setCustId(cust_id);
        customerRelations.setCustIdBd(cust_id_bd);
        customerRelations.setRelation(relation);
        customerRelationsService.save(customerRelations);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        customerRelationsService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(CustomerRelations customerRelations) {
        customerRelationsService.update(customerRelations);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam String id) {
        CustomerRelations customerRelations = customerRelationsService.findById(id);
        return ResultGenerator.genSuccessResult(customerRelations);
    }

    @PostMapping("/list")
    public Result list(String cust_id) {
        List<Map<String,Object>> list = daoService.getDivList("select r.id_,r.cust_id_bd as cust_id,r.relation,o.cust_name from customer_relations r LEFT JOIN customer o ON r.cust_id_bd=o.cust_id where r.cust_id='"+cust_id+"'\n" +
                "UNION\n" +
                "select r.id_,r.cust_id,r.relation,o.cust_name from customer_relations r LEFT JOIN customer o ON r.cust_id=o.cust_id where r.cust_id_bd='"+cust_id+"'");
        return ResultGenerator.genSuccessResult(list);
    }
}
