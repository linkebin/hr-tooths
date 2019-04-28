package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.SecOrgService;
import com.yidusoft.project.system.service.SecUserService;
import com.yidusoft.utils.CodeHelper;
import com.yidusoft.utils.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by you on 2017/7/17.
 */
@RestController
public class IndexController {
    @Autowired
    SecUserService secUserService;
    @Autowired
    SecOrgService secOrgService;

    @RequestMapping(value="/test")
    public String test(HttpServletResponse response){
        SecUser user = Security.getUser();
        return JSONObject.toJSON(user).toString();
    }

    @RequestMapping(value="/getCode")
    public String getCode(String title){
        return CodeHelper.getCode(title);
    }

}
