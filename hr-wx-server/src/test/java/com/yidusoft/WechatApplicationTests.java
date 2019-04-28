package com.yidusoft;

import com.yidusoft.dao.SecUserMapper;
import com.yidusoft.domain.SecUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest
public class WechatApplicationTests {
	@Autowired
	private SecUserMapper secUserMapper;
	@Test
	public void contextLoads() {
		SecUser secUser = secUserMapper.selectByPrimaryKey("1");
		System.out.println(secUser.getAccount());
	}

}
