package com.yidusoft.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.project.system.domain.CustLevel;
import com.yidusoft.project.system.domain.SecUser;
import com.yidusoft.project.system.service.CustLevelService;
import com.yidusoft.utils.Security;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * User: libf
 * Time: 2018-11-14 10:42
 */
@RestController
@RequestMapping("/cust/level")
public class CustLevelController {
	@Resource
	private CustLevelService custLevelService;

	@PostMapping("/add")
	public Result add(String json) {
		CustLevel custLevel= JSON.parseObject(json,CustLevel.class);
		custLevel.setLevelId(UUID.randomUUID().toString());
		SecUser user = Security.getUser();
		custLevel.setUserId(user.getId());
		custLevel.setUserName(user.getUserName());
		if(custLevel.getParentId() ==null || "".equals(custLevel.getParentId())){
			custLevel.setParentId("1");
		}
		custLevelService.save(custLevel);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/delete")
	public Result delete(@RequestParam String id) {
		custLevelService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/update")
	public Result update(String json) {
		SecUser user = Security.getUser();
		CustLevel custLevel= JSON.parseObject(json,CustLevel.class);
		custLevel.setUserId(user.getId());
		custLevel.setUserName(user.getUserName());
		custLevelService.update(custLevel);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/detail")
	public Result detail(@RequestParam String id) {
		CustLevel custLevel = custLevelService.findById(id);
		return ResultGenerator.genSuccessResult(custLevel);
	}

	@PostMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<CustLevel> list = custLevelService.findAll();
		PageInfo pageInfo = new PageInfo(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@PostMapping("/getCustLevel")
	public String getCustLevel() {
		List<Map<String,Object>> list = custLevelService.getCustLevel();
		return JSON.toJSONString(list);
	}

	@GetMapping("/findCustLevel")
	public Result findCustLevel(String id,int page,int size) {
		List<CustLevel> list = custLevelService.findCustLevel(id,size,(page -1)*size);
		int count = custLevelService.findCustLevelCount(id);
		return ResultGenerator.genSuccessResult(list).setTotal(count);
	}
}
