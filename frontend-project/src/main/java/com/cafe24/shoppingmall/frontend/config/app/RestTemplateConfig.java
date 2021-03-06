package com.cafe24.shoppingmall.frontend.config.app;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Value("${spring.restTemplate.factory.readTimeout}")
    private int READ_TIMEOUT;
	@Value("${spring.restTemplate.factory.connectTimeout}")
    private int CONNECT_TIMEOUT;
	@Value("${spring.restTemplate.httpClient.maxConnTotal}")
    private int MAX_CONN_TOTAL;
	@Value("${spring.restTemplate.httpClient.maxConnPerRoute}")
    private int MAX_CONN_PER_ROUTE;
	
	@Bean
	public RestTemplate restTemplate() {
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		// 가끔 
		// Cause: java.sql.SQLNonTransientConnectionException 에러 나는 문제
		// http://woowabros.github.io/experience/2017/01/20/billing-event.html
		// ** 빌링 웹 애플리케이션 서버에서 새로운 DB 커넥션을 생성할 때 dnslookup이 6초 이상 걸리는 바람에 DB 서버의 connect_timeout(=5초)이 발생한 문제라는 것을 알 수 있게 되었습니다.
		// connect_timeout 시간 높여주었음 = application.yml에서  
		
		HttpClient httpClient = HttpClientBuilder.create() 
            .setMaxConnTotal(MAX_CONN_TOTAL) 
            .setMaxConnPerRoute(MAX_CONN_PER_ROUTE) 
            .build();
        factory.setHttpClient(httpClient);
		
		RestTemplate restTemplate = new RestTemplate(factory);
		
		return restTemplate;
	}


	
	
	
}
