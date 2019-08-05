package com.cafe24.shoppingmall.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cafe24.shoppingmall.backend.config.app.DBConfig;
import com.cafe24.shoppingmall.backend.config.app.MyBatisConfig;


@Configuration
@EnableTransactionManagement
@ComponentScan({"com.cafe24.shoppingmall.backend"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
	public AppConfig() {
		System.out.println("appConfig");
	}
}
