package com.yidusoft.project.system.operation;

import com.yidusoft.project.system.service.DaoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: libf
 * Time: 2018-12-07 10:34
 */
@Component
public class ScheduledHandler {

	@Resource
	private DaoService daoService;

	//定时任务
	@Scheduled(cron = "0 0 1 */1 * ?")
	public void scheduled(){
		System.out.println("测试！");
	}
}
