package com.yidusoft.controller;

import com.yidusoft.core.Result;
import com.yidusoft.core.ResultGenerator;
import com.yidusoft.domain.YyManage;
import com.yidusoft.service.YyManageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * User: libf
 * Time: 2018-12-11 10:17
 */
@RestController
@RequestMapping("/wx/dispose")
public class DisposeController {
	@Resource
	private YyManageService yyManageService;

	@PostMapping("/add")
	public Result add(String date,String time,String name,String tel,String clinic,String doctor,String project) throws ParseException {
		YyManage yyManage = new YyManage();
		yyManage.setYyClinic(clinic);
		yyManage.setYyCode("YY"+new Date().getTime());
		yyManage.setYyCustName(name);
		yyManage.setYyCustTel(tel);
		yyManage.setYyDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		yyManage.setYyTime(time);
		yyManage.setYyDoctor(doctor);
		yyManage.setYyProject(project);
		yyManage.setYyId(UUID.randomUUID().toString());
		yyManage.setRemark("");
		yyManage.setYyAge(0);
		yyManage.setCreateTime(new Date());
		yyManage.setYyCustSex("");
		yyManage.setYySource("微信预约");
		yyManageService.save(yyManage);
		return ResultGenerator.genSuccessResult();
	}
}
