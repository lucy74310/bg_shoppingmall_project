package com.cafe24.shoppingmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.SwaggerConfig;

@Configuration
// spring-servlet.xml의 <mvc: 붙은 태그들
@EnableAspectJAutoProxy
// spring-servlet.xml의 <context:component-scan base-package="com.cafe24.springex.controller" />
@ComponentScan({"com.cafe24.shoppingmall.backend.controller.api"})
@Import({TestMVCConfig.class, MessageConfig.class, SwaggerConfig.class})
public class TestWebConfig {
	
	
	
}
