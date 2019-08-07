package com.cafe24.shoppingmall.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.cafe24.shoppingmall.backend.config.web.MVCConfig;
import com.cafe24.shoppingmall.backend.config.web.MessageConfig;
import com.cafe24.shoppingmall.backend.config.web.SwaggerConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shoppingmall.backend"})
@Import({MVCConfig.class, MessageConfig.class, SwaggerConfig.class})
public class WebConfig {
	
	public WebConfig() {
		System.out.println("webConfig");
	}
	
}
