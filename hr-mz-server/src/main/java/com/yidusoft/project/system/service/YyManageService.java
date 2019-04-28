package com.yidusoft.project.system.service;

import com.yidusoft.core.Service;
import com.yidusoft.project.system.domain.YyManage;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/11/13.
 */
public interface YyManageService extends Service<YyManage> {
	List<YyManage> getYYManage(String clinic,String doctor,String code,String project,String startTime,String endTime,int limit,int page);

	int getYYManageCount(String clinic,String doctor,String code,String project,String startTime,String endTime);

	/**
	* @Author: didi_c
	* @Description: 模糊查询
	* @Date: 2018/12/11 16:33
	* @Param: 
	* @returns: 
	*/
	List<YyManage> getList(Map<String, Object> map);
}
