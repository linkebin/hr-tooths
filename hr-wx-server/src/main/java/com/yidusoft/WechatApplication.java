package com.yidusoft;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan(basePackages = {"com.yidusoft.dao"})
public class WechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
}
