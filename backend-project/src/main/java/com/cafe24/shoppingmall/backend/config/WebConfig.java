package com.cafe24.shoppingmall.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.cafe24.shoppingmall.backend.config.web.MVCConfig;
import com.cafe24.shoppingmall.backend.config.web.SwaggerConfig;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shoppingmall.backend.controller.api",
	"com.cafe24.shoppingmall.backend.controller", "com.cafe24.shoppingmall.backend.exception"})
@Import({MVCConfig.class, SwaggerConfig.class})
public class WebConfig {
	
	public WebConfig() {
		System.out.println("webConfig");
	}
	
}
