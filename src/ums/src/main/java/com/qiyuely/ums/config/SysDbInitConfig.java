package com.qiyuely.ums.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import com.qiyuely.ums.service.SysService;

/**
 * 数据库初始化
 * 
 * @author Qiaoxin.Hong
 *
 */
@Configuration
public class SysDbInitConfig implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		SysService sysService = event.getApplicationContext().getBean(SysService.class);
		sysService.initDb();
	}
}
