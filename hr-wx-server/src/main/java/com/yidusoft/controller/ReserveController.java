package com.yidusoft.controller;

import com.alibaba.fastjson.JSONArray;
import com.yidusoft.domain.ProjectManage;
import com.yidusoft.domain.SecOrg;
import com.yidusoft.domain.SecUser;
import com.yidusoft.service.ProjectManageService;
import com.yidusoft.service.SecOrgService;
import com.yidusoft.service.SecUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: libf
 * Time: 2018-12-07 15:51
 */
@Controller
@RequestMapping("/wx/templates")
public class ReserveController {

	@Resource
	private SecOrgService secOrgService;

	@Resource
	private ProjectManageService projectManageService;

	@Resource
	private SecUserService secUserService;

	@RequestMapping("/index")
	public String menuCreate(ModelMap map){
		List<SecOrg> secOrgList = secOrgService.getSecOrg();

		Map<String,Object> userMap = new HashMap<>();
		List<SecUser> userList = secUserService.findUserAll();

		Map<String,Object> projectMap = new HashMap<>();
		List<ProjectManage> projectList= projectManageService.findProjectAll();

		List<Map<String,Object>> orgList = new ArrayList<>();
		secOrgList.forEach(t->{
			Map<String,Object> orgMap = new HashMap<>();
			orgMap.put("value",t.getId());
			orgMap.put("label",t.getOrgName());
			projectMap.put(t.getId(),"");
			userMap.put(t.getId(),"");
			orgList.add(orgMap);
		});

		userList.forEach(t->{
			JSONArray list = new JSONArray();
			if(userMap.get(t.getOrgId())!=null &&!"".equals(userMap.get(t.getOrgId()).toString())){
				list = JSONArray.parseArray(userMap.get(t.getOrgId()).toString());
			}
			Map<String,Object> Map = new HashMap<>();
			Map.put("value",t.getId());
			Map.put("label",t.getUserName());
			list.add(Map);
			userMap.put(t.getOrgId(),list);
		});

		projectList.forEach(t->{
			JSONArray list = new JSONArray();
			if(projectMap.get(t.getOrgId())!=null &&!"".equals(projectMap.get(t.getOrgId()).toString())){
				list = JSONArray.parseArray(projectMap.get(t.getOrgId()).toString());
			}
			Map<String,Object> Map = new HashMap<>();
			Map.put("value",t.getId());
			Map.put("label",t.getProjectName());
			list.add(Map);
			projectMap.put(t.getOrgId(),list);
		});
		map.put("secOrgList",orgList);
		map.put("userMap",userMap);
		map.put("projectMap",projectMap);
		return "template";
	}

	@RequestMapping("/success")
	public String success(ModelMap map){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		map.put("time",dateNowStr);
		return "success";
	}
}
